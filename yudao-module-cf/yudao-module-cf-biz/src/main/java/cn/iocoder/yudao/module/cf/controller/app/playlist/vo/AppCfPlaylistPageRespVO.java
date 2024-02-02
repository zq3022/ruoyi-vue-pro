package cn.iocoder.yudao.module.cf.controller.app.playlist.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "app - 歌单分页 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppCfPlaylistPageRespVO {
    private String version;
    private Boolean more;
    private List<AppCfPlaylistRespVO> playlist;
    private Integer code;
}
