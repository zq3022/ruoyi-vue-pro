package cn.iocoder.yudao.module.cf.controller.admin.artist;

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

import cn.iocoder.yudao.module.cf.controller.admin.artist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.artist.ArtistDO;
import cn.iocoder.yudao.module.cf.service.artist.ArtistService;

@Tag(name = "管理后台 - 艺术家")
@RestController
@RequestMapping("/cf/artist")
@Validated
public class ArtistController {

    @Resource
    private ArtistService artistService;

    @PostMapping("/create")
    @Operation(summary = "创建艺术家")
    @PreAuthorize("@ss.hasPermission('cf:artist:create')")
    public CommonResult<Long> createArtist(@Valid @RequestBody ArtistSaveReqVO createReqVO) {
        return success(artistService.createArtist(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新艺术家")
    @PreAuthorize("@ss.hasPermission('cf:artist:update')")
    public CommonResult<Boolean> updateArtist(@Valid @RequestBody ArtistSaveReqVO updateReqVO) {
        artistService.updateArtist(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除艺术家")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cf:artist:delete')")
    public CommonResult<Boolean> deleteArtist(@RequestParam("id") Long id) {
        artistService.deleteArtist(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得艺术家")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cf:artist:query')")
    public CommonResult<ArtistRespVO> getArtist(@RequestParam("id") Long id) {
        ArtistDO artist = artistService.getArtist(id);
        return success(BeanUtils.toBean(artist, ArtistRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得艺术家分页")
    @PreAuthorize("@ss.hasPermission('cf:artist:query')")
    public CommonResult<PageResult<ArtistRespVO>> getArtistPage(@Valid ArtistPageReqVO pageReqVO) {
        PageResult<ArtistDO> pageResult = artistService.getArtistPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ArtistRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出艺术家 Excel")
    @PreAuthorize("@ss.hasPermission('cf:artist:export')")
    @OperateLog(type = EXPORT)
    public void exportArtistExcel(@Valid ArtistPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ArtistDO> list = artistService.getArtistPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "艺术家.xls", "数据", ArtistRespVO.class,
                        BeanUtils.toBean(list, ArtistRespVO.class));
    }

}