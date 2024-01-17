package cn.iocoder.yudao.module.cf.controller.admin.playlist;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.playlist.PlaylistDO;
import cn.iocoder.yudao.module.cf.service.playlist.PlaylistService;

@Tag(name = "管理后台 - 歌单")
@RestController
@RequestMapping("/cf/playlist")
@Validated
public class PlaylistController {

    @Resource
    private PlaylistService playlistService;

    @PostMapping("/create")
    @Operation(summary = "创建歌单")
    @PreAuthorize("@ss.hasPermission('cf:playlist:create')")
    public CommonResult<Long> createPlaylist(@Valid @RequestBody PlaylistSaveReqVO createReqVO) {
        return success(playlistService.createPlaylist(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新歌单")
    @PreAuthorize("@ss.hasPermission('cf:playlist:update')")
    public CommonResult<Boolean> updatePlaylist(@Valid @RequestBody PlaylistSaveReqVO updateReqVO) {
        playlistService.updatePlaylist(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除歌单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cf:playlist:delete')")
    public CommonResult<Boolean> deletePlaylist(@RequestParam("id") Long id) {
        playlistService.deletePlaylist(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得歌单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cf:playlist:query')")
    public CommonResult<PlaylistRespVO> getPlaylist(@RequestParam("id") Long id) {
        PlaylistDO playlist = playlistService.getPlaylist(id);
        return success(BeanUtils.toBean(playlist, PlaylistRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得歌单分页")
    @PreAuthorize("@ss.hasPermission('cf:playlist:query')")
    public CommonResult<PageResult<PlaylistRespVO>> getPlaylistPage(@Valid PlaylistPageReqVO pageReqVO) {
        PageResult<PlaylistDO> pageResult = playlistService.getPlaylistPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PlaylistRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出歌单 Excel")
    @PreAuthorize("@ss.hasPermission('cf:playlist:export')")
    @OperateLog(type = EXPORT)
    public void exportPlaylistExcel(@Valid PlaylistPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PlaylistDO> list = playlistService.getPlaylistPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "歌单.xls", "数据", PlaylistRespVO.class,
                        BeanUtils.toBean(list, PlaylistRespVO.class));
    }

}