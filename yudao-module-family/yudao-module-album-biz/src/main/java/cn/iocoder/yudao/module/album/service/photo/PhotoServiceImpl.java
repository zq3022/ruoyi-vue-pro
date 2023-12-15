package cn.iocoder.yudao.module.album.service.photo;

import cn.iocoder.yudao.module.album.convert.photo.AlbumPhotoConvert;
import cn.iocoder.yudao.module.album.dal.redis.directory.DirectoryRedisDAO;
import cn.iocoder.yudao.module.space.api.directory.DirectoryApi;
import cn.iocoder.yudao.module.space.enums.MessageTypeEnum;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.album.controller.admin.photo.vo.*;
import cn.iocoder.yudao.module.album.dal.dataobject.photo.PhotoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.album.dal.mysql.photo.PhotoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.album.enums.ErrorCodeConstants.*;

/**
 * 相册照片 Service 实现类
 *
 * @author koiup
 */
@Service
@Validated
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    @Resource
    private PhotoMapper photoMapper;

    @Resource
    private DirectoryApi directoryApi;

    @Resource
    private DirectoryRedisDAO directoryRedisDAO;

    @Override
    public Long createPhoto(PhotoSaveReqVO createReqVO) {
        // 插入
        PhotoDO photo = BeanUtils.toBean(createReqVO, PhotoDO.class);
        photoMapper.insert(photo);
        // 返回
        return photo.getId();
    }

    @Override
    public void updatePhoto(PhotoSaveReqVO updateReqVO) {
        // 校验存在
        validatePhotoExists(updateReqVO.getId());
        // 更新
        PhotoDO updateObj = BeanUtils.toBean(updateReqVO, PhotoDO.class);
        photoMapper.updateById(updateObj);
    }

    @Override
    public void deletePhoto(Long id) {
        // 校验存在
        validatePhotoExists(id);
        // 删除
        photoMapper.deleteById(id);
    }

    private void validatePhotoExists(Long id) {
        if (photoMapper.selectById(id) == null) {
            throw exception(PHOTO_NOT_EXISTS);
        }
    }

    @Override
    public PhotoDO getPhoto(Long id) {
        return photoMapper.selectById(id);
    }

    @Override
    public PageResult<PhotoDO> getPhotoPage(PhotoPageReqVO pageReqVO) {
        return photoMapper.selectPage(pageReqVO);
    }


    /**
     * 处理源变更导致的相册目录变更，需要重新扫码目录下的照片
     *
     * @param message 目录变更 消息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAlbumDirectoryMessage(DirectoryMessage message) {
        // 幂等性处理,防止同一个文件夹的同样操作的消息重复消费
        if (directoryRedisDAO.hasDirectoryOperated(message.getDirectoryId(), String.valueOf(message.getMessageType()))) {
            return ;
        }

        log.info("处理目录的消息,{}", message.getDirectoryId());
        switch (MessageTypeEnum.valueOf(message.getMessageType())) {
            case ADD: // 目录新增,扫描照片文件
                scanPhotosInDirectory(message.getDirectoryId());
                break;
            case DELETE:
                // 删除目录
                deletePhotosInDirectory(message.getDirectoryId());
                break;
        }

        directoryRedisDAO.setDirectoryOperated(message.getDirectoryId(), message.getMessageType());
    }

    @SneakyThrows
    public void scanPhotosInDirectory(Long directoryId) {
        long curr = System.currentTimeMillis();
        // 获得目录的全路径
        String fullPath = directoryApi.getFullPath(directoryId);
        log.info("[scanPhotosInDirectory]{}[扫描目录({})的照片]", directoryId, fullPath);
        // 1. 获得目录
        File file = new File(fullPath);
        if (!file.exists() || !file.isDirectory()) {
//            log.error("[scanPhotosInDirectory][扫描目录({})的照片失败，原因是目录不存在]", directoryId);
            return;
        }
        List<PhotoDO> col = new ArrayList<>();
        Files.walkFileTree(file.toPath(),
                Arrays.stream(FileVisitOption.values()).collect(Collectors.toSet()),
                1,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFileFailed(Path path, IOException exc) {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                            throws IOException {
                        if (path == null) {
                            return FileVisitResult.CONTINUE;
                        }
//                        log.info("检测文件是否为图片path: {}", path);
                        // 检测文件是否为图片
                        PhotoDO photoDO = processImage(path);
//                        log.info("        文件{}{},{}", path,  photoDO == null ? "不是图片" : "是图片", photoDO);
                        if (photoDO!= null) {
                            photoDO.setDirectoryId(directoryId);
                            col.add(photoDO);
                        }
                        return FileVisitResult.CONTINUE;
                    }

                });
        photoMapper.insertBatch(col);
        log.info("[scanPhotosInDirectory end]{}", curr - System.currentTimeMillis());
    }

    private PhotoDO processImage(Path path) {
//        log.info("processImage:{}", path);
        File file = path.toFile();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            if (metadata == null) {
//                log.info("metadata is null");
                return null;
            }
            PhotoDO photoDO = AlbumPhotoConvert.convert(metadata);
//            log.info("convert to photoDO:{}", photoDO);
            return photoDO;
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
        return null;
    }

    private void deletePhotosInDirectory(Long directoryId) {
        photoMapper.deleteByDirectoryId(directoryId);
    }


}
