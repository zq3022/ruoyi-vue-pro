package cn.iocoder.yudao.module.cf.service.cfuser;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.cf.controller.admin.cfuser.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cf.dal.mysql.cfuser.CfUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cf.enums.ErrorCodeConstants.*;

/**
 * cf用户 Service 实现类
 *
 * @author koi
 */
@Service
@Validated
public class CfUserServiceImpl implements CfUserService {

    @Resource
    private CfUserMapper cfUserMapper;

    @Override
    public Long createCfUser(CfUserSaveReqVO createReqVO) {
        // 插入
        CfUserDO user = BeanUtils.toBean(createReqVO, CfUserDO.class);
        cfUserMapper.insert(user);
        // 返回
        return user.getId();
    }

    @Override
    public void updateCfUser(CfUserSaveReqVO updateReqVO) {
        // 校验存在
        validateCfUserExists(updateReqVO.getId());
        // 更新
        CfUserDO updateObj = BeanUtils.toBean(updateReqVO, CfUserDO.class);
        cfUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteCfUser(Long id) {
        // 校验存在
        validateCfUserExists(id);
        // 删除
        cfUserMapper.deleteById(id);
    }

    private void validateCfUserExists(Long id) {
        if (cfUserMapper.selectById(id) == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    @Override
    public CfUserDO getCfUser(Long cfUserId) {
        return cfUserMapper.selectById(cfUserId);
    }

    @Override
    public CfUserDO getByUserId(Long userId) {
        return cfUserMapper.selectByUserId(userId);
    }

    @Override
    public PageResult<CfUserDO> getCfUserPage(CfUserPageReqVO pageReqVO) {
        return cfUserMapper.selectPage(pageReqVO);
    }

}
