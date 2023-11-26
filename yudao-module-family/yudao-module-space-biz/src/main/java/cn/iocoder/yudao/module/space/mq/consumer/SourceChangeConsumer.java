package cn.iocoder.yudao.module.space.mq.consumer;

import cn.iocoder.yudao.module.space.mq.message.source.SourceChangeMessage;
import cn.iocoder.yudao.module.space.service.directory.DirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RocketMQMessageListener(
        topic = SourceChangeMessage.TOPIC,
        consumerGroup = SourceChangeMessage.TOPIC + "_CONSUMER"
)
@Slf4j
public class SourceChangeConsumer implements RocketMQListener<SourceChangeMessage> {
    @Resource
    private DirectoryService directoryService;

    @Override
    public void onMessage(SourceChangeMessage message) {
        log.info("[onMessage][源目录变更消息{}]", message);
        directoryService.doSourceChange(message);
    }
}
