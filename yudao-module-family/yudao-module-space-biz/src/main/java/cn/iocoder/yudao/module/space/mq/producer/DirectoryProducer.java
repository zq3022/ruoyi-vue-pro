package cn.iocoder.yudao.module.space.mq.producer;

import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.module.space.dal.redis.no.MessageNoRedisDAO;
import cn.iocoder.yudao.module.space.enums.MessageTypeEnum;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import com.alibaba.excel.util.StringUtils;
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

    @Resource
    private MessageNoRedisDAO messageNoRedisDAO;

    /**
     * 发送 {@link DirectoryMessage} 消息
     *
     * @param createdCol 新增的目录集合
     * @param sourceId 源目录id
     */
    public void sendCreatedMessages(List<DirectoryDO> createdCol, String sourceId) {
        sendDirectoryMessage(createdCol, sourceId, MessageTypeEnum.ADD.getValue());
    }

    /**
     * 发送 {@link DirectoryMessage} 消息
     *
     * @param deletedCol 新增的目录集合
     * @param sourceId 源目录id
     */
    public void sendDeletedMessages(List<DirectoryDO> deletedCol, String sourceId) {
        sendDirectoryMessage(deletedCol, sourceId, MessageTypeEnum.DELETE.getValue());
    }

    private void sendDirectoryMessage(List<DirectoryDO> deletedCol, String sourceId, Integer messageType) {
        if (deletedCol.isEmpty() || StringUtils.isBlank(sourceId)) {
            return;
        }
        List<Message<DirectoryMessage>> messages = deletedCol.stream()
                .map(d -> {
                    DirectoryMessage payload = new DirectoryMessage()
                            .setMessageType(messageType)
                            .setNo(messageNoRedisDAO.generateDirectoryMessageNo())
                            .setDirectoryId(d.getId());
                    Long tenantId = TenantContextHolder.getTenantId();
                    return MessageBuilder.withPayload(payload).setHeader(HEADER_TENANT_ID, tenantId.toString()).build();
                })
                .collect(Collectors.toList());
        rocketMQTemplate.syncSendOrderly(DirectoryMessage.TOPIC, messages, sourceId);
//        rocketMQTemplate.syncSend(DirectoryMessage.TOPIC, messages); // 重点：使用 RocketMQTemplate 同步发送源目录新增的消息
    }
}
