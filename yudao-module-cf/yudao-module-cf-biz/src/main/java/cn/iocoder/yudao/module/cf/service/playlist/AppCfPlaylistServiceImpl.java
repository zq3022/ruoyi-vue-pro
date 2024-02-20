package cn.iocoder.yudao.module.cf.service.playlist;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.*;
import cn.iocoder.yudao.module.cf.dal.mysql.playlist.PlaylistMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppCfPlaylistServiceImpl implements AppCfPlaylistService {

    @Resource
    private PlaylistMapper playlistMapper;

    @Override
    public AppCfPlaylistPageRespVO getPlaylistPage(AppCfPlaylistPageReqVO pageReqVO) {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        boolean more = playlistMapper.hasMoreOffsetPage(pageReqVO, loginUser.getId());
        List<AppCfPlaylistRespVO> playlist = playlistMapper.getPlaylistOffsetPage(pageReqVO, loginUser.getId());
        return new AppCfPlaylistPageRespVO().setPlaylist(playlist).setCode(200).setMore(more);
    }

    @Override
    public AppCfPlaylistDetailRespVO getPlaylistDetail(AppCfPlaylistDetailReqVO detailReqVO) {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        AppCfPlaylistRespVO playlist = playlistMapper.getPlaylistDetail(detailReqVO, loginUser.getId());
        return new AppCfPlaylistDetailRespVO().setPlaylist(playlist).setCode(0);
    }
}
