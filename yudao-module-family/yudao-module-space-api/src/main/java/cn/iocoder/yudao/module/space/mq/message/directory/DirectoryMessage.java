package cn.iocoder.yudao.module.space.mq.message.directory;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DirectoryMessage {
    public static final String TOPIC_PREFIX = "DIRECTORY_MESSAGE_";
    public static final String MESSAGE_NO_PREFIX = "SD";  // space directory

    /**
     * 消息类型
     * 枚举 {@link cn.iocoder.yudao.module.space.enums.DictTypeConstants} MESSAGE_MQ_TYPE
     */
    @NotNull(message = "消息类型不能为空")
    private Integer messageType;

//    /**
//     * 目录消息序号
//     */
//    @NotBlank(message = "源消息序号不能为空")
//    private String no;

    /**
     * 目录消息id
     */
    @NotNull(message = "目录编号不能为空")
    private Long directoryId;

}
