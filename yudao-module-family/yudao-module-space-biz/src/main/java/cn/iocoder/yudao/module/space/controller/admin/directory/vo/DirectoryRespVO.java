package cn.iocoder.yudao.module.space.controller.admin.directory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 目录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DirectoryRespVO {

    @Schema(description = "目录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "5778")
    @ExcelProperty("目录编号")
    private Long id;

    @Schema(description = "源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "3698")
    @ExcelProperty("源编号")
    private Long sourceId;

    @Schema(description = "左索引", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("左索引")
    private Long lft;

    @Schema(description = "右索引", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("右索引")
    private Long rgt;

    @Schema(description = "层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("层级")
    private Integer level;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("名称")
    private String name;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}