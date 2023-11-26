package cn.iocoder.yudao.module.space.mq.message.source;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SourceChangeMessage {
    public static final String TOPIC = "SOURCE_CHANGE_MESSAGE";

    /**
     * 目录源编号
     */
    @NotNull(message = "源Id不能为空")
    private Long id;

    /**
     * 目录路径
     */
    @NotBlank(message = "目录路径不能为空")
    private String path;

    /**
     * 源类型
     *
     * 枚举 {@link cn.iocoder.yudao.module.space.enums.DictTypeConstants}
     */
    @NotNull(message = "源类型不能为空")
    private Integer type;
}
