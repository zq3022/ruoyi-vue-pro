package cn.iocoder.yudao.module.space.mq.consumer;

import cn.iocoder.yudao.module.space.dal.redis.consumer.DirectoryConsumerLockRedisDAO;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import cn.iocoder.yudao.module.space.service.directory.DirectoryFilesService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RocketMQMessageListener(
        topic = DirectoryMessage.TOPIC,
        consumerGroup = DirectoryMessage.TOPIC + "_CONSUMER"
)
@Slf4j
public class DirectoryConsumer implements RocketMQListener<DirectoryMessage> {
    @Resource
    private DirectoryFilesService directoryFilesService;

    @Resource
    private DirectoryConsumerLockRedisDAO directoryConsumerLockRedisDAO;

    @Override
    @SneakyThrows
    public void onMessage(DirectoryMessage message) {
        log.info("[onMessage][目录,{}]", message);
        directoryConsumerLockRedisDAO.lock(message.getNo(), 20000L, () -> {
            try {
                directoryFilesService.doDirectoryMessage(message);
            } catch (Exception e) {
                log.error("[onMessage][执行目录消息失败，message({}) 异常({})]", message, e);
            }
        });
    }
}
