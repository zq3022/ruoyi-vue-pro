package cn.iocoder.yudao.module.space.controller.admin.source.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 目录源新增/修改 Request VO")
@Data
public class SourceSaveReqVO {

    @Schema(description = "源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "16557")
    private Long id;

    @Schema(description = "目录路径")
    private String path;

    @Schema(description = "源类型", example = "1")
    private Integer type;

}