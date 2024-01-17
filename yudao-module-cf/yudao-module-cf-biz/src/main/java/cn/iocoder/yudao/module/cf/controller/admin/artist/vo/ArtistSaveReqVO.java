package cn.iocoder.yudao.module.cf.controller.admin.artist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 艺术家新增/修改 Request VO")
@Data
public class ArtistSaveReqVO {

    @Schema(description = "歌手id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18793")
    private Long id;

    @Schema(description = "歌手名", example = "赵六")
    private String name;

    @Schema(description = "简介")
    private String briefDesc;

    @Schema(description = "歌曲数量")
    private Integer musicSize;

    @Schema(description = "专辑数量")
    private Integer albumSize;

    @Schema(description = "视频数量")
    private Integer mvSize;

    @Schema(description = "头像id", example = "31212")
    private Long picId;

    @Schema(description = "头像地址", example = "https://www.iocoder.cn")
    private String picUrl;

}