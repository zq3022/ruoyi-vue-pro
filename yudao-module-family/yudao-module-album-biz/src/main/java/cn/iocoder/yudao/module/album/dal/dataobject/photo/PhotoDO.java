package cn.iocoder.yudao.module.album.dal.dataobject.photo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
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
     * 文件类型
     */
    private String detectedFileTypeName;
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
     * 作者
     */
    private String artist;
    /**
     * 水平方向分辨率
     */
    private String xResolution;
    /**
     * 垂直方向分辨率
     */
    private String yResolution;
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
     * 闪光灯
     */
    private String flash;
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
     * 文件来源
     */
    private String fileSource;
    /**
     * 压缩类型
     */
    private String compressionType;
    /**
     * 数据精度
     */
    private String dataPrecision;
    /**
     * 压缩比
     */
    private String compressedBitsPerPixel;
    /**
     * 分辨率单位
     */
    private String resolutionUnits;
    /**
     * 方向
     */
    private String orientation;
    /**
     * 曝光补偿
     */
    private String exposureBiasValue;
    /**
     * 曝光模式
     */
    private String exposureMode;
    /**
     * 色彩
     */
    private String colorSpace;
    /**
     * 饱和度
     */
    private String saturation;
    /**
     * 亮度
     */
    private String brightnessValue;
    /**
     * 白平衡
     */
    private String whiteBalance;
    /**
     * 白平衡模式
     */
    private String whiteBalanceMode;
    /**
     * 光圈
     */
    private String apertureValue;
    /**
     * 最大光圈值
     */
    private String maxApertureValue;
    /**
     * 感光度
     */
    private String isoSpeedRatings;
    /**
     * 对比度
     */
    private String contrast;
    /**
     * 锯齿
     */
    private String sharpness;
    /**
     * 变焦
     */
    private String digitalZoomRatio;
    /**
     * 快门
     */
    private String shutterSpeedValue;
    /**
     * 测光模式
     */
    private String meteringMode;
    /**
     * 焦距
     */
    private String focalLength;
    /**
     * 拍摄距离范围
     */
    private String subjectDistanceRange;
    /**
     * 场景捕捉类型
     */
    private String sceneCaptureType;
    /**
     * 拍摄时间
     */
    private String datetimeOriginal;
    /**
     * 数字化时间
     */
    private String datetimeDigitized;
    /**
     * 场景类型
     */
    private String sceneType;
    /**
     * 感测方式
     */
    private String sensingMethod;

}