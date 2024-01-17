package cn.iocoder.yudao.module.cf.service.playlist;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.playlist.PlaylistDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cf.dal.mysql.playlist.PlaylistMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cf.enums.ErrorCodeConstants.*;

/**
 * 歌单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PlaylistServiceImpl implements PlaylistService {

    @Resource
    private PlaylistMapper playlistMapper;

    @Override
    public Long createPlaylist(PlaylistSaveReqVO createReqVO) {
        // 插入
        PlaylistDO playlist = BeanUtils.toBean(createReqVO, PlaylistDO.class);
        playlistMapper.insert(playlist);
        // 返回
        return playlist.getId();
    }

    @Override
    public void updatePlaylist(PlaylistSaveReqVO updateReqVO) {
        // 校验存在
        validatePlaylistExists(updateReqVO.getId());
        // 更新
        PlaylistDO updateObj = BeanUtils.toBean(updateReqVO, PlaylistDO.class);
        playlistMapper.updateById(updateObj);
    }

    @Override
    public void deletePlaylist(Long id) {
        // 校验存在
        validatePlaylistExists(id);
        // 删除
        playlistMapper.deleteById(id);
    }

    private void validatePlaylistExists(Long id) {
        if (playlistMapper.selectById(id) == null) {
            throw exception(PLAYLIST_NOT_EXISTS);
        }
    }

    @Override
    public PlaylistDO getPlaylist(Long id) {
        return playlistMapper.selectById(id);
    }

    @Override
    public PageResult<PlaylistDO> getPlaylistPage(PlaylistPageReqVO pageReqVO) {
        return playlistMapper.selectPage(pageReqVO);
    }

}