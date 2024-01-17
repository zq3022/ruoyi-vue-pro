package cn.iocoder.yudao.module.cf.controller.admin.playlist.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 歌单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaylistPageReqVO extends PageParam {

    @Schema(description = "用户id", example = "28458")
    private Long userId;

    @Schema(description = "歌单名称", example = "王五")
    private String name;

    @Schema(description = "封面图片编号", example = "2403")
    private Integer coverImgId;

    @Schema(description = "封面图片URL", example = "https://www.iocoder.cn")
    private String coverImgUrl;

    @Schema(description = "广告类型", example = "1")
    private Integer adType;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "是否允许评论")
    private Integer opRecommend;

    @Schema(description = "是否高质量")
    private Integer highQuality;

    @Schema(description = "是否新创建")
    private Integer newImported;

    @Schema(description = "曲目数量", example = "10449")
    private Integer trackCount;

    @Schema(description = "星级")
    private Integer privacy;

    @Schema(description = "曲目更新次数")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] trackUpdateTime;

    @Schema(description = "评价线程id", example = "32119")
    private Integer commentThreadId;

    @Schema(description = "播放次数", example = "29270")
    private String playCount;

    @Schema(description = "曲目数量更新次数")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] trackNumberUpdateTime;

    @Schema(description = "订阅量", example = "19804")
    private Integer subscribedCount;

    @Schema(description = "云曲目数量", example = "7694")
    private Integer cloudTrackCount;

    @Schema(description = "是否排序")
    private Integer ordered;

    @Schema(description = "描述", example = "你说的对")
    private String description;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "封面图片ID", example = "31288")
    private Integer backgroundCoverId;

    @Schema(description = "标题图片ID")
    private Integer titleImage;

    @Schema(description = "是否可以被订阅")
    private Integer subscribed;

    @Schema(description = "分享次数", example = "5881")
    private Integer shareCount;

    @Schema(description = "评论次数", example = "20444")
    private Integer commentCount;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "歌单类型(5:用户喜欢的，20：网易创建的，0：用户自己创建的)", example = "2")
    private Integer specialType;

}