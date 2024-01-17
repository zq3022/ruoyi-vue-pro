package cn.iocoder.yudao.module.cf.dal.mysql.playlist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cf.dal.dataobject.playlist.PlaylistDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.*;

/**
 * 歌单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PlaylistMapper extends BaseMapperX<PlaylistDO> {

    default PageResult<PlaylistDO> selectPage(PlaylistPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlaylistDO>()
                .eqIfPresent(PlaylistDO::getUserId, reqVO.getUserId())
                .likeIfPresent(PlaylistDO::getName, reqVO.getName())
                .eqIfPresent(PlaylistDO::getCoverImgId, reqVO.getCoverImgId())
                .eqIfPresent(PlaylistDO::getCoverImgUrl, reqVO.getCoverImgUrl())
                .eqIfPresent(PlaylistDO::getAdType, reqVO.getAdType())
                .eqIfPresent(PlaylistDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PlaylistDO::getOpRecommend, reqVO.getOpRecommend())
                .eqIfPresent(PlaylistDO::getHighQuality, reqVO.getHighQuality())
                .eqIfPresent(PlaylistDO::getNewImported, reqVO.getNewImported())
                .eqIfPresent(PlaylistDO::getTrackCount, reqVO.getTrackCount())
                .eqIfPresent(PlaylistDO::getPrivacy, reqVO.getPrivacy())
                .betweenIfPresent(PlaylistDO::getTrackUpdateTime, reqVO.getTrackUpdateTime())
                .eqIfPresent(PlaylistDO::getCommentThreadId, reqVO.getCommentThreadId())
                .eqIfPresent(PlaylistDO::getPlayCount, reqVO.getPlayCount())
                .betweenIfPresent(PlaylistDO::getTrackNumberUpdateTime, reqVO.getTrackNumberUpdateTime())
                .eqIfPresent(PlaylistDO::getSubscribedCount, reqVO.getSubscribedCount())
                .eqIfPresent(PlaylistDO::getCloudTrackCount, reqVO.getCloudTrackCount())
                .eqIfPresent(PlaylistDO::getOrdered, reqVO.getOrdered())
                .eqIfPresent(PlaylistDO::getDescription, reqVO.getDescription())
                .eqIfPresent(PlaylistDO::getTags, reqVO.getTags())
                .eqIfPresent(PlaylistDO::getBackgroundCoverId, reqVO.getBackgroundCoverId())
                .eqIfPresent(PlaylistDO::getTitleImage, reqVO.getTitleImage())
                .eqIfPresent(PlaylistDO::getSubscribed, reqVO.getSubscribed())
                .eqIfPresent(PlaylistDO::getShareCount, reqVO.getShareCount())
                .eqIfPresent(PlaylistDO::getCommentCount, reqVO.getCommentCount())
                .betweenIfPresent(PlaylistDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PlaylistDO::getSpecialType, reqVO.getSpecialType())
                .orderByDesc(PlaylistDO::getId));
    }

}