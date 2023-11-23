package cn.iocoder.yudao.module.space.controller.admin.permission.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 目录权限分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DirectoryPermissionPageReqVO extends PageParam {

    @Schema(description = "目录编号", example = "27010")
    private Long directoryId;

    @Schema(description = "用户编号", example = "30085")
    private Long userId;

    @Schema(description = "管理")
    private Boolean manage;

    @Schema(description = "可写")
    private Boolean writable;

    @Schema(description = "可读")
    private Boolean readable;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}