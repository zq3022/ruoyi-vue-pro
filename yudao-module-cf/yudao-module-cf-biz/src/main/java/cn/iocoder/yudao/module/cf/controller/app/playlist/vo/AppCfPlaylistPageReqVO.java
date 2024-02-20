package cn.iocoder.yudao.module.cf.controller.app.playlist.vo;

import cn.iocoder.yudao.framework.common.pojo.PageOffsetParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "APP - 歌单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppCfPlaylistPageReqVO extends PageOffsetParam {
}
