package cn.iocoder.yudao.module.cf.controller.admin.song.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 歌曲 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SongRespVO {

    @Schema(description = "歌曲id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13192")
    @ExcelProperty("歌曲id")
    private Long id;

    @Schema(description = "专辑id", example = "4814")
    @ExcelProperty("专辑id")
    private Long albumId;

    @Schema(description = "歌曲名", example = "赵六")
    @ExcelProperty("歌曲名")
    private String name;

    @Schema(description = "pst")
    @ExcelProperty("pst")
    private Integer pst;

    @Schema(description = "t")
    @ExcelProperty("t")
    private Integer t;

    @Schema(description = "pop")
    @ExcelProperty("pop")
    private Integer pop;

    @Schema(description = "st")
    @ExcelProperty("st")
    private Integer st;

    @Schema(description = "ft")
    @ExcelProperty("ft")
    private String rt;

    @Schema(description = "fee")
    @ExcelProperty("fee")
    private Integer fee;

    @Schema(description = "v")
    @ExcelProperty("v")
    private Integer v;

    @Schema(description = "cf")
    @ExcelProperty("cf")
    private String cf;

    @Schema(description = "dt")
    @ExcelProperty("dt")
    private Integer dt;

    @Schema(description = "cd")
    @ExcelProperty("cd")
    private String cd;

    @Schema(description = "no")
    @ExcelProperty("no")
    private Integer no;

    @Schema(description = "ftype", example = "1")
    @ExcelProperty("ftype")
    private Integer ftype;

    @Schema(description = "djId", example = "3969")
    @ExcelProperty("djId")
    private Integer djId;

    @Schema(description = "copyright")
    @ExcelProperty("copyright")
    private Integer copyright;

    @Schema(description = "sId", example = "31706")
    @ExcelProperty("sId")
    private Integer sId;

    @Schema(description = "mark")
    @ExcelProperty("mark")
    private Integer mark;

    @Schema(description = "originCoverType", example = "1")
    @ExcelProperty("originCoverType")
    private Integer originCoverType;

    @Schema(description = "single")
    @ExcelProperty("single")
    private Integer single;

    @Schema(description = "mst")
    @ExcelProperty("mst")
    private Integer mst;

    @Schema(description = "cp")
    @ExcelProperty("cp")
    private Integer cp;

    @Schema(description = "mv")
    @ExcelProperty("mv")
    private Integer mv;

    @Schema(description = "rtype", example = "2")
    @ExcelProperty("rtype")
    private Integer rtype;

    @Schema(description = "publishTime")
    @ExcelProperty("publishTime")
    private LocalDateTime publishTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}