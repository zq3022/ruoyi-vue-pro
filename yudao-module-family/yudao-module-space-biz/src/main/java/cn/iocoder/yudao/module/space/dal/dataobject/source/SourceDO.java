package cn.iocoder.yudao.module.space.dal.dataobject.source;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 目录源 DO
 *
 * @author 芋道源码
 */
@TableName("space_source")
@KeySequence("space_source_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceDO extends BaseDO {

    /**
     * 源编号
     */
    @TableId
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
