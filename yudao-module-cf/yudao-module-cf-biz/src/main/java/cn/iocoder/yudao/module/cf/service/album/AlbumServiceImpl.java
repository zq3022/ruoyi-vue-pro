package cn.iocoder.yudao.module.cf.service.album;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.cf.controller.admin.album.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.album.AlbumDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cf.dal.mysql.album.AlbumMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cf.enums.ErrorCodeConstants.ALBUM_NOT_EXISTS;

/**
 * 专辑 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AlbumServiceImpl implements AlbumService {

    @Resource
    private AlbumMapper albumMapper;

    @Override
    public Long createAlbum(AlbumSaveReqVO createReqVO) {
        // 插入
        AlbumDO album = BeanUtils.toBean(createReqVO, AlbumDO.class);
        albumMapper.insert(album);
        // 返回
        return album.getId();
    }

    @Override
    public void updateAlbum(AlbumSaveReqVO updateReqVO) {
        // 校验存在
        validateAlbumExists(updateReqVO.getId());
        // 更新
        AlbumDO updateObj = BeanUtils.toBean(updateReqVO, AlbumDO.class);
        albumMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlbum(Long id) {
        // 校验存在
        validateAlbumExists(id);
        // 删除
        albumMapper.deleteById(id);
    }

    private void validateAlbumExists(Long id) {
        if (albumMapper.selectById(id) == null) {
            throw exception(ALBUM_NOT_EXISTS);
        }
    }

    @Override
    public AlbumDO getAlbum(Long id) {
        return albumMapper.selectById(id);
    }

    @Override
    public PageResult<AlbumDO> getAlbumPage(AlbumPageReqVO pageReqVO) {
        return albumMapper.selectPage(pageReqVO);
    }

}
