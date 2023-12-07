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

    @Schema(description = "文件名称", example = "王五")
    private String fileName;

    @Schema(description = "文件大小字节数")
    private Long fileSize;

    @Schema(description = "图片宽度，像素")
    private Integer imageWidth;

    @Schema(description = "图片高度，像素")
    private Integer imageHeight;

    @Schema(description = "文件修改时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] fileModifiedDate;

    @Schema(description = "文件类型", example = "李四")
    private String detectedFileTypeName;

    @Schema(description = "文件类型长名", example = "张三")
    private String detectedFileTypeLongName;

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

    @Schema(description = "色彩空间")
    private String colorSpace;

    @Schema(description = "作者")
    private String artist;

    @Schema(description = "方向, 1'upper left', 3'lower right', 6'upper right', 8'lower left', 9'undefined'")
    private String orientation;

    @Schema(description = "水平方向分辨率")
    private String xResolution;

    @Schema(description = "垂直方向分辨率")
    private String yResolution;

    @Schema(description = "分辨率单位")
    private String resolutionUnit;

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

    @Schema(description = "Exif版本")
    private String exifVersion;

    @Schema(description = "拍摄时间")
    private LocalDateTime datetimeOriginal;

    @Schema(description = "数字化时间")
    private LocalDateTime datetimeDigitized;

    @Schema(description = "测光模式（平均测光、中央重点测光、点测光）")
    private String meteringMode;

    @Schema(description = "闪光灯")
    private String flash;

    @Schema(description = "镜头焦距")
    private String focalLength;

    @Schema(description = "图像宽度")
    private Integer exifImageWidth;

    @Schema(description = "图像高度")
    private Integer exifImageLength;

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

}