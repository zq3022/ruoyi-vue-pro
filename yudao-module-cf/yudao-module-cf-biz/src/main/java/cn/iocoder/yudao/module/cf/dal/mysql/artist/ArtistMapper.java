package cn.iocoder.yudao.module.cf.dal.mysql.artist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cf.dal.dataobject.artist.ArtistDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cf.controller.admin.artist.vo.*;

/**
 * 艺术家 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ArtistMapper extends BaseMapperX<ArtistDO> {

    default PageResult<ArtistDO> selectPage(ArtistPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ArtistDO>()
                .likeIfPresent(ArtistDO::getName, reqVO.getName())
                .eqIfPresent(ArtistDO::getBriefDesc, reqVO.getBriefDesc())
                .eqIfPresent(ArtistDO::getMusicSize, reqVO.getMusicSize())
                .eqIfPresent(ArtistDO::getAlbumSize, reqVO.getAlbumSize())
                .eqIfPresent(ArtistDO::getMvSize, reqVO.getMvSize())
                .eqIfPresent(ArtistDO::getPicId, reqVO.getPicId())
                .eqIfPresent(ArtistDO::getPicUrl, reqVO.getPicUrl())
                .betweenIfPresent(ArtistDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ArtistDO::getId));
    }

}