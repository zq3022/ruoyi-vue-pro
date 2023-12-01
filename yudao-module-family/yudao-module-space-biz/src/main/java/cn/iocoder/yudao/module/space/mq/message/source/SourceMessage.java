package cn.iocoder.yudao.module.space.mq.message.source;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SourceMessage {
    public static final String TOPIC = "SOURCE_MESSAGE";
    public static final String SOURCE_NO_PREFIX = "S";

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

    /**
     * 目录源编号
     */
    @NotNull(message = "源Id不能为空")
    private Long sourceId;

    /**
     * 目录路径
     */
    @NotBlank(message = "目录路径不能为空")
    private String path;

    /**
     * 源类型
     * 枚举 {@link cn.iocoder.yudao.module.space.enums.DictTypeConstants}
     */
    @NotNull(message = "源类型不能为空")
    private Integer type;

    /**
     * 旧的源目录路径（仅用于更新）
     */
    private String oldPath;
}
