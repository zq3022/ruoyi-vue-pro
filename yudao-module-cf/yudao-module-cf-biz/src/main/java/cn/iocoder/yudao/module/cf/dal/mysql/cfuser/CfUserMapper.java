package cn.iocoder.yudao.module.cf.dal.mysql.cfuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cf.controller.admin.cfuser.vo.*;

/**
 * cf用户 Mapper
 *
 * @author koi
 */
@Mapper
public interface CfUserMapper extends BaseMapperX<CfUserDO> {

    default PageResult<CfUserDO> selectPage(CfUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CfUserDO>()
                .eqIfPresent(CfUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CfUserDO::getUserType, reqVO.getUserType())
                .likeIfPresent(CfUserDO::getNickname, reqVO.getNickname())
                .eqIfPresent(CfUserDO::getAvatarUrl, reqVO.getAvatarUrl())
                .eqIfPresent(CfUserDO::getBackgroundUrl, reqVO.getBackgroundUrl())
                .eqIfPresent(CfUserDO::getSignature, reqVO.getSignature())
                .likeIfPresent(CfUserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(CfUserDO::getAccountType, reqVO.getAccountType())
                .likeIfPresent(CfUserDO::getShortUserName, reqVO.getShortUserName())
                .eqIfPresent(CfUserDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(CfUserDO::getGender, reqVO.getGender())
                .eqIfPresent(CfUserDO::getAccountStatus, reqVO.getAccountStatus())
                .eqIfPresent(CfUserDO::getProvince, reqVO.getProvince())
                .eqIfPresent(CfUserDO::getCity, reqVO.getCity())
                .eqIfPresent(CfUserDO::getAuthStatus, reqVO.getAuthStatus())
                .eqIfPresent(CfUserDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CfUserDO::getDefaultAvatar, reqVO.getDefaultAvatar())
                .eqIfPresent(CfUserDO::getDjStatus, reqVO.getDjStatus())
                .eqIfPresent(CfUserDO::getLocationStatus, reqVO.getLocationStatus())
                .eqIfPresent(CfUserDO::getVipType, reqVO.getVipType())
                .eqIfPresent(CfUserDO::getFollowed, reqVO.getFollowed())
                .eqIfPresent(CfUserDO::getMutual, reqVO.getMutual())
                .betweenIfPresent(CfUserDO::getLastLoginTime, reqVO.getLastLoginTime())
                .eqIfPresent(CfUserDO::getLastLoginIp, reqVO.getLastLoginIp())
                .eqIfPresent(CfUserDO::getViptypeVersion, reqVO.getViptypeVersion())
                .betweenIfPresent(CfUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CfUserDO::getId));
    }

    default CfUserDO selectByUserId(Long userId){
        return selectOne(new LambdaQueryWrapperX<CfUserDO>()
                .eq(CfUserDO::getUserId, userId).last("limit 1"));
    }
}
