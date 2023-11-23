package cn.iocoder.yudao.module.space.service.permission;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.space.controller.admin.permission.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.permission.DirectoryPermissionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.space.dal.mysql.permission.DirectoryPermissionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.*;

/**
 * 目录权限 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DirectoryPermissionServiceImpl implements DirectoryPermissionService {

    @Resource
    private DirectoryPermissionMapper directoryPermissionMapper;

    @Override
    public Long createDirectoryPermission(DirectoryPermissionSaveReqVO createReqVO) {
        // 插入
        DirectoryPermissionDO directoryPermission = BeanUtils.toBean(createReqVO, DirectoryPermissionDO.class);
        directoryPermissionMapper.insert(directoryPermission);
        // 返回
        return directoryPermission.getId();
    }

    @Override
    public void updateDirectoryPermission(DirectoryPermissionSaveReqVO updateReqVO) {
        // 校验存在
        validateDirectoryPermissionExists(updateReqVO.getId());
        // 更新
        DirectoryPermissionDO updateObj = BeanUtils.toBean(updateReqVO, DirectoryPermissionDO.class);
        directoryPermissionMapper.updateById(updateObj);
    }

    @Override
    public void deleteDirectoryPermission(Long id) {
        // 校验存在
        validateDirectoryPermissionExists(id);
        // 删除
        directoryPermissionMapper.deleteById(id);
    }

    private void validateDirectoryPermissionExists(Long id) {
        if (directoryPermissionMapper.selectById(id) == null) {
            throw exception(DIRECTORY_PERMISSION_NOT_EXISTS);
        }
    }

    @Override
    public DirectoryPermissionDO getDirectoryPermission(Long id) {
        return directoryPermissionMapper.selectById(id);
    }

    @Override
    public PageResult<DirectoryPermissionDO> getDirectoryPermissionPage(DirectoryPermissionPageReqVO pageReqVO) {
        return directoryPermissionMapper.selectPage(pageReqVO);
    }

}