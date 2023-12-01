package cn.iocoder.yudao.module.space.mq.consumer;

import cn.iocoder.yudao.module.space.dal.redis.consumer.SourceConsumerLockRedisDAO;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;
import cn.iocoder.yudao.module.space.service.directory.DirectoryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RocketMQMessageListener(
        topic = SourceMessage.TOPIC,
        consumerGroup = SourceMessage.TOPIC + "_CONSUMER"
)
@Slf4j
public class SourceConsumer implements RocketMQListener<SourceMessage> {
    @Resource
    private DirectoryService directoryService;

    @Resource
    private SourceConsumerLockRedisDAO sourceConsumerLockRedisDAO;

    @Override
    @SneakyThrows
    public void onMessage(SourceMessage message) {
        log.info("[onMessage][源目录消息{}]", message);
        sourceConsumerLockRedisDAO.lock(message.getNo(), 20000L, () -> {
            try {
                directoryService.doSourceMessage(message);
            } catch (Exception e) {
                log.error("[onMessage][执行源目录消息失败，message({}) 异常({})]", message, e);
            }
        });
        directoryService.doSourceMessage(message);
    }
}
