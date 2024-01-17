package cn.iocoder.yudao.module.cf.controller.admin.artist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 艺术家 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ArtistRespVO {

    @Schema(description = "歌手id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18793")
    @ExcelProperty("歌手id")
    private Long id;

    @Schema(description = "歌手名", example = "赵六")
    @ExcelProperty("歌手名")
    private String name;

    @Schema(description = "简介")
    @ExcelProperty("简介")
    private String briefDesc;

    @Schema(description = "歌曲数量")
    @ExcelProperty("歌曲数量")
    private Integer musicSize;

    @Schema(description = "专辑数量")
    @ExcelProperty("专辑数量")
    private Integer albumSize;

    @Schema(description = "视频数量")
    @ExcelProperty("视频数量")
    private Integer mvSize;

    @Schema(description = "头像id", example = "31212")
    @ExcelProperty("头像id")
    private Long picId;

    @Schema(description = "头像地址", example = "https://www.iocoder.cn")
    @ExcelProperty("头像地址")
    private String picUrl;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}