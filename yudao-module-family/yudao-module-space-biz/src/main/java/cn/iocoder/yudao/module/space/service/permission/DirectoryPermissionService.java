package cn.iocoder.yudao.module.space.service.permission;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.space.controller.admin.permission.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.permission.DirectoryPermissionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 目录权限 Service 接口
 *
 * @author 芋道源码
 */
public interface DirectoryPermissionService {

    /**
     * 创建目录权限
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDirectoryPermission(@Valid DirectoryPermissionSaveReqVO createReqVO);

    /**
     * 更新目录权限
     *
     * @param updateReqVO 更新信息
     */
    void updateDirectoryPermission(@Valid DirectoryPermissionSaveReqVO updateReqVO);

    /**
     * 删除目录权限
     *
     * @param id 编号
     */
    void deleteDirectoryPermission(Long id);

    /**
     * 获得目录权限
     *
     * @param id 编号
     * @return 目录权限
     */
    DirectoryPermissionDO getDirectoryPermission(Long id);

    /**
     * 获得目录权限分页
     *
     * @param pageReqVO 分页查询
     * @return 目录权限分页
     */
    PageResult<DirectoryPermissionDO> getDirectoryPermissionPage(DirectoryPermissionPageReqVO pageReqVO);

}