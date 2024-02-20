package cn.iocoder.yudao.module.cf.service.playlist;

import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistDetailReqVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistDetailRespVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistPageReqVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistPageRespVO;

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
    AppCfPlaylistPageRespVO getPlaylistPage(AppCfPlaylistPageReqVO pageReqVO);

    /**
     * 获得歌单详情
     *
     * @param detailReqVO 详情查询
     * @return 歌单详情
     */
    AppCfPlaylistDetailRespVO getPlaylistDetail(AppCfPlaylistDetailReqVO detailReqVO);
}
