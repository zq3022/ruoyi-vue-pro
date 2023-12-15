package cn.iocoder.yudao.module.space.mq.producer;

import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.module.space.enums.MessageTypeEnum;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.HEADER_TENANT_ID;

@Component
@Slf4j
public class DirectoryProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate; // 重点：注入 RocketMQTemplate 对象

    /**
     * 发送 {@link DirectoryMessage} 消息
     *
     * @param createdCol 新增的目录集合
     * @param sourceId 源目录id
     */
    public void sendCreatedMessages(List<DirectoryDO> createdCol, Long sourceId, Integer sourceType) {
        sendDirectoryMessage(createdCol, sourceId, MessageTypeEnum.ADD.getValue(), sourceType);
    }

    /**
     * 发送 {@link DirectoryMessage} 消息
     *
     * @param deletedCol 新增的目录集合
     * @param sourceId 源目录id
     */
    public void sendDeletedMessages(List<DirectoryDO> deletedCol, Long sourceId, Integer sourceType) {
        sendDirectoryMessage(deletedCol, sourceId, MessageTypeEnum.DELETE.getValue(), sourceType);
    }

    private void sendDirectoryMessage(List<DirectoryDO> deletedCol, Long sourceId, Integer messageType, Integer sourceType) {
        if (deletedCol.isEmpty() || sourceId == null) {
            return;
        }
        String topic = DirectoryMessage.TOPIC_PREFIX + sourceType;
        List<Message<DirectoryMessage>> messages = deletedCol.stream()
                .map(d -> {
                    DirectoryMessage payload = new DirectoryMessage()
                            .setMessageType(messageType)
                            .setDirectoryId(d.getId());
                    Long tenantId = TenantContextHolder.getTenantId();
                    return MessageBuilder.withPayload(payload).setHeader(HEADER_TENANT_ID, tenantId.toString()).build();
                })
                .collect(Collectors.toList());
        // 重点：使用 RocketMQTemplate 同步发送目录的有序消息,有序消息按目录源id hash
        rocketMQTemplate.syncSendOrderly(topic, messages, String.valueOf(sourceId));
    }
}
