package cn.iocoder.yudao.module.space.dal.dataobject.directory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 目录 DO
 *
 * @author 芋道源码
 */
@TableName("space_directory")
@KeySequence("space_directory_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryDO extends BaseDO {

    /**
     * 目录编号
     */
    @TableId
    private Long id;
    /**
     * 源编号
     */
    private Long sourceId;
    /**
     * 左索引
     */
    private Long lft;
    /**
     * 右索引
     */
    private Long rgt;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 名称
     */
    private String name;

}