package cn.iocoder.yudao.module.space.controller.admin.source;

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

import cn.iocoder.yudao.module.space.controller.admin.source.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.service.source.SourceService;

@Tag(name = "管理后台 - 目录源")
@RestController
@RequestMapping("/space/source")
@Validated
public class SourceController {

    @Resource
    private SourceService sourceService;

    @PostMapping("/create")
    @Operation(summary = "创建目录源")
    @PreAuthorize("@ss.hasPermission('space:source:create')")
    public CommonResult<Long> createSource(@Valid @RequestBody SourceSaveReqVO createReqVO) {
        return success(sourceService.createSource(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新目录源")
    @PreAuthorize("@ss.hasPermission('space:source:update')")
    public CommonResult<Boolean> updateSource(@Valid @RequestBody SourceSaveReqVO updateReqVO) {
        sourceService.updateSource(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除目录源")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('space:source:delete')")
    public CommonResult<Boolean> deleteSource(@RequestParam("id") Long id) {
        sourceService.deleteSource(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得目录源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('space:source:query')")
    public CommonResult<SourceRespVO> getSource(@RequestParam("id") Long id) {
        SourceDO source = sourceService.getSource(id);
        return success(BeanUtils.toBean(source, SourceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得目录源分页")
    @PreAuthorize("@ss.hasPermission('space:source:query')")
    public CommonResult<PageResult<SourceRespVO>> getSourcePage(@Valid SourcePageReqVO pageReqVO) {
        PageResult<SourceDO> pageResult = sourceService.getSourcePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SourceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出目录源 Excel")
    @PreAuthorize("@ss.hasPermission('space:source:export')")
    @OperateLog(type = EXPORT)
    public void exportSourceExcel(@Valid SourcePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SourceDO> list = sourceService.getSourcePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "目录源.xls", "数据", SourceRespVO.class,
                        BeanUtils.toBean(list, SourceRespVO.class));
    }

}