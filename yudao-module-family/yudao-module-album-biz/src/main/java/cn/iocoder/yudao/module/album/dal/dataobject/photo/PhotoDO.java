package cn.iocoder.yudao.module.album.dal.dataobject.photo;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 相册照片 DO
 *
 * @author koiup
 */
@TableName("album_photo")
@KeySequence("album_photo_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDO extends BaseDO {

    /**
     * 图片编号
     */
    @TableId
    private Long id;
    /**
     * 目录编号
     */
    private Long directoryId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小字节数
     */
    private String fileSize;
    /**
     * 图片宽度，像素
     */
    private String imageWidth;
    /**
     * 图片高度，像素
     */
    private String imageHeight;
    /**
     * 文件修改时间
     */
    private String fileModifiedDate;
    /**
     * 文件类型
     */
    private String detectedFileTypeName;
    /**
     * 文件类型长名
     */
    private String detectedFileTypeLongName;
    /**
     * MIME类型
     */
    private String detectedMimeType;
    /**
     * 文件后缀
     */
    private String expectedFileNameExtension;
    /**
     * 图像描述
     */
    private String imageDescription;
    /**
     * 生产商
     */
    private String make;
    /**
     * 型号
     */
    private String model;
    /**
     * 色彩空间
     */
    private String colorSpace;
    /**
     * 作者
     */
    private String artist;
    /**
     * 方向, 1'upper left', 3'lower right', 6'upper right', 8'lower left', 9'undefined'
     */
    private String orientation;
    /**
     * 水平方向分辨率
     */
    private String xResolution;
    /**
     * 垂直方向分辨率
     */
    private String yResolution;
    /**
     * 分辨率单位
     */
    private String resolutionUnit;
    /**
     * 软件
     */
    private String software;
    /**
     * 白点
     */
    private String whitePoint;
    /**
     * 主色度
     */
    private String primaryChromaticities;
    /**
     * YCbCr系数
     */
    private String ycbcrCoefficients;
    /**
     * YCbCr定位
     */
    private String ycbcrPositioning;
    /**
     * 版权
     */
    private String copyright;
    /**
     * 曝光时间
     */
    private String exposureTime;
    /**
     * 光圈系数
     */
    private String fnumber;
    /**
     * 曝光程序
     */
    private String exposureProgram;
    /**
     * Exif版本
     */
    private String exifVersion;
    /**
     * 拍摄时间
     */
    private String datetimeOriginal;
    /**
     * 数字化时间
     */
    private String datetimeDigitized;
    /**
     * 测光模式（平均测光、中央重点测光、点测光）
     */
    private String meteringMode;
    /**
     * 闪光灯
     */
    private String flash;
    /**
     * 镜头焦距
     */
    private String focalLength;
    /**
     * 图像宽度
     */
    private String exifImageWidth;
    /**
     * 图像高度
     */
    private String exifImageLength;
    /**
     * 镜头厂商
     */
    private String lensMake;
    /**
     * 镜头型号
     */
    private String lensModel;
    /**
     * 厂商注释
     */
    private String makernote;
    /**
     * 用户注释
     */
    private String userComment;

}
