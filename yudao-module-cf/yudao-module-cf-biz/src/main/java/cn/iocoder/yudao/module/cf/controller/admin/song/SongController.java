package cn.iocoder.yudao.module.cf.controller.admin.song;

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

import cn.iocoder.yudao.module.cf.controller.admin.song.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.song.SongDO;
import cn.iocoder.yudao.module.cf.service.song.SongService;

@Tag(name = "管理后台 - 歌曲")
@RestController
@RequestMapping("/cf/song")
@Validated
public class SongController {

    @Resource
    private SongService songService;

    @PostMapping("/create")
    @Operation(summary = "创建歌曲")
    @PreAuthorize("@ss.hasPermission('cf:song:create')")
    public CommonResult<Long> createSong(@Valid @RequestBody SongSaveReqVO createReqVO) {
        return success(songService.createSong(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新歌曲")
    @PreAuthorize("@ss.hasPermission('cf:song:update')")
    public CommonResult<Boolean> updateSong(@Valid @RequestBody SongSaveReqVO updateReqVO) {
        songService.updateSong(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除歌曲")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cf:song:delete')")
    public CommonResult<Boolean> deleteSong(@RequestParam("id") Long id) {
        songService.deleteSong(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得歌曲")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cf:song:query')")
    public CommonResult<SongRespVO> getSong(@RequestParam("id") Long id) {
        SongDO song = songService.getSong(id);
        return success(BeanUtils.toBean(song, SongRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得歌曲分页")
    @PreAuthorize("@ss.hasPermission('cf:song:query')")
    public CommonResult<PageResult<SongRespVO>> getSongPage(@Valid SongPageReqVO pageReqVO) {
        PageResult<SongDO> pageResult = songService.getSongPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SongRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出歌曲 Excel")
    @PreAuthorize("@ss.hasPermission('cf:song:export')")
    @OperateLog(type = EXPORT)
    public void exportSongExcel(@Valid SongPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SongDO> list = songService.getSongPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "歌曲.xls", "数据", SongRespVO.class,
                        BeanUtils.toBean(list, SongRespVO.class));
    }

}