package cn.iocoder.yudao.module.cf.service.playlist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.PlaylistPageReqVO;
import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.PlaylistSaveReqVO;
import cn.iocoder.yudao.module.cf.dal.dataobject.playlist.PlaylistDO;

import javax.validation.Valid;

/**
 * 歌单 Service 接口
 *
 * @author 芋道源码
 */
public interface AppCfPlaylistService {
    /**
     * 获得歌单分页
     *
     * @param pageReqVO 分页查询
     * @return 歌单分页
     */
    PageResult<PlaylistDO> getPlaylistPage(PlaylistPageReqVO pageReqVO);

}
