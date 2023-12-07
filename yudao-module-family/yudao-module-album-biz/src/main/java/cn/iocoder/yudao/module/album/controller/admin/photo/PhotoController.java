package cn.iocoder.yudao.module.album.controller.admin.photo;

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

import cn.iocoder.yudao.module.album.controller.admin.photo.vo.*;
import cn.iocoder.yudao.module.album.dal.dataobject.photo.PhotoDO;
import cn.iocoder.yudao.module.album.service.photo.PhotoService;

@Tag(name = "管理后台 - 相册照片")
@RestController
@RequestMapping("/album/photo")
@Validated
public class PhotoController {

    @Resource
    private PhotoService photoService;

    @PostMapping("/create")
    @Operation(summary = "创建相册照片")
    @PreAuthorize("@ss.hasPermission('album:photo:create')")
    public CommonResult<Long> createPhoto(@Valid @RequestBody PhotoSaveReqVO createReqVO) {
        return success(photoService.createPhoto(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新相册照片")
    @PreAuthorize("@ss.hasPermission('album:photo:update')")
    public CommonResult<Boolean> updatePhoto(@Valid @RequestBody PhotoSaveReqVO updateReqVO) {
        photoService.updatePhoto(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除相册照片")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('album:photo:delete')")
    public CommonResult<Boolean> deletePhoto(@RequestParam("id") Long id) {
        photoService.deletePhoto(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得相册照片")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('album:photo:query')")
    public CommonResult<PhotoRespVO> getPhoto(@RequestParam("id") Long id) {
        PhotoDO photo = photoService.getPhoto(id);
        return success(BeanUtils.toBean(photo, PhotoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得相册照片分页")
    @PreAuthorize("@ss.hasPermission('album:photo:query')")
    public CommonResult<PageResult<PhotoRespVO>> getPhotoPage(@Valid PhotoPageReqVO pageReqVO) {
        PageResult<PhotoDO> pageResult = photoService.getPhotoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PhotoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出相册照片 Excel")
    @PreAuthorize("@ss.hasPermission('album:photo:export')")
    @OperateLog(type = EXPORT)
    public void exportPhotoExcel(@Valid PhotoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PhotoDO> list = photoService.getPhotoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "相册照片.xls", "数据", PhotoRespVO.class,
                        BeanUtils.toBean(list, PhotoRespVO.class));
    }

}