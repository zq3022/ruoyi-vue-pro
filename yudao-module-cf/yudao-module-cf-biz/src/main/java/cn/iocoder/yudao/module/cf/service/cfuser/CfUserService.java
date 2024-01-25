package cn.iocoder.yudao.module.cf.service.cfuser;

import javax.validation.*;
import cn.iocoder.yudao.module.cf.controller.admin.cfuser.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * cf用户 Service 接口
 *
 * @author koi
 */
public interface CfUserService {

    /**
     * 创建cf用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCfUser(@Valid CfUserSaveReqVO createReqVO);

    /**
     * 更新cf用户
     *
     * @param updateReqVO 更新信息
     */
    void updateCfUser(@Valid CfUserSaveReqVO updateReqVO);

    /**
     * 删除cf用户
     *
     * @param id 编号
     */
    void deleteCfUser(Long id);

    /**
     * 获得cf用户
     *
     * @param id cf用户编号
     * @return cf用户
     */
    CfUserDO getCfUser(Long id);

    /**
     * 获得cf用户
     *
     * @param id 用户编号
     * @return cf用户
     */
    CfUserDO getByUserId(Long id);

    /**
     * 获得cf用户分页
     *
     * @param pageReqVO 分页查询
     * @return cf用户分页
     */
    PageResult<CfUserDO> getCfUserPage(CfUserPageReqVO pageReqVO);

}
