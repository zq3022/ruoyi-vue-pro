package cn.iocoder.yudao.framework.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description="分页参数-偏移量方式")
@Data
public class PageOffsetParam implements Serializable {

    private static final Long OFFSET = 0L;
    private static final Integer LIMIT = 10;

    @Schema(description = "偏移量,一般设置为id值", requiredMode = Schema.RequiredMode.REQUIRED,example = "1")
    @NotNull(message = "偏移量不能为空")
    private Long offset = OFFSET;

    @Schema(description = "每页条数，最大值为 100", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer limit = LIMIT;
}
