package cn.iocoder.yudao.module.cf.dal.mysql.album;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cf.dal.dataobject.album.AlbumDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cf.controller.admin.album.vo.*;

/**
 * 专辑 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AlbumMapper extends BaseMapperX<AlbumDO> {

    default PageResult<AlbumDO> selectPage(AlbumPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlbumDO>()
                .likeIfPresent(AlbumDO::getName, reqVO.getName())
                .eqIfPresent(AlbumDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(AlbumDO::getPic, reqVO.getPic())
                .eqIfPresent(AlbumDO::getAlia, reqVO.getAlia())
                .betweenIfPresent(AlbumDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(AlbumDO::getCompany, reqVO.getCompany())
                .eqIfPresent(AlbumDO::getDescription, reqVO.getDescription())
                .eqIfPresent(AlbumDO::getCommentCount, reqVO.getCommentCount())
                .eqIfPresent(AlbumDO::getLikedCount, reqVO.getLikedCount())
                .eqIfPresent(AlbumDO::getShareCount, reqVO.getShareCount())
                .eqIfPresent(AlbumDO::getSongCount, reqVO.getSongCount())
                .betweenIfPresent(AlbumDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlbumDO::getId));
    }

}