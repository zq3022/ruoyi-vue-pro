package cn.iocoder.yudao.module.space.controller.admin.directory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 目录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DirectoryPageReqVO extends PageParam {

    @Schema(description = "源编号", example = "3698")
    private Long sourceId;

    @Schema(description = "左索引")
    private Long lft;

    @Schema(description = "右索引")
    private Long rgt;

    @Schema(description = "层级")
    private Integer level;

    @Schema(description = "名称", example = "李四")
    private String name;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}