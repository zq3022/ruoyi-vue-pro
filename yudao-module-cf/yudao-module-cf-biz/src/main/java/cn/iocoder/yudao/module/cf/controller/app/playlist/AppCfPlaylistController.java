package cn.iocoder.yudao.module.cf.controller.app.playlist;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.PlaylistPageReqVO;
import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserDetailRespVO;
import cn.iocoder.yudao.module.cf.service.cfuser.AppCfUserService;
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

    @Resource
    private AppCfUserService cfUserService;

    @Resource
    private AppCfPlaylistService appCfPlaylistService;

    @GetMapping("/list")
    @Operation(summary = "获得用户播放列表")
    @PreAuthenticated
    public CommonResult<AppCfUserDetailRespVO> getUserPlaylist(@Valid PlaylistPageReqVO pageReqVO) {
//        AppCfUserDetailRespVO userDetail = appCfPlaylistService.getPlaylistPage(pageReqVO);
//        return success(userDetail);
        //TODO
        return null;
    }


}
