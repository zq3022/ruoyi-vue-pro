package cn.iocoder.yudao.module.cf.service.artist;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.cf.controller.admin.artist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.artist.ArtistDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cf.dal.mysql.artist.ArtistMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cf.enums.ErrorCodeConstants.*;

/**
 * 艺术家 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ArtistServiceImpl implements ArtistService {

    @Resource
    private ArtistMapper artistMapper;

    @Override
    public Long createArtist(ArtistSaveReqVO createReqVO) {
        // 插入
        ArtistDO artist = BeanUtils.toBean(createReqVO, ArtistDO.class);
        artistMapper.insert(artist);
        // 返回
        return artist.getId();
    }

    @Override
    public void updateArtist(ArtistSaveReqVO updateReqVO) {
        // 校验存在
        validateArtistExists(updateReqVO.getId());
        // 更新
        ArtistDO updateObj = BeanUtils.toBean(updateReqVO, ArtistDO.class);
        artistMapper.updateById(updateObj);
    }

    @Override
    public void deleteArtist(Long id) {
        // 校验存在
        validateArtistExists(id);
        // 删除
        artistMapper.deleteById(id);
    }

    private void validateArtistExists(Long id) {
        if (artistMapper.selectById(id) == null) {
            throw exception(ARTIST_NOT_EXISTS);
        }
    }

    @Override
    public ArtistDO getArtist(Long id) {
        return artistMapper.selectById(id);
    }

    @Override
    public PageResult<ArtistDO> getArtistPage(ArtistPageReqVO pageReqVO) {
        return artistMapper.selectPage(pageReqVO);
    }

}