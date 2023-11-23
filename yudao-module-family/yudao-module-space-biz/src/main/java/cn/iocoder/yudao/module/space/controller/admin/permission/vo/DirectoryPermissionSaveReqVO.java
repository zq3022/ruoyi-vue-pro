package cn.iocoder.yudao.module.space.controller.admin.permission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 目录权限新增/修改 Request VO")
@Data
public class DirectoryPermissionSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2859")
    private Long id;

    @Schema(description = "目录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "27010")
    @NotNull(message = "目录编号不能为空")
    private Long directoryId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30085")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "管理")
    private Boolean manage;

    @Schema(description = "可写")
    private Boolean writable;

    @Schema(description = "可读")
    private Boolean readable;

}