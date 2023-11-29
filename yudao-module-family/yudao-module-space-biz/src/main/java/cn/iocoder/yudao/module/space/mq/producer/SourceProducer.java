package cn.iocoder.yudao.module.space.mq.producer;

import cn.iocoder.yudao.module.space.enums.MessageTypeEnum;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SourceProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate; // 重点：注入 RocketMQTemplate 对象

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录新增的消息
     */
    public void sendSourceAddMessage(SourceMessage message) {
        message.setMessageType(MessageTypeEnum.ADD.getValue());
        rocketMQTemplate.syncSend(SourceMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送源目录新增的消息
    }

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录删除的消息
     */
    public void sendSourceDeleteMessage(SourceMessage message) {
        message.setMessageType(MessageTypeEnum.DELETE.getValue());
        rocketMQTemplate.syncSend(SourceMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送源目录删除的消息
    }

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录更新的消息
     */
    public void sendSourceUpdateMessage(SourceMessage message) {
        message.setMessageType(MessageTypeEnum.UPDATE.getValue());
        rocketMQTemplate.syncSend(SourceMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送源目录变更的消息
    }
}
