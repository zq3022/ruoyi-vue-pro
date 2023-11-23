package cn.iocoder.yudao.module.space.dal.mysql.permission;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.space.dal.dataobject.permission.DirectoryPermissionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.space.controller.admin.permission.vo.*;

/**
 * 目录权限 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DirectoryPermissionMapper extends BaseMapperX<DirectoryPermissionDO> {

    default PageResult<DirectoryPermissionDO> selectPage(DirectoryPermissionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DirectoryPermissionDO>()
                .eqIfPresent(DirectoryPermissionDO::getDirectoryId, reqVO.getDirectoryId())
                .eqIfPresent(DirectoryPermissionDO::getUserId, reqVO.getUserId())
                .eqIfPresent(DirectoryPermissionDO::getManage, reqVO.getManage())
                .eqIfPresent(DirectoryPermissionDO::getWritable, reqVO.getWritable())
                .eqIfPresent(DirectoryPermissionDO::getReadable, reqVO.getReadable())
                .betweenIfPresent(DirectoryPermissionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DirectoryPermissionDO::getId));
    }

}