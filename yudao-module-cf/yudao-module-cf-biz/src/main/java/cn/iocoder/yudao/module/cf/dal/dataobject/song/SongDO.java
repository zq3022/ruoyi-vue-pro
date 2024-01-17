package cn.iocoder.yudao.module.cf.dal.dataobject.song;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 歌曲 DO
 *
 * @author 芋道源码
 */
@TableName("cf_song")
@KeySequence("cf_song_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDO extends BaseDO {

    /**
     * 歌曲id
     */
    @TableId
    private Long id;
    /**
     * 专辑id
     */
    private Long albumId;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * pst
     */
    private Integer pst;
    /**
     * t
     */
    private Integer t;
    /**
     * pop
     */
    private Integer pop;
    /**
     * st
     */
    private Integer st;
    /**
     * ft
     */
    private String rt;
    /**
     * fee
     */
    private Integer fee;
    /**
     * v
     */
    private Integer v;
    /**
     * cf
     */
    private String cf;
    /**
     * dt
     */
    private Integer dt;
    /**
     * cd
     */
    private String cd;
    /**
     * no
     */
    private Integer no;
    /**
     * ftype
     */
    private Integer ftype;
    /**
     * djId
     */
    private Integer djId;
    /**
     * copyright
     */
    private Integer copyright;
    /**
     * sId
     */
    private Integer sId;
    /**
     * mark
     */
    private Integer mark;
    /**
     * originCoverType
     */
    private Integer originCoverType;
    /**
     * single
     */
    private Integer single;
    /**
     * mst
     */
    private Integer mst;
    /**
     * cp
     */
    private Integer cp;
    /**
     * mv
     */
    private Integer mv;
    /**
     * rtype
     */
    private Integer rtype;
    /**
     * publishTime
     */
    private LocalDateTime publishTime;

}