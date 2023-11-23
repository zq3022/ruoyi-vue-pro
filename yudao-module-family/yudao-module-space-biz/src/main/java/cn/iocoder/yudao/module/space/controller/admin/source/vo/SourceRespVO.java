package cn.iocoder.yudao.module.space.controller.admin.source.vo;

import cn.iocoder.yudao.module.space.enums.DictTypeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 目录源 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SourceRespVO {

    @Schema(description = "源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "16557")
    @ExcelProperty("源编号")
    private Long id;

    @Schema(description = "目录路径")
    @ExcelProperty("目录路径")
    private String path;

    @Schema(description = "源类型", example = "1")
    @ExcelProperty(value = "源类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.SPACE_SOURCE_TYPE)
    private Integer type;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
