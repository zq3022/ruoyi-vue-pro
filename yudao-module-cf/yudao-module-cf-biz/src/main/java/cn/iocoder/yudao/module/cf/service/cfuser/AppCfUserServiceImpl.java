package cn.iocoder.yudao.module.cf.service.cfuser;


import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserDetailRespVO;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
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
    /**
     * 获取cf user用户详情
     * @param userId
     * @return
     */
    @Override
    public AppCfUserDetailRespVO getUserDetail(Long userId) {
        getLoginUser();
        CfUserDO cfUser = cfUserService.getByUserId(userId);
        AppCfUserDetailRespVO resp = new AppCfUserDetailRespVO();
//        .setProfile(cfUser);
        return null;
    }
}
