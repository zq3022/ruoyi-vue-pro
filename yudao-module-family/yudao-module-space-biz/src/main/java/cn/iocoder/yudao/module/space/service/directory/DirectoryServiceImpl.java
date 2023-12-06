package cn.iocoder.yudao.module.space.service.directory;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectoryPageReqVO;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectorySaveReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.dal.mysql.directory.DirectoryMapper;
import cn.iocoder.yudao.module.space.enums.MessageTypeEnum;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;
import cn.iocoder.yudao.module.space.mq.producer.DirectoryProducer;
import cn.iocoder.yudao.module.space.service.source.SourceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.file.SimplePathVisitor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Stack;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.DIRECTORY_NOT_EXISTS;

/**
 * 目录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class DirectoryServiceImpl implements DirectoryService {

    @Resource
    private DirectoryMapper directoryMapper;
    @Resource
    private SourceService sourceService;
    @Resource
    private DirectoryProducer directoryProducer;

    @Override
    public Long createDirectory(DirectorySaveReqVO createReqVO) {
        // 插入
        DirectoryDO directory = BeanUtils.toBean(createReqVO, DirectoryDO.class);
        directoryMapper.insert(directory);
        // 返回
        return directory.getId();
    }

    @Override
    public void updateDirectory(DirectorySaveReqVO updateReqVO) {
        // 校验存在
        validateDirectoryExists(updateReqVO.getId());
        // 更新
        DirectoryDO updateObj = BeanUtils.toBean(updateReqVO, DirectoryDO.class);
        directoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteDirectory(Long id) {
        // 校验存在
        validateDirectoryExists(id);
        // 删除
        directoryMapper.deleteById(id);
    }

    private void validateDirectoryExists(Long id) {
        if (directoryMapper.selectById(id) == null) {
            throw exception(DIRECTORY_NOT_EXISTS);
        }
    }

    @Override
    public DirectoryDO getDirectory(Long id) {
        return directoryMapper.selectById(id);
    }

    @Override
    public PageResult<DirectoryDO> getDirectoryPage(DirectoryPageReqVO pageReqVO) {
        return directoryMapper.selectPage(pageReqVO);
    }

    @Override
    public String getFullPath(Long directoryId) {
        DirectoryDO directory = getDirectory(directoryId);

        if(directory == null){
            return null;
        }
        SourceDO sourceDO = sourceService.getSource(directory.getSourceId());
        return StrUtil.concat(true, sourceDO.getPath(), File.separator, directoryMapper.getFullPath(directory));
    }

    /**
     * 处理目录源的消
     *
     * @param message 源变更 消息
     */
    @Override
    @Transactional
    public void doSourceMessage(SourceMessage message) throws IOException {
        log.info("处理目录源的消息,{}",message.getSourceId());
        List<DirectoryDO> createdCol = new ArrayList<>();
        List<DirectoryDO> deletedCol = new ArrayList<>();
        switch (MessageTypeEnum.valueOf(message.getMessageType())) {
            case ADD: // 新增源
                createdCol = createTree(message);
                break;
            case DELETE:
                // 删除源
                deletedCol = deleteTree(message);
                break;
            case UPDATE:
                updateTree(message, createdCol, deletedCol);
                break;
        }

        // 发送文件夹变更的消息
        if (!createdCol.isEmpty()) {
            directoryProducer.sendCreatedMessages(createdCol, message.getSourceId());
        }

        if (!deletedCol.isEmpty()) {
            directoryProducer.sendDeletedMessages(deletedCol, message.getSourceId());
        }
    }

    private List<DirectoryDO> deleteTree(SourceMessage message) {
        List<DirectoryDO> list = directoryMapper.selectBySource(message.getSourceId());
        directoryMapper.deleteBySource(message.getSourceId());
        return list;
    }

    private List<DirectoryDO> createTree(SourceMessage message) throws IOException {
        final Long[] idx = {1L}; // lft
        final Integer[] level = {1}; // level
        Long sourceId = message.getSourceId();
        Stack<DirectoryDO> stack = new Stack<>();
        List<DirectoryDO> col = new ArrayList<>();
        Files.walkFileTree(Paths.get(message.getPath()),
                EnumSet.allOf(FileVisitOption.class),
                Integer.MAX_VALUE,
                new SimplePathVisitor(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                DirectoryDO ddo = new DirectoryDO()
                        .setSourceId(sourceId)
                        .setName(dir.getFileName().toString())
                        .setLevel(level[0]++)
                        .setLft(idx[0]++);
                stack.push(ddo);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                DirectoryDO ddo = stack.pop();
                ddo.setRgt(idx[0]++);
                level[0]--;
                col.add(ddo);
                return FileVisitResult.CONTINUE;
            }
        });
        directoryMapper.insertBatch(col);
        return col;
    }

    // 注意，updateTree内部，不得改变createdCol及deleteCol的引用
    private void updateTree(SourceMessage message, final List<DirectoryDO> createdCol, final List<DirectoryDO> deletedCol) throws IOException {
        Path oldPath = Paths.get(message.getOldPath());
        Path newPath = Paths.get(message.getPath());

        // 新源目录和旧的路径根目录是同一个文件夹，无需调整树
        if(FileUtil.toAbsNormal(oldPath).equals(FileUtil.toAbsNormal(newPath))) {
            log.info("新源目录和旧的路径根目录是同一个文件夹，无需调整树");
            return;
        }

        // 新源目录是旧源目录的子孙文件夹，即新的源目录指向了旧源的子文件夹,修剪树
        if (FileUtil.isSub(oldPath, newPath)) {
            // 1. 找到新的根目录在旧树中的节点newRoot
            DirectoryDO newRoot = findNodeByPathInTree(message.getSourceId(), oldPath, newPath);
            if (newRoot != null) {
                log.info("新源目录是旧源目录的子孙文件夹，修剪树");
                // 2. 修剪树
                reduceTree(newRoot, deletedCol);
                return;
            }
        }

        // 新源目录是旧源目录的祖先文件夹，即新的源目录指向了旧源的祖先文件夹，膨胀树
        if (FileUtil.isSub(newPath, oldPath)) {
            // oldRoot
            DirectoryDO oldRoot = directoryMapper.selectOne(DirectoryDO::getSourceId, message.getSourceId(), DirectoryDO::getLevel, 1, DirectoryDO::getLft, 1L);
            if (oldRoot != null) {
                log.info("新源目录是旧源目录的祖先文件夹，膨胀树");
                createdCol.addAll(dilateTree(message.getSourceId(), message.getOldPath(), message.getPath(), oldRoot));
                return;
            }
        }

        // 新源目录是旧源目录无父子关系，删除旧的树，构建新的树
         log.info("新源目录是旧源目录无父子关系，删除旧的树，构建新的树");
        deletedCol.addAll(directoryMapper.selectBySource(message.getSourceId()));
        directoryMapper.deleteBySource(message.getSourceId());
    }

    // 缩小树
    private void reduceTree(DirectoryDO newRoot, final List<DirectoryDO> deletedCol) {
        // 1. 删除所有lft<newRoot并且rgt>newRoot的节点
        deletedCol.addAll(directoryMapper.deleteParentsAndSiblingsBySubTreeRoot(newRoot));

        // 2. 调整新的树的lft和rgt及level
        directoryMapper.reconstructedOffsetByRoot(newRoot.getSourceId(), newRoot.getLft() - 1, newRoot.getLevel() - 1);
    }

    // 在old树中找寻节点
    private DirectoryDO findNodeByPathInTree(Long sourceId, Path oldPath, Path newPath) {
        // 要找的节点所在的level,注意根节点level为1
        Integer level = newPath.getNameCount() - oldPath.getNameCount() + 1; // 目录所在层级
        String nodeName = newPath.getFileName().toString(); // 目录名
        return directoryMapper.selectOne(DirectoryDO::getSourceId, sourceId, DirectoryDO::getLevel, level, DirectoryDO::getName, nodeName);
    }

    // 膨胀树，即旧树的根节点变更为新树的某个子节点，保持旧树中的节点的id不变
    private List<DirectoryDO> dilateTree(Long sourceId, String oldPath, String newDilatePath, DirectoryDO oldRoot) throws IOException {
        Long offset = oldRoot.getRgt() - oldRoot.getLft(); // oldRoot的lft和rgt的差值
        File oldRootFile= FileUtils.getFile(oldPath);

        final Long[] idx = {1L}; // lft
        final Integer[] level = {1}; // level
        final DirectoryDO[] oldRootReplace = {null};

        Stack<DirectoryDO> stack = new Stack<>();
        List<DirectoryDO> col = new ArrayList<>();

        // 1. 构建树，跳过oldRoot及其子树
        Files.walkFileTree(Paths.get(newDilatePath),
                EnumSet.allOf(FileVisitOption.class),
                Integer.MAX_VALUE,
                new SimplePathVisitor(){
                    @SneakyThrows
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                        DirectoryDO ddo = new DirectoryDO()
                                .setSourceId(sourceId)
                                .setName(dir.getFileName().toString())
                                .setLevel(level[0]++)
                                .setLft(idx[0]++);

                        if (Files.isSameFile(dir, oldRootFile.toPath())) {
                            ddo.setRgt(ddo.getLft() + offset);
                            idx[0] = ddo.getRgt() + 1;
                            oldRootReplace[0] = ddo;
                            level[0] -- ;
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        stack.push(ddo);
                        return FileVisitResult.CONTINUE;
                    }
                    @SneakyThrows
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                        DirectoryDO ddo = stack.pop();
                        ddo.setRgt(idx[0]++);
                        level[0]--;
                        col.add(ddo);
                        return FileVisitResult.CONTINUE;
                    }
                });
        // 2. 调整旧树的lft、rgt及level，保持id不变
        directoryMapper.reconstructedOffsetByRoot(sourceId, oldRoot.getLft() - oldRootReplace[0].getLft(), 1 - oldRootReplace[0].getLevel() );

        // 3. 插入新的树
        directoryMapper.insertBatch(col);
        return col;
    }

}
