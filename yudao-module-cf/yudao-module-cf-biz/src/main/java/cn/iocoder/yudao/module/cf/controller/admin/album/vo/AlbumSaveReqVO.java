package cn.iocoder.yudao.module.cf.controller.admin.album.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 专辑新增/修改 Request VO")
@Data
public class AlbumSaveReqVO {

    @Schema(description = "专辑di", requiredMode = Schema.RequiredMode.REQUIRED, example = "26560")
    private Long id;

    @Schema(description = "专辑名称", example = "李四")
    private String name;

    @Schema(description = "专辑封面", example = "https://www.iocoder.cn")
    private String picUrl;

    @Schema(description = "封面id")
    private Long pic;

    @Schema(description = "alisa")
    private String alia;

    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

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

}