package cn.iocoder.yudao.module.cf.controller.app.cfuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "CF用户 APP - 用户详情 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppCfUserDetailRespVO {
    private int level = 0;
    private int listenSongs = 0;
    private AppCfUserPointRespVO userPoint;
    private Boolean mobileSign = false;
    private Boolean pcSign = false;
    private AppCfUserProfileRespVO profile;
    private Boolean peopleCanSeeMyPlayRecord = false;
//    private int bindings;
    private Boolean adValid = false;
    private int code = 0;
    private LocalDateTime createTime;
//    private int createDays = 0;
//    private int profileVillageInfo;
}
