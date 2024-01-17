package cn.iocoder.yudao.module.cf.controller.admin.album.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 专辑分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlbumPageReqVO extends PageParam {

    @Schema(description = "专辑名称", example = "李四")
    private String name;

    @Schema(description = "专辑封面", example = "https://www.iocoder.cn")
    private String picUrl;

    @Schema(description = "封面id")
    private Long pic;

    @Schema(description = "alisa")
    private String alia;

    @Schema(description = "发布时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] publishTime;

    @Schema(description = "发行公司")
    private String company;

    @Schema(description = "专辑介绍", example = "随便")
    private String description;

    @Schema(description = "评论条数", example = "25379")
    private Integer commentCount;

    @Schema(description = "点赞数", example = "16508")
    private Integer likedCount;

    @Schema(description = "分享数", example = "6371")
    private Integer shareCount;

    @Schema(description = "曲目数量", example = "5354")
    private Integer songCount;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
