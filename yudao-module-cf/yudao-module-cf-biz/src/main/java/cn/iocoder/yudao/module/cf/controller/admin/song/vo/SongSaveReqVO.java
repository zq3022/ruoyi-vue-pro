package cn.iocoder.yudao.module.cf.controller.admin.song.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 歌曲新增/修改 Request VO")
@Data
public class SongSaveReqVO {

    @Schema(description = "歌曲id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13192")
    private Long id;

    @Schema(description = "专辑id", example = "4814")
    private Long albumId;

    @Schema(description = "歌曲名", example = "赵六")
    private String name;

    @Schema(description = "pst")
    private Integer pst;

    @Schema(description = "t")
    private Integer t;

    @Schema(description = "pop")
    private Integer pop;

    @Schema(description = "st")
    private Integer st;

    @Schema(description = "ft")
    private String rt;

    @Schema(description = "fee")
    private Integer fee;

    @Schema(description = "v")
    private Integer v;

    @Schema(description = "cf")
    private String cf;

    @Schema(description = "dt")
    private Integer dt;

    @Schema(description = "cd")
    private String cd;

    @Schema(description = "no")
    private Integer no;

    @Schema(description = "ftype", example = "1")
    private Integer ftype;

    @Schema(description = "djId", example = "3969")
    private Integer djId;

    @Schema(description = "copyright")
    private Integer copyright;

    @Schema(description = "sId", example = "31706")
    private Integer sId;

    @Schema(description = "mark")
    private Integer mark;

    @Schema(description = "originCoverType", example = "1")
    private Integer originCoverType;

    @Schema(description = "single")
    private Integer single;

    @Schema(description = "mst")
    private Integer mst;

    @Schema(description = "cp")
    private Integer cp;

    @Schema(description = "mv")
    private Integer mv;

    @Schema(description = "rtype", example = "2")
    private Integer rtype;

    @Schema(description = "publishTime")
    private LocalDateTime publishTime;

}