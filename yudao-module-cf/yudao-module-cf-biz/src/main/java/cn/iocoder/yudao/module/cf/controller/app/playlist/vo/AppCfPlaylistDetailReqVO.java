package cn.iocoder.yudao.module.cf.controller.app.playlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Schema(description = "APP - 歌单详情 Request VO")
@Data
@ToString(callSuper = true)
public class AppCfPlaylistDetailReqVO implements Serializable {

    @Schema(description = "歌单id", example = "28458")
    private Long id;

    @Schema(description = "最近收藏", example = "5")
    private Integer s;


}
