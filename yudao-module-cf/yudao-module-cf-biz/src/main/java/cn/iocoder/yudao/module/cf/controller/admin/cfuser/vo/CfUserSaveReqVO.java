package cn.iocoder.yudao.module.cf.controller.admin.cfuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - cf用户新增/修改 Request VO")
@Data
public class CfUserSaveReqVO {

    @Schema(description = "歌曲id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30755")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "3992")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    @Schema(description = "用户类型", example = "2")
    private Integer userType;

    @Schema(description = "用户昵称", example = "芋艿")
    private String nickname;

    @Schema(description = "用户头像", example = "https://www.iocoder.cn")
    private String avatarUrl;

    @Schema(description = "背景图片URL", example = "https://www.iocoder.cn")
    private String backgroundUrl;

    @Schema(description = "用户签名")
    private String signature;

    @Schema(description = "用户名称", example = "王五")
    private String userName;

    @Schema(description = "账户类型", example = "2")
    private Integer accountType;

    @Schema(description = "用户简称", example = "王五")
    private String shortUserName;

    @Schema(description = "生日")
    private LocalDateTime birthday;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "账号状态", example = "1")
    private Integer accountStatus;

    @Schema(description = "省份")
    private Integer province;

    @Schema(description = "城市")
    private Integer city;

    @Schema(description = "授权状态", example = "2")
    private Integer authStatus;

    @Schema(description = "描述", example = "你猜")
    private String description;

    @Schema(description = "默认头像")
    private Integer defaultAvatar;

    @Schema(description = "dj状态", example = "1")
    private Integer djStatus;

    @Schema(description = "本地状态", example = "1")
    private Integer locationStatus;

    @Schema(description = "vip类型", example = "2")
    private Integer vipType;

    @Schema(description = "粉丝数")
    private Integer followed;

    @Schema(description = "点赞数")
    private Integer mutual;

    @Schema(description = "最近登录时间")
    private LocalDateTime lastLoginTime;

    @Schema(description = "最近登录ip")
    private String lastLoginIp;

    @Schema(description = "vip类型乐观锁")
    private Long viptypeVersion;

}