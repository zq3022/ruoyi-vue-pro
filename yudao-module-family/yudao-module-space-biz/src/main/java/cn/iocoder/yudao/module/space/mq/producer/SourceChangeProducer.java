package cn.iocoder.yudao.module.space.mq.producer;

import cn.iocoder.yudao.module.space.mq.message.source.SourceChangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SourceChangeProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate; // 重点：注入 RocketMQTemplate 对象

    /**
     * 发送 {@link SourceChangeMessage} 消息
     *
     * @param message 源目录变更的消息
     */
    public void sendSourceChangeMessage(SourceChangeMessage message) {
        rocketMQTemplate.syncSend(SourceChangeMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送消息
    }
}
