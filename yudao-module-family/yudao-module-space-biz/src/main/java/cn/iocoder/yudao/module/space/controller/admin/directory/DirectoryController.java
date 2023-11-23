package cn.iocoder.yudao.module.space.controller.admin.directory;

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

import cn.iocoder.yudao.module.space.controller.admin.directory.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.module.space.service.directory.DirectoryService;

@Tag(name = "管理后台 - 目录")
@RestController
@RequestMapping("/space/directory")
@Validated
public class DirectoryController {

    @Resource
    private DirectoryService directoryService;

    @PostMapping("/create")
    @Operation(summary = "创建目录")
    @PreAuthorize("@ss.hasPermission('space:directory:create')")
    public CommonResult<Long> createDirectory(@Valid @RequestBody DirectorySaveReqVO createReqVO) {
        return success(directoryService.createDirectory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新目录")
    @PreAuthorize("@ss.hasPermission('space:directory:update')")
    public CommonResult<Boolean> updateDirectory(@Valid @RequestBody DirectorySaveReqVO updateReqVO) {
        directoryService.updateDirectory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除目录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('space:directory:delete')")
    public CommonResult<Boolean> deleteDirectory(@RequestParam("id") Long id) {
        directoryService.deleteDirectory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得目录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('space:directory:query')")
    public CommonResult<DirectoryRespVO> getDirectory(@RequestParam("id") Long id) {
        DirectoryDO directory = directoryService.getDirectory(id);
        return success(BeanUtils.toBean(directory, DirectoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得目录分页")
    @PreAuthorize("@ss.hasPermission('space:directory:query')")
    public CommonResult<PageResult<DirectoryRespVO>> getDirectoryPage(@Valid DirectoryPageReqVO pageReqVO) {
        PageResult<DirectoryDO> pageResult = directoryService.getDirectoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DirectoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出目录 Excel")
    @PreAuthorize("@ss.hasPermission('space:directory:export')")
    @OperateLog(type = EXPORT)
    public void exportDirectoryExcel(@Valid DirectoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DirectoryDO> list = directoryService.getDirectoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "目录.xls", "数据", DirectoryRespVO.class,
                        BeanUtils.toBean(list, DirectoryRespVO.class));
    }

}