package cn.iocoder.yudao.module.cf.dal.dataobject.album;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 专辑 DO
 *
 * @author 芋道源码
 */
@TableName("cf_album")
@KeySequence("cf_album_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDO extends BaseDO {

    /**
     * 专辑di
     */
    @TableId
    private Long id;
    /**
     * 专辑名称
     */
    private String name;
    /**
     * 专辑封面
     */
    private String picUrl;
    /**
     * 封面id
     */
    private Long pic;
    /**
     * alisa
     */
    private String alia;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 发行公司
     */
    private String company;
    /**
     * 专辑介绍
     */
    private String description;
    /**
     * 评论条数
     */
    private Integer commentCount;
    /**
     * 点赞数
     */
    private Integer likedCount;
    /**
     * 分享数
     */
    private Integer shareCount;
    /**
     * 曲目数量
     */
    private Integer songCount;

}