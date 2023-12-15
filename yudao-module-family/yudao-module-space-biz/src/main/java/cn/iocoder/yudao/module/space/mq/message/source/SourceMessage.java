package cn.iocoder.yudao.module.space.mq.message.source;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SourceMessage {
    public static final String TOPIC = "SOURCE_MESSAGE";
    public static final String MESSAGE_NO_PREFIX = "SS"; // space source

    /**
     * 消息类型
     * 枚举 {@link cn.iocoder.yudao.module.space.enums.DictTypeConstants} MESSAGE_MQ_TYPE
     */
    @NotNull(message = "消息类型不能为空")
    private Integer messageType;

    /**
     * 目录源消息序号
     */
    @NotBlank(message = "源消息序号不能为空")
    private String no;

    // 新建时,指新创建的source
    // 修改时,指修改之后的source
    private SourceMessageVO source;

    // 删除时,指被删除的source
    // 修改时,指修改前的source
    private SourceMessageVO oldSource;

}
