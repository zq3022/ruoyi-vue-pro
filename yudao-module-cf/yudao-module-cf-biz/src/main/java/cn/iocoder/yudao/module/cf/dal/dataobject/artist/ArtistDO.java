package cn.iocoder.yudao.module.cf.dal.dataobject.artist;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 艺术家 DO
 *
 * @author 芋道源码
 */
@TableName("cf_artist")
@KeySequence("cf_artist_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDO extends BaseDO {

    /**
     * 歌手id
     */
    @TableId
    private Long id;
    /**
     * 歌手名
     */
    private String name;
    /**
     * 简介
     */
    private String briefDesc;
    /**
     * 歌曲数量
     */
    private Integer musicSize;
    /**
     * 专辑数量
     */
    private Integer albumSize;
    /**
     * 视频数量
     */
    private Integer mvSize;
    /**
     * 头像id
     */
    private Long picId;
    /**
     * 头像地址
     */
    private String picUrl;

}