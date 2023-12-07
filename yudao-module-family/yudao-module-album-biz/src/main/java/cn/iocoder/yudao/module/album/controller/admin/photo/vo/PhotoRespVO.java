package cn.iocoder.yudao.module.album.controller.admin.photo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 相册照片 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PhotoRespVO {

    @Schema(description = "图片编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "12201")
    @ExcelProperty("图片编号")
    private Long id;

    @Schema(description = "目录编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "4469")
    @ExcelProperty("目录编号")
    private Long directoryId;

    @Schema(description = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("文件名称")
    private String fileName;

    @Schema(description = "文件大小字节数")
    @ExcelProperty("文件大小字节数")
    private Long fileSize;

    @Schema(description = "图片宽度，像素")
    @ExcelProperty("图片宽度，像素")
    private Integer imageWidth;

    @Schema(description = "图片高度，像素")
    @ExcelProperty("图片高度，像素")
    private Integer imageHeight;

    @Schema(description = "文件修改时间")
    @ExcelProperty("文件修改时间")
    private LocalDateTime fileModifiedDate;

    @Schema(description = "文件类型", example = "李四")
    @ExcelProperty("文件类型")
    private String detectedFileTypeName;

    @Schema(description = "文件类型长名", example = "张三")
    @ExcelProperty("文件类型长名")
    private String detectedFileTypeLongName;

    @Schema(description = "MIME类型", example = "2")
    @ExcelProperty("MIME类型")
    private String detectedMimeType;

    @Schema(description = "文件后缀")
    @ExcelProperty("文件后缀")
    private String expectedFileNameExtension;

    @Schema(description = "图像描述", example = "你猜")
    @ExcelProperty("图像描述")
    private String imageDescription;

    @Schema(description = "生产商")
    @ExcelProperty("生产商")
    private String make;

    @Schema(description = "型号")
    @ExcelProperty("型号")
    private String model;

    @Schema(description = "色彩空间")
    @ExcelProperty("色彩空间")
    private String colorSpace;

    @Schema(description = "作者")
    @ExcelProperty("作者")
    private String artist;

    @Schema(description = "方向, 1'upper left', 3'lower right', 6'upper right', 8'lower left', 9'undefined'")
    @ExcelProperty("方向, 1'upper left', 3'lower right', 6'upper right', 8'lower left', 9'undefined'")
    private String orientation;

    @Schema(description = "水平方向分辨率")
    @ExcelProperty("水平方向分辨率")
    private String xResolution;

    @Schema(description = "垂直方向分辨率")
    @ExcelProperty("垂直方向分辨率")
    private String yResolution;

    @Schema(description = "分辨率单位")
    @ExcelProperty("分辨率单位")
    private String resolutionUnit;

    @Schema(description = "软件")
    @ExcelProperty("软件")
    private String software;

    @Schema(description = "白点")
    @ExcelProperty("白点")
    private String whitePoint;

    @Schema(description = "主色度")
    @ExcelProperty("主色度")
    private String primaryChromaticities;

    @Schema(description = "YCbCr系数")
    @ExcelProperty("YCbCr系数")
    private String ycbcrCoefficients;

    @Schema(description = "YCbCr定位")
    @ExcelProperty("YCbCr定位")
    private String ycbcrPositioning;

    @Schema(description = "版权")
    @ExcelProperty("版权")
    private String copyright;

    @Schema(description = "曝光时间")
    @ExcelProperty("曝光时间")
    private String exposureTime;

    @Schema(description = "光圈系数")
    @ExcelProperty("光圈系数")
    private String fnumber;

    @Schema(description = "曝光程序")
    @ExcelProperty("曝光程序")
    private String exposureProgram;

    @Schema(description = "Exif版本")
    @ExcelProperty("Exif版本")
    private String exifVersion;

    @Schema(description = "拍摄时间")
    @ExcelProperty("拍摄时间")
    private LocalDateTime datetimeOriginal;

    @Schema(description = "数字化时间")
    @ExcelProperty("数字化时间")
    private LocalDateTime datetimeDigitized;

    @Schema(description = "测光模式（平均测光、中央重点测光、点测光）")
    @ExcelProperty("测光模式（平均测光、中央重点测光、点测光）")
    private String meteringMode;

    @Schema(description = "闪光灯")
    @ExcelProperty("闪光灯")
    private String flash;

    @Schema(description = "镜头焦距")
    @ExcelProperty("镜头焦距")
    private String focalLength;

    @Schema(description = "图像宽度")
    @ExcelProperty("图像宽度")
    private Integer exifImageWidth;

    @Schema(description = "图像高度")
    @ExcelProperty("图像高度")
    private Integer exifImageLength;

    @Schema(description = "镜头厂商")
    @ExcelProperty("镜头厂商")
    private String lensMake;

    @Schema(description = "镜头型号")
    @ExcelProperty("镜头型号")
    private String lensModel;

    @Schema(description = "厂商注释")
    @ExcelProperty("厂商注释")
    private String makernote;

    @Schema(description = "用户注释")
    @ExcelProperty("用户注释")
    private String userComment;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}