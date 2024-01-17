package cn.iocoder.yudao.module.cf.dal.dataobject.playlist;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 歌单 DO
 *
 * @author 芋道源码
 */
@TableName("cf_playlist")
@KeySequence("cf_playlist_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 歌单名称
     */
    private String name;
    /**
     * 封面图片编号
     */
    private Integer coverImgId;
    /**
     * 封面图片URL
     */
    private String coverImgUrl;
    /**
     * 广告类型
     */
    private Integer adType;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否允许评论
     */
    private Integer opRecommend;
    /**
     * 是否高质量
     */
    private Integer highQuality;
    /**
     * 是否新创建
     */
    private Integer newImported;
    /**
     * 曲目数量
     */
    private Integer trackCount;
    /**
     * 星级
     */
    private Integer privacy;
    /**
     * 曲目更新次数
     */
    private Integer trackUpdateTime;
    /**
     * 评价线程id
     */
    private Integer commentThreadId;
    /**
     * 播放次数
     */
    private String playCount;
    /**
     * 曲目数量更新次数
     */
    private Integer trackNumberUpdateTime;
    /**
     * 订阅量
     */
    private Integer subscribedCount;
    /**
     * 云曲目数量
     */
    private Integer cloudTrackCount;
    /**
     * 是否排序
     */
    private Integer ordered;
    /**
     * 描述
     */
    private String description;
    /**
     * 标签
     */
    private String tags;
    /**
     * 封面图片ID
     */
    private Integer backgroundCoverId;
    /**
     * 标题图片ID
     */
    private Integer titleImage;
    /**
     * 是否可以被订阅
     */
    private Integer subscribed;
    /**
     * 分享次数
     */
    private Integer shareCount;
    /**
     * 评论次数
     */
    private Integer commentCount;
    /**
     * 歌单类型(5:用户喜欢的，20：网易创建的，0：用户自己创建的)
     */
    private Integer specialType;

}