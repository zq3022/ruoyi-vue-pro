package cn.iocoder.yudao.module.album.mq.consumer;

import cn.iocoder.yudao.module.album.service.photo.PhotoService;
import cn.iocoder.yudao.module.space.api.directory.DirectoryConsumerLockRedisApi;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听相册目录变更的消息
 * topic 格式：ALBUM_DIRECTORY_MESSAGE_1, 其中1代表
 * @link {SourceTypeEnum#ALBUM}
 */
@Component
@RocketMQMessageListener(
    topic = DirectoryMessage.TOPIC_PREFIX + "1",
    consumerGroup = DirectoryMessage.TOPIC_PREFIX + "_CONSUMER"
)
@Slf4j
public class AlbumDirectoryConsumer implements RocketMQListener<DirectoryMessage> {
    @Resource
    private PhotoService photoService;

    @Resource
    private DirectoryConsumerLockRedisApi directoryConsumerLockRedisApi;


    @Override
    @SneakyThrows
    public void onMessage(DirectoryMessage message) {
        log.info("[onMessage][目录,{}]", message);
        directoryConsumerLockRedisApi.lock(message.getNo(), 20000L, () -> {
        try {
            photoService.doAlbumDirectoryMessage(message);
        } catch (Exception e) {
            log.error("[onMessage][执行目录消息失败，message({}) 异常({})]", message, e);
        }
        });
    }
}

