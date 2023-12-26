package cn.iocoder.yudao.module.space.dal.mysql.source;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.space.controller.admin.source.vo.SourcePageReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;

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

    default List<SourceDO> listParentSubRelationSource(Long oldSourceId, String newPath, Integer newType) {
        return selectList(new LambdaQueryWrapperX<SourceDO>()
                .eq(SourceDO::getType, newType)
                .ne(oldSourceId != null, SourceDO::getId, oldSourceId)
                .and(w -> w.likeRight(SourceDO::getPath, newPath)
                        .or()
                        .apply(" path = left({0}, char_length(path)) ",newPath)));
    }

    default List<Integer> listSourceTypes(){
        return selectList(new QueryWrapper<SourceDO>()
                .select("distinct type")).stream().map(SourceDO::getType).collect(Collectors.toList());
    }
}
