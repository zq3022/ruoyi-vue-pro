package cn.iocoder.yudao.module.member.controller.app.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "用户 APP - 手机是否已注册 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthMobileExistRespVO {

    @Schema(description = "是否存在", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer exist;

    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "koi")
    private String nickname;

    @Schema(description = "登录是否开启了需要密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean hasPassword;

}
