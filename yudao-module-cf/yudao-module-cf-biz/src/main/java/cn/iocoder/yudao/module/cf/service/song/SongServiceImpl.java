package cn.iocoder.yudao.module.cf.service.song;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.cf.controller.admin.song.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.song.SongDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.cf.dal.mysql.song.SongMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cf.enums.ErrorCodeConstants.*;

/**
 * 歌曲 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SongServiceImpl implements SongService {

    @Resource
    private SongMapper songMapper;

    @Override
    public Long createSong(SongSaveReqVO createReqVO) {
        // 插入
        SongDO song = BeanUtils.toBean(createReqVO, SongDO.class);
        songMapper.insert(song);
        // 返回
        return song.getId();
    }

    @Override
    public void updateSong(SongSaveReqVO updateReqVO) {
        // 校验存在
        validateSongExists(updateReqVO.getId());
        // 更新
        SongDO updateObj = BeanUtils.toBean(updateReqVO, SongDO.class);
        songMapper.updateById(updateObj);
    }

    @Override
    public void deleteSong(Long id) {
        // 校验存在
        validateSongExists(id);
        // 删除
        songMapper.deleteById(id);
    }

    private void validateSongExists(Long id) {
        if (songMapper.selectById(id) == null) {
            throw exception(SONG_NOT_EXISTS);
        }
    }

    @Override
    public SongDO getSong(Long id) {
        return songMapper.selectById(id);
    }

    @Override
    public PageResult<SongDO> getSongPage(SongPageReqVO pageReqVO) {
        return songMapper.selectPage(pageReqVO);
    }

}