package cn.iocoder.yudao.module.space.service.directory;

import cn.iocoder.yudao.module.space.dal.redis.no.MessageNoRedisDAO;
import cn.iocoder.yudao.module.space.enums.MessageTypeEnum;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import com.alibaba.druid.util.StringUtils;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.io.File;

/**
 * 目录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class DirectoryFilesServiceImpl implements DirectoryFilesService {

    @Resource
    private DirectoryService directoryService;

    @Resource
    private MessageNoRedisDAO messageNoRedisDAO;

    /**
     * 处理源变更导致的目录变更，需要重新扫码目录下的文件
     *
     * @param message 目录变更 消息
     */
    @Override
    public void doDirectoryMessage(DirectoryMessage message) {
        String messageTempNo = messageNoRedisDAO.generateDirectoryMessageNo();
        messageNoRedisDAO.setDirectoryScanning(message.getDirectoryId(), messageTempNo);

        if (!isCurrentMessageScanningDirectory(message.getDirectoryId(), messageTempNo)) {
            return ;
        }

        // TODO
        log.info("处理目录的消息,{}", message.getDirectoryId());

        switch (MessageTypeEnum.valueOf(message.getMessageType())) {
            case ADD: // 目录新增
                scanFilesInDirectory(message.getDirectoryId());
                break;
            case DELETE:
                // 删除目录
//                deletedCol = deleteTree(message);
                break;
        }

    }

    private boolean isCurrentMessageScanningDirectory(Long directoryId, String messageTempNo) {
        String currentDirectoryScanning = messageNoRedisDAO.getDirectoryScanning(directoryId);
        return StringUtils.equals(currentDirectoryScanning, messageTempNo);
    }

    @SneakyThrows
    public void scanFilesInDirectory(Long directoryId) {
        // 1. 获得目录
        File file = new File(directoryService.getFullPath(directoryId));
        if (!file.exists() && !file.isDirectory()) {
            return;
        }
        for (File listFile : file.listFiles()) {
            Metadata metadata = ImageMetadataReader.readMetadata(listFile);
            log.info("listFile: {}", listFile.getName());
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    String tagName = tag.getTagName();
                    String tagDesc = tag.getDescription();
                    log.info("tagName: {}, tagDesc: {}", tagName, tagDesc);
                    if (StringUtils.equals(tagName, "Image Description")) {
                        // 图片描述
                        log.info("图片描述: {}", tagDesc);
                    }
                }
            }
        }

//        JpegImageMetadata metadata = (JpegImageMetadata)Imaging.getMetadata(file);
//         metadata.getExif().getFieldValue(ExifTagConstants.EXIF_TAG_ISO);
////        ExifTagConstants
//        TiffImageMetadata exif = metadata.getExif();
//        if(exif == null) {
//            return;
//        }
//
//        exif.getFieldValue(ExifTagConstants.EXif_TAG_IMAGE_DESCRIPTION);
//        short[] exifTagIso = exif.getFieldValue(ExifTagConstants.EXIF_TAG_ISO);
//
//
//
//        DirectoryDO directory = directoryMapper.selectById(directoryId);
//        if (directory == null) {
//            return;
//        }
//        if (directory.getType() == 1) {
//            // 1.1 目录类型为 1 的，直接扫描目录下的文件
//            scanFilesInDirectory(directory.getSourceId());
//        } else {
//            // 1.2 目录类型为 2 的，扫描目录下的文件
//            scanFilesInDirectory(directoryId);
//        }
    }

}
