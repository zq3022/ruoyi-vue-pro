package cn.iocoder.yudao.module.cf.controller.admin.cfuser;

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

import cn.iocoder.yudao.module.cf.controller.admin.cfuser.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import cn.iocoder.yudao.module.cf.service.cfuser.CfUserService;

@Tag(name = "管理后台 - cf用户")
@RestController
@RequestMapping("/cf/user")
@Validated
public class CfUserController {

    @Resource
    private CfUserService userService;

    @PostMapping("/create")
    @Operation(summary = "创建cf用户")
    @PreAuthorize("@ss.hasPermission('cf:user:create')")
    public CommonResult<Long> createCfUser(@Valid @RequestBody CfUserSaveReqVO createReqVO) {
        return success(userService.createCfUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新cf用户")
    @PreAuthorize("@ss.hasPermission('cf:user:update')")
    public CommonResult<Boolean> updateCfUser(@Valid @RequestBody CfUserSaveReqVO updateReqVO) {
        userService.updateCfUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除cf用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cf:user:delete')")
    public CommonResult<Boolean> deleteCfUser(@RequestParam("id") Long id) {
        userService.deleteCfUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得cf用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cf:user:query')")
    public CommonResult<CfUserRespVO> getCfUser(@RequestParam("id") Long id) {
        CfUserDO user = userService.getCfUser(id);
        return success(BeanUtils.toBean(user, CfUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得cf用户分页")
    @PreAuthorize("@ss.hasPermission('cf:user:query')")
    public CommonResult<PageResult<CfUserRespVO>> getCfUserPage(@Valid CfUserPageReqVO pageReqVO) {
        PageResult<CfUserDO> pageResult = userService.getCfUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CfUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出cf用户 Excel")
    @PreAuthorize("@ss.hasPermission('cf:user:export')")
    @OperateLog(type = EXPORT)
    public void exportCfUserExcel(@Valid CfUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CfUserDO> list = userService.getCfUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "cf用户.xls", "数据", CfUserRespVO.class,
                        BeanUtils.toBean(list, CfUserRespVO.class));
    }

}
