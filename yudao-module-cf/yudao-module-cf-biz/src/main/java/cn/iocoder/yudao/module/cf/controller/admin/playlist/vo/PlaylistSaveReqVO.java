package cn.iocoder.yudao.module.cf.controller.admin.playlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 歌单新增/修改 Request VO")
@Data
public class PlaylistSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "15708")
    private Long id;

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28458")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @Schema(description = "歌单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "歌单名称不能为空")
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
    private Integer trackUpdateTime;

    @Schema(description = "评价线程id", example = "32119")
    private Integer commentThreadId;

    @Schema(description = "播放次数", example = "29270")
    private String playCount;

    @Schema(description = "曲目数量更新次数")
    private Integer trackNumberUpdateTime;

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

    @Schema(description = "歌单类型(5:用户喜欢的，20：网易创建的，0：用户自己创建的)", example = "2")
    private Integer specialType;

}