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

    @Schema(description = "文件类型", example = "李四")
    @ExcelProperty("文件类型")
    private String detectedFileTypeName;

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

    @Schema(description = "作者")
    @ExcelProperty("作者")
    private String artist;

    @Schema(description = "水平方向分辨率")
    @ExcelProperty("水平方向分辨率")
    private String xResolution;

    @Schema(description = "垂直方向分辨率")
    @ExcelProperty("垂直方向分辨率")
    private String yResolution;

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

    @Schema(description = "闪光灯")
    @ExcelProperty("闪光灯")
    private String flash;

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

    @Schema(description = "文件名称", example = "李四")
    @ExcelProperty("文件名称")
    private String fileName;

    @Schema(description = "文件大小字节数")
    @ExcelProperty("文件大小字节数")
    private String fileSize;

    @Schema(description = "图片宽度，像素")
    @ExcelProperty("图片宽度，像素")
    private String imageWidth;

    @Schema(description = "图片高度，像素")
    @ExcelProperty("图片高度，像素")
    private String imageHeight;

    @Schema(description = "文件修改时间")
    @ExcelProperty("文件修改时间")
    private String fileModifiedDate;

    @Schema(description = "文件来源")
    @ExcelProperty("文件来源")
    private String fileSource;

    @Schema(description = "压缩类型", example = "2")
    @ExcelProperty("压缩类型")
    private String compressionType;

    @Schema(description = "数据精度")
    @ExcelProperty("数据精度")
    private String dataPrecision;

    @Schema(description = "压缩比")
    @ExcelProperty("压缩比")
    private String compressedBitsPerPixel;

    @Schema(description = "分辨率单位")
    @ExcelProperty("分辨率单位")
    private String resolutionUnits;

    @Schema(description = "方向")
    @ExcelProperty("方向")
    private String orientation;

    @Schema(description = "曝光补偿")
    @ExcelProperty("曝光补偿")
    private String exposureBiasValue;

    @Schema(description = "曝光模式")
    @ExcelProperty("曝光模式")
    private String exposureMode;

    @Schema(description = "色彩")
    @ExcelProperty("色彩")
    private String colorSpace;

    @Schema(description = "饱和度")
    @ExcelProperty("饱和度")
    private String saturation;

    @Schema(description = "亮度")
    @ExcelProperty("亮度")
    private String brightnessValue;

    @Schema(description = "白平衡")
    @ExcelProperty("白平衡")
    private String whiteBalance;

    @Schema(description = "白平衡模式")
    @ExcelProperty("白平衡模式")
    private String whiteBalanceMode;

    @Schema(description = "光圈")
    @ExcelProperty("光圈")
    private String apertureValue;

    @Schema(description = "最大光圈值")
    @ExcelProperty("最大光圈值")
    private String maxApertureValue;

    @Schema(description = "感光度")
    @ExcelProperty("感光度")
    private String isoSpeedRatings;

    @Schema(description = "对比度")
    @ExcelProperty("对比度")
    private String contrast;

    @Schema(description = "锯齿")
    @ExcelProperty("锯齿")
    private String sharpness;

    @Schema(description = "变焦")
    @ExcelProperty("变焦")
    private String digitalZoomRatio;

    @Schema(description = "快门")
    @ExcelProperty("快门")
    private String shutterSpeedValue;

    @Schema(description = "测光模式")
    @ExcelProperty("测光模式")
    private String meteringMode;

    @Schema(description = "焦距")
    @ExcelProperty("焦距")
    private String focalLength;

    @Schema(description = "拍摄距离范围")
    @ExcelProperty("拍摄距离范围")
    private String subjectDistanceRange;

    @Schema(description = "场景捕捉类型", example = "2")
    @ExcelProperty("场景捕捉类型")
    private String sceneCaptureType;

    @Schema(description = "拍摄时间")
    @ExcelProperty("拍摄时间")
    private String datetimeOriginal;

    @Schema(description = "数字化时间")
    @ExcelProperty("数字化时间")
    private String datetimeDigitized;

    @Schema(description = "场景类型", example = "1")
    @ExcelProperty("场景类型")
    private String sceneType;

    @Schema(description = "感测方式")
    @ExcelProperty("感测方式")
    private String sensingMethod;

}