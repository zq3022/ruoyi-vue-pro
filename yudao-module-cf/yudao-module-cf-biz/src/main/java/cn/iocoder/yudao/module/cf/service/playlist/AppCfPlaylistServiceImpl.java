package cn.iocoder.yudao.module.cf.service.playlist;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistPageReqVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistPageRespVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistRespVO;
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
        Assert.notNull(loginUser, "登录用户不能为空");
        pageReqVO.setUserId(loginUser.getId());
        boolean more = playlistMapper.hasMoreOffsetPage(pageReqVO);
        List<AppCfPlaylistRespVO> playlist = playlistMapper.getPlaylistOffsetPage(pageReqVO);
        return new AppCfPlaylistPageRespVO().setPlaylist(playlist).setCode(200).setMore(more);
    }
}
