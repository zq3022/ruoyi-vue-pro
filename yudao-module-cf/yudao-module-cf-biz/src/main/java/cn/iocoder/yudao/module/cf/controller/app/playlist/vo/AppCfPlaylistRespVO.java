package cn.iocoder.yudao.module.cf.controller.app.playlist.vo;

import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "app - 歌单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppCfPlaylistRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "15708")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28458")
    @ExcelProperty("用户id")
    private Long userId;

    @Schema(description = "歌单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("歌单名称")
    private String name;

    @Schema(description = "封面图片编号", example = "2403")
    @ExcelProperty("封面图片编号")
    private Integer coverImgId;

    @Schema(description = "封面图片URL", example = "https://www.iocoder.cn")
    @ExcelProperty("封面图片URL")
    private String coverImgUrl;

    @Schema(description = "广告类型", example = "1")
    @ExcelProperty("广告类型")
    private Integer adType;

    @Schema(description = "状态", example = "2")
    @ExcelProperty("状态")
    private Integer status;

    @Schema(description = "是否允许评论")
    @ExcelProperty("是否允许评论")
    private Integer opRecommend;

    @Schema(description = "是否高质量")
    @ExcelProperty("是否高质量")
    private Integer highQuality;

    @Schema(description = "是否新创建")
    @ExcelProperty("是否新创建")
    private Integer newImported;

    @Schema(description = "曲目数量", example = "10449")
    @ExcelProperty("曲目数量")
    private Integer trackCount;

    @Schema(description = "星级")
    @ExcelProperty("星级")
    private Integer privacy;

    @Schema(description = "曲目更新次数")
    @ExcelProperty("曲目更新次数")
    private Integer trackUpdateTime;

    @Schema(description = "评价线程id", example = "32119")
    @ExcelProperty("评价线程id")
    private Integer commentThreadId;

    @Schema(description = "播放次数", example = "29270")
    @ExcelProperty("播放次数")
    private String playCount;

    @Schema(description = "曲目数量更新次数")
    @ExcelProperty("曲目数量更新次数")
    private Integer trackNumberUpdateTime;

    @Schema(description = "订阅量", example = "19804")
    @ExcelProperty("订阅量")
    private Integer subscribedCount;

    @Schema(description = "云曲目数量", example = "7694")
    @ExcelProperty("云曲目数量")
    private Integer cloudTrackCount;

    @Schema(description = "是否排序")
    @ExcelProperty("是否排序")
    private Integer ordered;

    @Schema(description = "描述", example = "你说的对")
    @ExcelProperty("描述")
    private String description;

    @Schema(description = "标签")
    @ExcelProperty("标签")
    private String tags;

    @Schema(description = "封面图片ID", example = "31288")
    @ExcelProperty("封面图片ID")
    private Integer backgroundCoverId;

    @Schema(description = "标题图片ID")
    @ExcelProperty("标题图片ID")
    private Integer titleImage;

    @Schema(description = "是否可以被订阅")
    @ExcelProperty("是否可以被订阅")
    private Integer subscribed;

    @Schema(description = "分享次数", example = "5881")
    @ExcelProperty("分享次数")
    private Integer shareCount;

    @Schema(description = "评论次数", example = "20444")
    @ExcelProperty("评论次数")
    private Integer commentCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "歌单类型(5:用户喜欢的，20：网易创建的，0：用户自己创建的)", example = "2")
    @ExcelProperty("歌单类型(5:用户喜欢的，20：网易创建的，0：用户自己创建的)")
    private Integer specialType;

    @Schema(description = "歌单创建人", example = "2")
    @ExcelProperty("歌单创建人")
    @JsonAlias("creator")
    private CfUserDO playListCreator ;
}
