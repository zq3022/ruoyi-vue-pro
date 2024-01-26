package cn.iocoder.yudao.module.cf.controller.app.cfuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
//import lombok.NoArgsConstructor;

@Schema(description = "CF用户 APP - 用户积分信息 Response VO")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class AppCfUserPointRespVO {
    private Long userId;
    private Integer balance;
    private LocalDateTime updateTime;
    private Integer version;
    private Integer status;
    private Integer blockBalance;

}
