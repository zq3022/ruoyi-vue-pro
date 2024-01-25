package cn.iocoder.yudao.module.cf.service.cfuser;

import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserDetailRespVO;

public interface AppCfUserService {
    AppCfUserDetailRespVO getUserDetail(Long userId);
}
