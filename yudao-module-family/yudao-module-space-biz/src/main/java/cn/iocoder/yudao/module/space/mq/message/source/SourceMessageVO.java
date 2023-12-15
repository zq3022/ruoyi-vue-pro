package cn.iocoder.yudao.module.space.mq.message.source;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceMessageVO implements Serializable {
    /**
     * 源编号
     */
    private Long id;
    /**
     * 目录路径
     */
    private String path;
    /**
     * 源类型
     *
     * 枚举 {@link cn.iocoder.yudao.module.space.enums.DictTypeConstants}
     */
    private Integer type;
}
