package cn.iocoder.yudao.module.space.dal.mysql.source;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.space.controller.admin.source.vo.*;

/**
 * 目录源 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SourceMapper extends BaseMapperX<SourceDO> {

    default PageResult<SourceDO> selectPage(SourcePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SourceDO>()
                .eqIfPresent(SourceDO::getPath, reqVO.getPath())
                .eqIfPresent(SourceDO::getType, reqVO.getType())
                .betweenIfPresent(SourceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SourceDO::getId));
    }

}