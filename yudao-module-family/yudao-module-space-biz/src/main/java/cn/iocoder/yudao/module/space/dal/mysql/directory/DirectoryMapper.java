package cn.iocoder.yudao.module.space.dal.mysql.directory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.*;

/**
 * 目录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DirectoryMapper extends BaseMapperX<DirectoryDO> {

    default PageResult<DirectoryDO> selectPage(DirectoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DirectoryDO>()
                .eqIfPresent(DirectoryDO::getSourceId, reqVO.getSourceId())
                .eqIfPresent(DirectoryDO::getLft, reqVO.getLft())
                .eqIfPresent(DirectoryDO::getRgt, reqVO.getRgt())
                .eqIfPresent(DirectoryDO::getLevel, reqVO.getLevel())
                .likeIfPresent(DirectoryDO::getName, reqVO.getName())
                .betweenIfPresent(DirectoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DirectoryDO::getId));
    }

}