package cn.iocoder.yudao.module.cf.controller.admin.song.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 歌曲分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SongPageReqVO extends PageParam {

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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] publishTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}