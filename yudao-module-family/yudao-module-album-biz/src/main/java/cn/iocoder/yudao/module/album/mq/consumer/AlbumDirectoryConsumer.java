package cn.iocoder.yudao.module.album.mq.consumer;

import cn.iocoder.yudao.module.album.service.photo.PhotoService;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听相册目录变更的消息
 * topic 格式：ALBUM_DIRECTORY_MESSAGE_1, 其中1代表"相册"类型
 * @link {SourceTypeEnum#ALBUM}
 */
@Component
@RocketMQMessageListener(
    topic = DirectoryMessage.TOPIC_PREFIX + "1",
    consumerGroup = DirectoryMessage.TOPIC_PREFIX + "_CONSUMER",
    consumeMode = ConsumeMode.ORDERLY
)
@Slf4j
public class AlbumDirectoryConsumer implements RocketMQListener<DirectoryMessage> {
    @Resource
    private PhotoService photoService;

    /**
     * 处理目录变更的有序消息
     * 目录消息,只有两种类型: 1. 目录创建 2. 目录删除
     * 如果是目录创建, 则需要重新扫描目录下的照片文件入库,并建立缓存
     * 如果是目录删除, 则需要删除库中的记录并删除缓存
     * 业务上需要做到幂等
     * @param message
     */
    @Override
    @SneakyThrows
    public void onMessage(DirectoryMessage message) {
        log.info("[onMessage][目录,{}]", message);
        photoService.doAlbumDirectoryMessage(message);
    }
}
