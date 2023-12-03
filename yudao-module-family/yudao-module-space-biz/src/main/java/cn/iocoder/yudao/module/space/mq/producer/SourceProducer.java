package cn.iocoder.yudao.module.space.mq.producer;

import cn.iocoder.yudao.module.space.dal.redis.no.MessageNoRedisDAO;
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

    @Resource
    private MessageNoRedisDAO messageNoRedisDAO;

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录新增的消息
     */
    public void sendSourceAddMessage(SourceMessage message) {
        // 重点：使用 RocketMQTemplate 同步发送源目录新增的消息
        sendSourceMessage(message, MessageTypeEnum.ADD.getValue());
    }

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录删除的消息
     */
    public void sendSourceDeleteMessage(SourceMessage message) {
        // 重点：使用 RocketMQTemplate 同步发送源目录删除的消息
        sendSourceMessage(message, MessageTypeEnum.DELETE.getValue());
    }

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录更新的消息
     */
    public void sendSourceUpdateMessage(SourceMessage message) {
        // 重点：使用 RocketMQTemplate 同步发送源目录变更的消息
        sendSourceMessage(message, MessageTypeEnum.UPDATE.getValue());
    }

    private void sendSourceMessage(SourceMessage message, Integer messageType) {
        message.setMessageType(messageType);
        message.setNo(messageNoRedisDAO.generateSourceMessageNo());
        rocketMQTemplate.syncSend(SourceMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送源目录消息
    }
}
