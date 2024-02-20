package cn.iocoder.yudao.module.cf.controller.app.playlist;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistDetailReqVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistDetailRespVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistPageReqVO;
import cn.iocoder.yudao.module.cf.controller.app.playlist.vo.AppCfPlaylistPageRespVO;
import cn.iocoder.yudao.module.cf.service.playlist.AppCfPlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "Cf  APP - 用户歌单")
@RestController
@RequestMapping("/cf/playlist")
@Validated
@Slf4j
public class AppCfPlaylistController {

//    @Resource
//    private AppCfUserService cfUserService;

    @Resource
    private AppCfPlaylistService appCfPlaylistService;

    @GetMapping("/list")
    @Operation(summary = "获得用户播放列表集合")
    @PreAuthenticated
    public CommonResult<AppCfPlaylistPageRespVO> getUserPlaylist(@Valid AppCfPlaylistPageReqVO pageReqVO) {
        AppCfPlaylistPageRespVO resp = appCfPlaylistService.getPlaylistPage(pageReqVO);
        return success(resp);
    }

    @GetMapping("/detail")
    @Operation(summary = "获得用户播放列表详细信息")
    @PreAuthenticated
    public CommonResult<AppCfPlaylistDetailRespVO> getUserPlaylistDetail(@Valid AppCfPlaylistDetailReqVO detailReqVO) {
        AppCfPlaylistDetailRespVO resp = appCfPlaylistService.getPlaylistDetail(detailReqVO);
        return success(resp);
    }

}
