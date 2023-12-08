package cn.iocoder.yudao.module.album.dal.mysql.photo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.album.dal.dataobject.photo.PhotoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.album.controller.admin.photo.vo.*;

/**
 * 相册照片 Mapper
 *
 * @author koiup
 */
@Mapper
public interface PhotoMapper extends BaseMapperX<PhotoDO> {

    default PageResult<PhotoDO> selectPage(PhotoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PhotoDO>()
                .eqIfPresent(PhotoDO::getDirectoryId, reqVO.getDirectoryId())
                .likeIfPresent(PhotoDO::getDetectedFileTypeName, reqVO.getDetectedFileTypeName())
                .eqIfPresent(PhotoDO::getDetectedMimeType, reqVO.getDetectedMimeType())
                .eqIfPresent(PhotoDO::getExpectedFileNameExtension, reqVO.getExpectedFileNameExtension())
                .eqIfPresent(PhotoDO::getImageDescription, reqVO.getImageDescription())
                .eqIfPresent(PhotoDO::getMake, reqVO.getMake())
                .eqIfPresent(PhotoDO::getModel, reqVO.getModel())
                .eqIfPresent(PhotoDO::getArtist, reqVO.getArtist())
                .eqIfPresent(PhotoDO::getXResolution, reqVO.getXResolution())
                .eqIfPresent(PhotoDO::getYResolution, reqVO.getYResolution())
                .eqIfPresent(PhotoDO::getSoftware, reqVO.getSoftware())
                .eqIfPresent(PhotoDO::getWhitePoint, reqVO.getWhitePoint())
                .eqIfPresent(PhotoDO::getPrimaryChromaticities, reqVO.getPrimaryChromaticities())
                .eqIfPresent(PhotoDO::getYcbcrCoefficients, reqVO.getYcbcrCoefficients())
                .eqIfPresent(PhotoDO::getYcbcrPositioning, reqVO.getYcbcrPositioning())
                .eqIfPresent(PhotoDO::getCopyright, reqVO.getCopyright())
                .betweenIfPresent(PhotoDO::getExposureTime, reqVO.getExposureTime())
                .eqIfPresent(PhotoDO::getFnumber, reqVO.getFnumber())
                .eqIfPresent(PhotoDO::getExposureProgram, reqVO.getExposureProgram())
                .eqIfPresent(PhotoDO::getFlash, reqVO.getFlash())
                .eqIfPresent(PhotoDO::getLensMake, reqVO.getLensMake())
                .eqIfPresent(PhotoDO::getLensModel, reqVO.getLensModel())
                .eqIfPresent(PhotoDO::getMakernote, reqVO.getMakernote())
                .eqIfPresent(PhotoDO::getUserComment, reqVO.getUserComment())
                .betweenIfPresent(PhotoDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(PhotoDO::getFileName, reqVO.getFileName())
                .eqIfPresent(PhotoDO::getFileSize, reqVO.getFileSize())
                .eqIfPresent(PhotoDO::getImageWidth, reqVO.getImageWidth())
                .eqIfPresent(PhotoDO::getImageHeight, reqVO.getImageHeight())
                .betweenIfPresent(PhotoDO::getFileModifiedDate, reqVO.getFileModifiedDate())
                .eqIfPresent(PhotoDO::getFileSource, reqVO.getFileSource())
                .eqIfPresent(PhotoDO::getCompressionType, reqVO.getCompressionType())
                .eqIfPresent(PhotoDO::getDataPrecision, reqVO.getDataPrecision())
                .eqIfPresent(PhotoDO::getCompressedBitsPerPixel, reqVO.getCompressedBitsPerPixel())
                .eqIfPresent(PhotoDO::getResolutionUnits, reqVO.getResolutionUnits())
                .eqIfPresent(PhotoDO::getOrientation, reqVO.getOrientation())
                .eqIfPresent(PhotoDO::getExposureBiasValue, reqVO.getExposureBiasValue())
                .eqIfPresent(PhotoDO::getExposureMode, reqVO.getExposureMode())
                .eqIfPresent(PhotoDO::getColorSpace, reqVO.getColorSpace())
                .eqIfPresent(PhotoDO::getSaturation, reqVO.getSaturation())
                .eqIfPresent(PhotoDO::getBrightnessValue, reqVO.getBrightnessValue())
                .eqIfPresent(PhotoDO::getWhiteBalance, reqVO.getWhiteBalance())
                .eqIfPresent(PhotoDO::getWhiteBalanceMode, reqVO.getWhiteBalanceMode())
                .eqIfPresent(PhotoDO::getApertureValue, reqVO.getApertureValue())
                .eqIfPresent(PhotoDO::getMaxApertureValue, reqVO.getMaxApertureValue())
                .eqIfPresent(PhotoDO::getIsoSpeedRatings, reqVO.getIsoSpeedRatings())
                .eqIfPresent(PhotoDO::getContrast, reqVO.getContrast())
                .eqIfPresent(PhotoDO::getSharpness, reqVO.getSharpness())
                .eqIfPresent(PhotoDO::getDigitalZoomRatio, reqVO.getDigitalZoomRatio())
                .eqIfPresent(PhotoDO::getShutterSpeedValue, reqVO.getShutterSpeedValue())
                .eqIfPresent(PhotoDO::getMeteringMode, reqVO.getMeteringMode())
                .eqIfPresent(PhotoDO::getFocalLength, reqVO.getFocalLength())
                .eqIfPresent(PhotoDO::getSubjectDistanceRange, reqVO.getSubjectDistanceRange())
                .eqIfPresent(PhotoDO::getSceneCaptureType, reqVO.getSceneCaptureType())
                .eqIfPresent(PhotoDO::getDatetimeOriginal, reqVO.getDatetimeOriginal())
                .eqIfPresent(PhotoDO::getDatetimeDigitized, reqVO.getDatetimeDigitized())
                .eqIfPresent(PhotoDO::getSceneType, reqVO.getSceneType())
                .eqIfPresent(PhotoDO::getSensingMethod, reqVO.getSensingMethod())
                .orderByDesc(PhotoDO::getId));
    }

}