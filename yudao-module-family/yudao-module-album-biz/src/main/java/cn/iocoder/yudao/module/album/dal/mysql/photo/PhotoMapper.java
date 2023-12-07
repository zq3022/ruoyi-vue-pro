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
                .likeIfPresent(PhotoDO::getFileName, reqVO.getFileName())
                .eqIfPresent(PhotoDO::getFileSize, reqVO.getFileSize())
                .eqIfPresent(PhotoDO::getImageWidth, reqVO.getImageWidth())
                .eqIfPresent(PhotoDO::getImageHeight, reqVO.getImageHeight())
                .betweenIfPresent(PhotoDO::getFileModifiedDate, reqVO.getFileModifiedDate())
                .likeIfPresent(PhotoDO::getDetectedFileTypeName, reqVO.getDetectedFileTypeName())
                .likeIfPresent(PhotoDO::getDetectedFileTypeLongName, reqVO.getDetectedFileTypeLongName())
                .eqIfPresent(PhotoDO::getDetectedMimeType, reqVO.getDetectedMimeType())
                .eqIfPresent(PhotoDO::getExpectedFileNameExtension, reqVO.getExpectedFileNameExtension())
                .eqIfPresent(PhotoDO::getImageDescription, reqVO.getImageDescription())
                .eqIfPresent(PhotoDO::getMake, reqVO.getMake())
                .eqIfPresent(PhotoDO::getModel, reqVO.getModel())
                .eqIfPresent(PhotoDO::getColorSpace, reqVO.getColorSpace())
                .eqIfPresent(PhotoDO::getArtist, reqVO.getArtist())
                .eqIfPresent(PhotoDO::getOrientation, reqVO.getOrientation())
                .eqIfPresent(PhotoDO::getXResolution, reqVO.getXResolution())
                .eqIfPresent(PhotoDO::getYResolution, reqVO.getYResolution())
                .eqIfPresent(PhotoDO::getResolutionUnit, reqVO.getResolutionUnit())
                .eqIfPresent(PhotoDO::getSoftware, reqVO.getSoftware())
                .eqIfPresent(PhotoDO::getWhitePoint, reqVO.getWhitePoint())
                .eqIfPresent(PhotoDO::getPrimaryChromaticities, reqVO.getPrimaryChromaticities())
                .eqIfPresent(PhotoDO::getYcbcrCoefficients, reqVO.getYcbcrCoefficients())
                .eqIfPresent(PhotoDO::getYcbcrPositioning, reqVO.getYcbcrPositioning())
                .eqIfPresent(PhotoDO::getCopyright, reqVO.getCopyright())
                .betweenIfPresent(PhotoDO::getExposureTime, reqVO.getExposureTime())
                .eqIfPresent(PhotoDO::getFnumber, reqVO.getFnumber())
                .eqIfPresent(PhotoDO::getExposureProgram, reqVO.getExposureProgram())
                .eqIfPresent(PhotoDO::getExifVersion, reqVO.getExifVersion())
                .eqIfPresent(PhotoDO::getDatetimeOriginal, reqVO.getDatetimeOriginal())
                .eqIfPresent(PhotoDO::getDatetimeDigitized, reqVO.getDatetimeDigitized())
                .eqIfPresent(PhotoDO::getMeteringMode, reqVO.getMeteringMode())
                .eqIfPresent(PhotoDO::getFlash, reqVO.getFlash())
                .eqIfPresent(PhotoDO::getFocalLength, reqVO.getFocalLength())
                .eqIfPresent(PhotoDO::getExifImageWidth, reqVO.getExifImageWidth())
                .eqIfPresent(PhotoDO::getExifImageLength, reqVO.getExifImageLength())
                .eqIfPresent(PhotoDO::getLensMake, reqVO.getLensMake())
                .eqIfPresent(PhotoDO::getLensModel, reqVO.getLensModel())
                .eqIfPresent(PhotoDO::getMakernote, reqVO.getMakernote())
                .eqIfPresent(PhotoDO::getUserComment, reqVO.getUserComment())
                .betweenIfPresent(PhotoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PhotoDO::getId));
    }

}