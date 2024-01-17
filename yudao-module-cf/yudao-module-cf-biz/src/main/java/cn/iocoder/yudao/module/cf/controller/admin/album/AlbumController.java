package cn.iocoder.yudao.module.cf.controller.admin.album;

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

import cn.iocoder.yudao.module.cf.controller.admin.album.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.album.AlbumDO;
import cn.iocoder.yudao.module.cf.service.album.AlbumService;

@Tag(name = "管理后台 - 专辑")
@RestController
@RequestMapping("/cf/album")
@Validated
public class AlbumController {

    @Resource
    private AlbumService albumService;

    @PostMapping("/create")
    @Operation(summary = "创建专辑")
    @PreAuthorize("@ss.hasPermission('cf:album:create')")
    public CommonResult<Long> createAlbum(@Valid @RequestBody AlbumSaveReqVO createReqVO) {
        return success(albumService.createAlbum(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新专辑")
    @PreAuthorize("@ss.hasPermission('cf:album:update')")
    public CommonResult<Boolean> updateAlbum(@Valid @RequestBody AlbumSaveReqVO updateReqVO) {
        albumService.updateAlbum(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除专辑")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cf:album:delete')")
    public CommonResult<Boolean> deleteAlbum(@RequestParam("id") Long id) {
        albumService.deleteAlbum(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得专辑")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cf:album:query')")
    public CommonResult<AlbumRespVO> getAlbum(@RequestParam("id") Long id) {
        AlbumDO album = albumService.getAlbum(id);
        return success(BeanUtils.toBean(album, AlbumRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得专辑分页")
    @PreAuthorize("@ss.hasPermission('cf:album:query')")
    public CommonResult<PageResult<AlbumRespVO>> getAlbumPage(@Valid AlbumPageReqVO pageReqVO) {
        PageResult<AlbumDO> pageResult = albumService.getAlbumPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlbumRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出专辑 Excel")
    @PreAuthorize("@ss.hasPermission('cf:album:export')")
    @OperateLog(type = EXPORT)
    public void exportAlbumExcel(@Valid AlbumPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlbumDO> list = albumService.getAlbumPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "专辑.xls", "数据", AlbumRespVO.class,
                        BeanUtils.toBean(list, AlbumRespVO.class));
    }

}