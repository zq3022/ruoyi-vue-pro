package cn.iocoder.yudao.module.cf.service.playlist;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.playlist.PlaylistDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 歌单 Service 接口
 *
 * @author 芋道源码
 */
public interface PlaylistService {

    /**
     * 创建歌单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlaylist(@Valid PlaylistSaveReqVO createReqVO);

    /**
     * 更新歌单
     *
     * @param updateReqVO 更新信息
     */
    void updatePlaylist(@Valid PlaylistSaveReqVO updateReqVO);

    /**
     * 删除歌单
     *
     * @param id 编号
     */
    void deletePlaylist(Long id);

    /**
     * 获得歌单
     *
     * @param id 编号
     * @return 歌单
     */
    PlaylistDO getPlaylist(Long id);

    /**
     * 获得歌单分页
     *
     * @param pageReqVO 分页查询
     * @return 歌单分页
     */
    PageResult<PlaylistDO> getPlaylistPage(PlaylistPageReqVO pageReqVO);

}