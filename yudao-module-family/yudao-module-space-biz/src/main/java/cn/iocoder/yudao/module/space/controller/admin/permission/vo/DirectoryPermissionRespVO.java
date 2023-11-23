package cn.iocoder.yudao.module.space.controller.admin.permission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 目录权限 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DirectoryPermissionRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2859")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "目录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "27010")
    @ExcelProperty("目录编号")
    private Long directoryId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30085")
    @ExcelProperty("用户编号")
    private Long userId;

    @Schema(description = "管理")
    @ExcelProperty("管理")
    private Boolean manage;

    @Schema(description = "可写")
    @ExcelProperty("可写")
    private Boolean writable;

    @Schema(description = "可读")
    @ExcelProperty("可读")
    private Boolean readable;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}