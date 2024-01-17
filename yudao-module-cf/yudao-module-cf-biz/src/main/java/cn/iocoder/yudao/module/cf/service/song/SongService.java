package cn.iocoder.yudao.module.cf.service.song;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cf.controller.admin.song.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.song.SongDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 歌曲 Service 接口
 *
 * @author 芋道源码
 */
public interface SongService {

    /**
     * 创建歌曲
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSong(@Valid SongSaveReqVO createReqVO);

    /**
     * 更新歌曲
     *
     * @param updateReqVO 更新信息
     */
    void updateSong(@Valid SongSaveReqVO updateReqVO);

    /**
     * 删除歌曲
     *
     * @param id 编号
     */
    void deleteSong(Long id);

    /**
     * 获得歌曲
     *
     * @param id 编号
     * @return 歌曲
     */
    SongDO getSong(Long id);

    /**
     * 获得歌曲分页
     *
     * @param pageReqVO 分页查询
     * @return 歌曲分页
     */
    PageResult<SongDO> getSongPage(SongPageReqVO pageReqVO);

}