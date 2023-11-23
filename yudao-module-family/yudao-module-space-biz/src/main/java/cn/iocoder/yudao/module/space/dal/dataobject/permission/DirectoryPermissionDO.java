package cn.iocoder.yudao.module.space.dal.dataobject.permission;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 目录权限 DO
 *
 * @author 芋道源码
 */
@TableName("space_directory_permission")
@KeySequence("space_directory_permission_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryPermissionDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 目录编号
     */
    private Long directoryId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 管理
     */
    private Boolean manage;
    /**
     * 可写
     */
    private Boolean writable;
    /**
     * 可读
     */
    private Boolean readable;

}