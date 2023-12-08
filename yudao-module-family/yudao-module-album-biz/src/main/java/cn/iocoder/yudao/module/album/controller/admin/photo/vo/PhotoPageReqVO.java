package cn.iocoder.yudao.module.album.controller.admin.photo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 相册照片分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PhotoPageReqVO extends PageParam {

    @Schema(description = "目录编号", example = "4469")
    private Long directoryId;

    @Schema(description = "文件类型", example = "李四")
    private String detectedFileTypeName;

    @Schema(description = "MIME类型", example = "2")
    private String detectedMimeType;

    @Schema(description = "文件后缀")
    private String expectedFileNameExtension;

    @Schema(description = "图像描述", example = "你猜")
    private String imageDescription;

    @Schema(description = "生产商")
    private String make;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "作者")
    private String artist;

    @Schema(description = "水平方向分辨率")
    private String xResolution;

    @Schema(description = "垂直方向分辨率")
    private String yResolution;

    @Schema(description = "软件")
    private String software;

    @Schema(description = "白点")
    private String whitePoint;

    @Schema(description = "主色度")
    private String primaryChromaticities;

    @Schema(description = "YCbCr系数")
    private String ycbcrCoefficients;

    @Schema(description = "YCbCr定位")
    private String ycbcrPositioning;

    @Schema(description = "版权")
    private String copyright;

    @Schema(description = "曝光时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] exposureTime;

    @Schema(description = "光圈系数")
    private String fnumber;

    @Schema(description = "曝光程序")
    private String exposureProgram;

    @Schema(description = "闪光灯")
    private String flash;

    @Schema(description = "镜头厂商")
    private String lensMake;

    @Schema(description = "镜头型号")
    private String lensModel;

    @Schema(description = "厂商注释")
    private String makernote;

    @Schema(description = "用户注释")
    private String userComment;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "文件名称", example = "李四")
    private String fileName;

    @Schema(description = "文件大小字节数")
    private String fileSize;

    @Schema(description = "图片宽度，像素")
    private String imageWidth;

    @Schema(description = "图片高度，像素")
    private String imageHeight;

    @Schema(description = "文件修改时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] fileModifiedDate;

    @Schema(description = "文件来源")
    private String fileSource;

    @Schema(description = "压缩类型", example = "2")
    private String compressionType;

    @Schema(description = "数据精度")
    private String dataPrecision;

    @Schema(description = "压缩比")
    private String compressedBitsPerPixel;

    @Schema(description = "分辨率单位")
    private String resolutionUnits;

    @Schema(description = "方向")
    private String orientation;

    @Schema(description = "曝光补偿")
    private String exposureBiasValue;

    @Schema(description = "曝光模式")
    private String exposureMode;

    @Schema(description = "色彩")
    private String colorSpace;

    @Schema(description = "饱和度")
    private String saturation;

    @Schema(description = "亮度")
    private String brightnessValue;

    @Schema(description = "白平衡")
    private String whiteBalance;

    @Schema(description = "白平衡模式")
    private String whiteBalanceMode;

    @Schema(description = "光圈")
    private String apertureValue;

    @Schema(description = "最大光圈值")
    private String maxApertureValue;

    @Schema(description = "感光度")
    private String isoSpeedRatings;

    @Schema(description = "对比度")
    private String contrast;

    @Schema(description = "锯齿")
    private String sharpness;

    @Schema(description = "变焦")
    private String digitalZoomRatio;

    @Schema(description = "快门")
    private String shutterSpeedValue;

    @Schema(description = "测光模式")
    private String meteringMode;

    @Schema(description = "焦距")
    private String focalLength;

    @Schema(description = "拍摄距离范围")
    private String subjectDistanceRange;

    @Schema(description = "场景捕捉类型", example = "2")
    private String sceneCaptureType;

    @Schema(description = "拍摄时间")
    private String datetimeOriginal;

    @Schema(description = "数字化时间")
    private String datetimeDigitized;

    @Schema(description = "场景类型", example = "1")
    private String sceneType;

    @Schema(description = "感测方式")
    private String sensingMethod;

}