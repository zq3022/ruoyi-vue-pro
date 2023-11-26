package cn.iocoder.yudao.module.space.service.directory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectoryPageReqVO;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectorySaveReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.module.space.dal.mysql.directory.DirectoryMapper;
import cn.iocoder.yudao.module.space.mq.message.source.SourceChangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

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

    /**
     * 重新构建源下面的目录
     *
     * @param message 源目录
     * @return
     */
    @Override
    public void doSourceChange(SourceChangeMessage message) {
        log.info("重新构建源下面的目录结构,{}",message.getId());
        // 获取源信息

        // 保存源目录为根目录
        // 开始构建目录

    }
}
