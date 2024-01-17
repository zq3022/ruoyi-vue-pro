package cn.iocoder.yudao.module.cf.controller.admin.album.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 专辑 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AlbumRespVO {

    @Schema(description = "专辑di", requiredMode = Schema.RequiredMode.REQUIRED, example = "26560")
    @ExcelProperty("专辑di")
    private Long id;

    @Schema(description = "专辑名称", example = "李四")
    @ExcelProperty("专辑名称")
    private String name;

    @Schema(description = "专辑封面", example = "https://www.iocoder.cn")
    @ExcelProperty("专辑封面")
    private String picUrl;

    @Schema(description = "封面id")
    @ExcelProperty("封面id")
    private Long pic;

    @Schema(description = "alisa")
    @ExcelProperty("alisa")
    private String alia;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "发行公司")
    @ExcelProperty("发行公司")
    private String company;

    @Schema(description = "专辑介绍", example = "随便")
    @ExcelProperty("专辑介绍")
    private String description;

    @Schema(description = "评论条数", example = "25379")
    @ExcelProperty("评论条数")
    private Integer commentCount;

    @Schema(description = "点赞数", example = "16508")
    @ExcelProperty("点赞数")
    private Integer likedCount;

    @Schema(description = "分享数", example = "6371")
    @ExcelProperty("分享数")
    private Integer shareCount;

    @Schema(description = "曲目数量", example = "5354")
    @ExcelProperty("曲目数量")
    private Integer songCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}