package cn.iocoder.yudao.module.space.controller.admin.permission;

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

import cn.iocoder.yudao.module.space.controller.admin.permission.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.permission.DirectoryPermissionDO;
import cn.iocoder.yudao.module.space.service.permission.DirectoryPermissionService;

@Tag(name = "管理后台 - 目录权限")
@RestController
@RequestMapping("/space/directory-permission")
@Validated
public class DirectoryPermissionController {

    @Resource
    private DirectoryPermissionService directoryPermissionService;

    @PostMapping("/create")
    @Operation(summary = "创建目录权限")
    @PreAuthorize("@ss.hasPermission('space:directory-permission:create')")
    public CommonResult<Long> createDirectoryPermission(@Valid @RequestBody DirectoryPermissionSaveReqVO createReqVO) {
        return success(directoryPermissionService.createDirectoryPermission(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新目录权限")
    @PreAuthorize("@ss.hasPermission('space:directory-permission:update')")
    public CommonResult<Boolean> updateDirectoryPermission(@Valid @RequestBody DirectoryPermissionSaveReqVO updateReqVO) {
        directoryPermissionService.updateDirectoryPermission(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除目录权限")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('space:directory-permission:delete')")
    public CommonResult<Boolean> deleteDirectoryPermission(@RequestParam("id") Long id) {
        directoryPermissionService.deleteDirectoryPermission(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得目录权限")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('space:directory-permission:query')")
    public CommonResult<DirectoryPermissionRespVO> getDirectoryPermission(@RequestParam("id") Long id) {
        DirectoryPermissionDO directoryPermission = directoryPermissionService.getDirectoryPermission(id);
        return success(BeanUtils.toBean(directoryPermission, DirectoryPermissionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得目录权限分页")
    @PreAuthorize("@ss.hasPermission('space:directory-permission:query')")
    public CommonResult<PageResult<DirectoryPermissionRespVO>> getDirectoryPermissionPage(@Valid DirectoryPermissionPageReqVO pageReqVO) {
        PageResult<DirectoryPermissionDO> pageResult = directoryPermissionService.getDirectoryPermissionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DirectoryPermissionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出目录权限 Excel")
    @PreAuthorize("@ss.hasPermission('space:directory-permission:export')")
    @OperateLog(type = EXPORT)
    public void exportDirectoryPermissionExcel(@Valid DirectoryPermissionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DirectoryPermissionDO> list = directoryPermissionService.getDirectoryPermissionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "目录权限.xls", "数据", DirectoryPermissionRespVO.class,
                        BeanUtils.toBean(list, DirectoryPermissionRespVO.class));
    }

}