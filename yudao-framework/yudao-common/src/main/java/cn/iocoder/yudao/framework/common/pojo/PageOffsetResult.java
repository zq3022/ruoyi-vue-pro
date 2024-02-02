package cn.iocoder.yudao.framework.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "分页结果")
@Data
public final class PageOffsetResult<T> implements Serializable {

    @Schema(description = "数据", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<T> list;

    @Schema(description = "总量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long total;

    @Schema(description = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long nextOffset;

    public PageOffsetResult() {
    }

    public PageOffsetResult(List<T> list, Long total, Long nextOffset) {
        this.list = list;
        this.total = total;
        this.nextOffset = nextOffset;
    }

    public PageOffsetResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
        this.nextOffset = null;
    }

    public static <T> PageOffsetResult<T> empty() {
        return new PageOffsetResult<>(0L);
    }

    public static <T> PageOffsetResult<T> empty(Long total) {
        return new PageOffsetResult<>(total);
    }

}
