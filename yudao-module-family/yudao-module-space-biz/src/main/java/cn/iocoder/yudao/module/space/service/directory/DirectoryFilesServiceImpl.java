package cn.iocoder.yudao.module.space.service.directory;

import cn.iocoder.yudao.module.space.dal.mysql.directory.DirectoryMapper;
import cn.iocoder.yudao.module.space.dal.redis.no.MessageNoRedisDAO;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

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
    private DirectoryMapper directoryMapper;

    @Resource
    private MessageNoRedisDAO messageNoRedisDAO;

    /**
     * 处理目录变更的消息
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
//        switch (MessageTypeEnum.valueOf(message.getMessageType())) {
//            case ADD: // 目录新增
//                createdCol = createTree(message);
//                break;
//            case DELETE:
//                // 删除目录
//                deletedCol = deleteTree(message);
//                break;
//            case UPDATE:
//                break;
//        }

    }

    private boolean isCurrentMessageScanningDirectory(Long directoryId, String messageTempNo) {
        String currentDirectoryScanning = messageNoRedisDAO.getDirectoryScanning(directoryId);
        return StringUtils.equals(currentDirectoryScanning, messageTempNo);
    }
//
//    public void scanFilesInDirectory(Long directoryId) {
//        AnimatedGifEncoder
//    }

}
