package cn.iocoder.yudao.module.cf.controller.app.cfuser;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserDetailRespVO;
import cn.iocoder.yudao.module.cf.service.cfuser.AppCfUserService;
//import cn.iocoder.yudao.module.member.convert.user.MemberUserConvert;
//import cn.iocoder.yudao.module.member.dal.dataobject.level.MemberLevelDO;
//import cn.iocoder.yudao.module.member.dal.dataobject.user.MemberUserDO;
//import cn.iocoder.yudao.module.member.service.level.MemberLevelService;
//import cn.iocoder.yudao.module.member.service.user.MemberUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 用户个人中心")
@RestController
@RequestMapping("/cf/user")
@Validated
@Slf4j
public class AppCfUserController {

    @Resource
    private AppCfUserService cfUserService;
//    @Resource
//    private MemberLevelService levelService;

    @GetMapping("/detail")
    @Operation(summary = "获得详细信息")
    @PreAuthenticated
    public CommonResult<AppCfUserDetailRespVO> getUserInfo() {
        AppCfUserDetailRespVO userDetail = cfUserService.getUserDetail(getLoginUserId());
        return success(userDetail);
    }


}

