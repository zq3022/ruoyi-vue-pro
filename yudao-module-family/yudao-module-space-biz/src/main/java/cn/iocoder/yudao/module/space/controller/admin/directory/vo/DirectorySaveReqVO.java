package cn.iocoder.yudao.module.space.controller.admin.directory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 目录新增/修改 Request VO")
@Data
public class DirectorySaveReqVO {

    @Schema(description = "目录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "5778")
    private Long id;

    @Schema(description = "源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "3698")
    @NotNull(message = "源编号不能为空")
    private Long sourceId;

    @Schema(description = "左索引", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long lft;

    @Schema(description = "右索引", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long rgt;

    @Schema(description = "层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "层级不能为空")
    private Integer level;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "名称不能为空")
    private String name;

}
