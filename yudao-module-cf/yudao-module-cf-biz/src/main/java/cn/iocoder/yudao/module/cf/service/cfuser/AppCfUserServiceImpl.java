package cn.iocoder.yudao.module.cf.service.cfuser;


import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserDetailRespVO;
import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserPointRespVO;
import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserProfileRespVO;
import cn.iocoder.yudao.module.cf.converter.CfUserConverter;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import cn.iocoder.yudao.module.member.dal.dataobject.level.MemberLevelDO;
import cn.iocoder.yudao.module.member.dal.dataobject.user.MemberUserDO;
import cn.iocoder.yudao.module.member.service.level.MemberLevelService;
import cn.iocoder.yudao.module.member.service.user.MemberUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;

/**
 * CF User Service 实现类
 *
 * @author Koi
 */
@Service
@Valid
@Slf4j
public class AppCfUserServiceImpl implements AppCfUserService {

    @Resource
    private CfUserService cfUserService;
    @Resource
    private MemberUserService userService;
    @Resource
    private MemberLevelService levelService;

    /**
     * 获取cf user用户详情
     * @param userId
     * @return
     */
    @Override
    public AppCfUserDetailRespVO getUserDetail(Long userId) {
        getLoginUser();
        CfUserDO cfUser = cfUserService.getByUserId(userId);
        MemberUserDO user = userService.getUser(userId);
        MemberLevelDO level = levelService.getLevel(user.getLevelId());
        AppCfUserDetailRespVO resp = new AppCfUserDetailRespVO()
                .setLevel(level.getLevel())
                .setUserPoint(new AppCfUserPointRespVO().setBalance(user.getPoint()).setUserId(userId));
        resp.setProfile(CfUserConverter.INSTANCE.convert(cfUser));
        return resp;
    }
}
