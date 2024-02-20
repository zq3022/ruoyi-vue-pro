package cn.iocoder.yudao.module.cf.controller.app.playlist.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "app - 歌单详情 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppCfPlaylistDetailRespVO {
    private AppCfPlaylistRespVO playlist;
    private Integer code;
}
