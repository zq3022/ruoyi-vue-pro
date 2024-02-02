package cn.iocoder.yudao.module.cf.dal.mysql.playlist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserProfileRespVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistPageReqVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistRespVO;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import cn.iocoder.yudao.module.cf.dal.dataobject.playlist.PlaylistDO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.resultmap.MybatisLabel;
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

    default Boolean hasMoreOffsetPage(AppCfPlaylistPageReqVO reqVO) {
        Long count = selectCount(MPJWrappers.lambdaJoin(PlaylistDO.class)
                .select(PlaylistDO::getId)
                .rightJoin(String.format("cf_user_playlist up on up.playlist_id = t.id and up.user_id = %d", reqVO.getUserId()))
                .gt(PlaylistDO::getId, reqVO.getOffset())
                .orderByDesc(PlaylistDO::getId)
        );
        return count > reqVO.getLimit();
    }

    default List<AppCfPlaylistRespVO> getPlaylistOffsetPage(AppCfPlaylistPageReqVO reqVO){
         List<AppCfPlaylistRespVO> list = selectJoinList(AppCfPlaylistRespVO.class, MPJWrappers.lambdaJoin(PlaylistDO.class)
                 .selectAll(PlaylistDO.class)
                 .selectAssociation(CfUserDO.class, AppCfPlaylistRespVO::getPlayListCreator)
                 .innerJoin("cf_user_playlist up on up.playlist_id = t.id")
                 .leftJoin(CfUserDO.class, "u", CfUserDO::getUserId, PlaylistDO::getUserId)
                 .gt(PlaylistDO::getId, reqVO.getOffset())
                 .eq("up.user_id", reqVO.getUserId())
                 .orderByDesc(PlaylistDO::getId)
                 .last(String.format("limit %d", reqVO.getLimit()))
         );
         return list;
    }

}
