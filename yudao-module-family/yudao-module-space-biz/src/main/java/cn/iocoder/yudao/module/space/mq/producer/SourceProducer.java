package cn.iocoder.yudao.module.space.mq.producer;

import cn.iocoder.yudao.module.space.dal.redis.no.MessageNoRedisDAO;
import cn.iocoder.yudao.module.space.enums.MessageTypeEnum;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static cn.iocoder.yudao.module.space.mq.message.source.SourceMessage.SOURCE_NO_PREFIX;

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
        message.setMessageType(MessageTypeEnum.ADD.getValue());
        message.setNo(messageNoRedisDAO.generate(SOURCE_NO_PREFIX));
        rocketMQTemplate.syncSend(SourceMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送源目录新增的消息
    }

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录删除的消息
     */
    public void sendSourceDeleteMessage(SourceMessage message) {
        message.setMessageType(MessageTypeEnum.DELETE.getValue());
        message.setNo(messageNoRedisDAO.generate(SOURCE_NO_PREFIX));
        rocketMQTemplate.syncSend(SourceMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送源目录删除的消息
    }

    /**
     * 发送 {@link SourceMessage} 消息
     *
     * @param message 源目录更新的消息
     */
    public void sendSourceUpdateMessage(SourceMessage message) {
        message.setMessageType(MessageTypeEnum.UPDATE.getValue());
        message.setNo(messageNoRedisDAO.generate(SOURCE_NO_PREFIX));
        rocketMQTemplate.syncSend(SourceMessage.TOPIC, message); // 重点：使用 RocketMQTemplate 同步发送源目录变更的消息
    }
}
