package cn.iocoder.yudao.module.cf.controller.admin.artist.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 艺术家分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArtistPageReqVO extends PageParam {

    @Schema(description = "歌手名", example = "赵六")
    private String name;

    @Schema(description = "简介")
    private String briefDesc;

    @Schema(description = "歌曲数量")
    private Integer musicSize;

    @Schema(description = "专辑数量")
    private Integer albumSize;

    @Schema(description = "视频数量")
    private Integer mvSize;

    @Schema(description = "头像id", example = "31212")
    private Long picId;

    @Schema(description = "头像地址", example = "https://www.iocoder.cn")
    private String picUrl;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}