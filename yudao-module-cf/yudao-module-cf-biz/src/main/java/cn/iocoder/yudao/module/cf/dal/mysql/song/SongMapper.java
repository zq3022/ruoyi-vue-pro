package cn.iocoder.yudao.module.cf.dal.mysql.song;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cf.dal.dataobject.song.SongDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cf.controller.admin.song.vo.*;

/**
 * 歌曲 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SongMapper extends BaseMapperX<SongDO> {

    default PageResult<SongDO> selectPage(SongPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SongDO>()
                .eqIfPresent(SongDO::getAlbumId, reqVO.getAlbumId())
                .likeIfPresent(SongDO::getName, reqVO.getName())
                .eqIfPresent(SongDO::getPst, reqVO.getPst())
                .eqIfPresent(SongDO::getT, reqVO.getT())
                .eqIfPresent(SongDO::getPop, reqVO.getPop())
                .eqIfPresent(SongDO::getSt, reqVO.getSt())
                .eqIfPresent(SongDO::getRt, reqVO.getRt())
                .eqIfPresent(SongDO::getFee, reqVO.getFee())
                .eqIfPresent(SongDO::getV, reqVO.getV())
                .eqIfPresent(SongDO::getCf, reqVO.getCf())
                .eqIfPresent(SongDO::getDt, reqVO.getDt())
                .eqIfPresent(SongDO::getCd, reqVO.getCd())
                .eqIfPresent(SongDO::getNo, reqVO.getNo())
                .eqIfPresent(SongDO::getFtype, reqVO.getFtype())
                .eqIfPresent(SongDO::getDjId, reqVO.getDjId())
                .eqIfPresent(SongDO::getCopyright, reqVO.getCopyright())
                .eqIfPresent(SongDO::getSId, reqVO.getSId())
                .eqIfPresent(SongDO::getMark, reqVO.getMark())
                .eqIfPresent(SongDO::getOriginCoverType, reqVO.getOriginCoverType())
                .eqIfPresent(SongDO::getSingle, reqVO.getSingle())
                .eqIfPresent(SongDO::getMst, reqVO.getMst())
                .eqIfPresent(SongDO::getCp, reqVO.getCp())
                .eqIfPresent(SongDO::getMv, reqVO.getMv())
                .eqIfPresent(SongDO::getRtype, reqVO.getRtype())
                .betweenIfPresent(SongDO::getPublishTime, reqVO.getPublishTime())
                .betweenIfPresent(SongDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SongDO::getId));
    }

}