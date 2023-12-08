package cn.iocoder.yudao.module.album.service.photo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.album.controller.admin.photo.vo.*;
import cn.iocoder.yudao.module.album.dal.dataobject.photo.PhotoDO;
import cn.iocoder.yudao.module.album.dal.mysql.photo.PhotoMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.album.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link PhotoServiceImpl} 的单元测试类
 *
 * @author koiup
 */
@Import(PhotoServiceImpl.class)
public class PhotoServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PhotoServiceImpl photoService;

    @Resource
    private PhotoMapper photoMapper;

    @Test
    public void testCreatePhoto_success() {
        // 准备参数
        PhotoSaveReqVO createReqVO = randomPojo(PhotoSaveReqVO.class).setId(null);

        // 调用
        Long photoId = photoService.createPhoto(createReqVO);
        // 断言
        assertNotNull(photoId);
        // 校验记录的属性是否正确
        PhotoDO photo = photoMapper.selectById(photoId);
        assertPojoEquals(createReqVO, photo, "id");
    }

    @Test
    public void testUpdatePhoto_success() {
        // mock 数据
        PhotoDO dbPhoto = randomPojo(PhotoDO.class);
        photoMapper.insert(dbPhoto);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PhotoSaveReqVO updateReqVO = randomPojo(PhotoSaveReqVO.class, o -> {
            o.setId(dbPhoto.getId()); // 设置更新的 ID
        });

        // 调用
        photoService.updatePhoto(updateReqVO);
        // 校验是否更新正确
        PhotoDO photo = photoMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, photo);
    }

    @Test
    public void testUpdatePhoto_notExists() {
        // 准备参数
        PhotoSaveReqVO updateReqVO = randomPojo(PhotoSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> photoService.updatePhoto(updateReqVO), PHOTO_NOT_EXISTS);
    }

    @Test
    public void testDeletePhoto_success() {
        // mock 数据
        PhotoDO dbPhoto = randomPojo(PhotoDO.class);
        photoMapper.insert(dbPhoto);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPhoto.getId();

        // 调用
        photoService.deletePhoto(id);
       // 校验数据不存在了
       assertNull(photoMapper.selectById(id));
    }

    @Test
    public void testDeletePhoto_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> photoService.deletePhoto(id), PHOTO_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPhotoPage() {
       // mock 数据
       PhotoDO dbPhoto = randomPojo(PhotoDO.class, o -> { // 等会查询到
           o.setDirectoryId(null);
           o.setDetectedFileTypeName(null);
           o.setDetectedMimeType(null);
           o.setExpectedFileNameExtension(null);
           o.setImageDescription(null);
           o.setMake(null);
           o.setModel(null);
           o.setArtist(null);
           o.setXResolution(null);
           o.setYResolution(null);
           o.setSoftware(null);
           o.setWhitePoint(null);
           o.setPrimaryChromaticities(null);
           o.setYcbcrCoefficients(null);
           o.setYcbcrPositioning(null);
           o.setCopyright(null);
           o.setExposureTime(null);
           o.setFnumber(null);
           o.setExposureProgram(null);
           o.setFlash(null);
           o.setLensMake(null);
           o.setLensModel(null);
           o.setMakernote(null);
           o.setUserComment(null);
           o.setCreateTime(null);
           o.setFileName(null);
           o.setFileSize(null);
           o.setImageWidth(null);
           o.setImageHeight(null);
           o.setFileModifiedDate(null);
           o.setFileSource(null);
           o.setCompressionType(null);
           o.setDataPrecision(null);
           o.setCompressedBitsPerPixel(null);
           o.setResolutionUnits(null);
           o.setOrientation(null);
           o.setExposureBiasValue(null);
           o.setExposureMode(null);
           o.setColorSpace(null);
           o.setSaturation(null);
           o.setBrightnessValue(null);
           o.setWhiteBalance(null);
           o.setWhiteBalanceMode(null);
           o.setApertureValue(null);
           o.setMaxApertureValue(null);
           o.setIsoSpeedRatings(null);
           o.setContrast(null);
           o.setSharpness(null);
           o.setDigitalZoomRatio(null);
           o.setShutterSpeedValue(null);
           o.setMeteringMode(null);
           o.setFocalLength(null);
           o.setSubjectDistanceRange(null);
           o.setSceneCaptureType(null);
           o.setDatetimeOriginal(null);
           o.setDatetimeDigitized(null);
           o.setSceneType(null);
           o.setSensingMethod(null);
       });
       photoMapper.insert(dbPhoto);
       // 测试 directoryId 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setDirectoryId(null)));
       // 测试 detectedFileTypeName 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setDetectedFileTypeName(null)));
       // 测试 detectedMimeType 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setDetectedMimeType(null)));
       // 测试 expectedFileNameExtension 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setExpectedFileNameExtension(null)));
       // 测试 imageDescription 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setImageDescription(null)));
       // 测试 make 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setMake(null)));
       // 测试 model 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setModel(null)));
       // 测试 artist 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setArtist(null)));
       // 测试 xResolution 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setXResolution(null)));
       // 测试 yResolution 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setYResolution(null)));
       // 测试 software 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setSoftware(null)));
       // 测试 whitePoint 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setWhitePoint(null)));
       // 测试 primaryChromaticities 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setPrimaryChromaticities(null)));
       // 测试 ycbcrCoefficients 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setYcbcrCoefficients(null)));
       // 测试 ycbcrPositioning 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setYcbcrPositioning(null)));
       // 测试 copyright 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setCopyright(null)));
       // 测试 exposureTime 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setExposureTime(null)));
       // 测试 fnumber 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setFnumber(null)));
       // 测试 exposureProgram 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setExposureProgram(null)));
       // 测试 flash 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setFlash(null)));
       // 测试 lensMake 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setLensMake(null)));
       // 测试 lensModel 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setLensModel(null)));
       // 测试 makernote 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setMakernote(null)));
       // 测试 userComment 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setUserComment(null)));
       // 测试 createTime 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setCreateTime(null)));
       // 测试 fileName 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setFileName(null)));
       // 测试 fileSize 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setFileSize(null)));
       // 测试 imageWidth 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setImageWidth(null)));
       // 测试 imageHeight 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setImageHeight(null)));
       // 测试 fileModifiedDate 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setFileModifiedDate(null)));
       // 测试 fileSource 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setFileSource(null)));
       // 测试 compressionType 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setCompressionType(null)));
       // 测试 dataPrecision 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setDataPrecision(null)));
       // 测试 compressedBitsPerPixel 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setCompressedBitsPerPixel(null)));
       // 测试 resolutionUnits 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setResolutionUnits(null)));
       // 测试 orientation 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setOrientation(null)));
       // 测试 exposureBiasValue 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setExposureBiasValue(null)));
       // 测试 exposureMode 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setExposureMode(null)));
       // 测试 colorSpace 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setColorSpace(null)));
       // 测试 saturation 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setSaturation(null)));
       // 测试 brightnessValue 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setBrightnessValue(null)));
       // 测试 whiteBalance 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setWhiteBalance(null)));
       // 测试 whiteBalanceMode 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setWhiteBalanceMode(null)));
       // 测试 apertureValue 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setApertureValue(null)));
       // 测试 maxApertureValue 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setMaxApertureValue(null)));
       // 测试 isoSpeedRatings 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setIsoSpeedRatings(null)));
       // 测试 contrast 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setContrast(null)));
       // 测试 sharpness 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setSharpness(null)));
       // 测试 digitalZoomRatio 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setDigitalZoomRatio(null)));
       // 测试 shutterSpeedValue 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setShutterSpeedValue(null)));
       // 测试 meteringMode 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setMeteringMode(null)));
       // 测试 focalLength 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setFocalLength(null)));
       // 测试 subjectDistanceRange 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setSubjectDistanceRange(null)));
       // 测试 sceneCaptureType 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setSceneCaptureType(null)));
       // 测试 datetimeOriginal 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setDatetimeOriginal(null)));
       // 测试 datetimeDigitized 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setDatetimeDigitized(null)));
       // 测试 sceneType 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setSceneType(null)));
       // 测试 sensingMethod 不匹配
       photoMapper.insert(cloneIgnoreId(dbPhoto, o -> o.setSensingMethod(null)));
       // 准备参数
       PhotoPageReqVO reqVO = new PhotoPageReqVO();
       reqVO.setDirectoryId(null);
       reqVO.setDetectedFileTypeName(null);
       reqVO.setDetectedMimeType(null);
       reqVO.setExpectedFileNameExtension(null);
       reqVO.setImageDescription(null);
       reqVO.setMake(null);
       reqVO.setModel(null);
       reqVO.setArtist(null);
       reqVO.setXResolution(null);
       reqVO.setYResolution(null);
       reqVO.setSoftware(null);
       reqVO.setWhitePoint(null);
       reqVO.setPrimaryChromaticities(null);
       reqVO.setYcbcrCoefficients(null);
       reqVO.setYcbcrPositioning(null);
       reqVO.setCopyright(null);
//       reqVO.setExposureTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setFnumber(null);
       reqVO.setExposureProgram(null);
       reqVO.setFlash(null);
       reqVO.setLensMake(null);
       reqVO.setLensModel(null);
       reqVO.setMakernote(null);
       reqVO.setUserComment(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setFileName(null);
       reqVO.setFileSize(null);
       reqVO.setImageWidth(null);
       reqVO.setImageHeight(null);
//       reqVO.setFileModifiedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setFileSource(null);
       reqVO.setCompressionType(null);
       reqVO.setDataPrecision(null);
       reqVO.setCompressedBitsPerPixel(null);
       reqVO.setResolutionUnits(null);
       reqVO.setOrientation(null);
       reqVO.setExposureBiasValue(null);
       reqVO.setExposureMode(null);
       reqVO.setColorSpace(null);
       reqVO.setSaturation(null);
       reqVO.setBrightnessValue(null);
       reqVO.setWhiteBalance(null);
       reqVO.setWhiteBalanceMode(null);
       reqVO.setApertureValue(null);
       reqVO.setMaxApertureValue(null);
       reqVO.setIsoSpeedRatings(null);
       reqVO.setContrast(null);
       reqVO.setSharpness(null);
       reqVO.setDigitalZoomRatio(null);
       reqVO.setShutterSpeedValue(null);
       reqVO.setMeteringMode(null);
       reqVO.setFocalLength(null);
       reqVO.setSubjectDistanceRange(null);
       reqVO.setSceneCaptureType(null);
       reqVO.setDatetimeOriginal(null);
       reqVO.setDatetimeDigitized(null);
       reqVO.setSceneType(null);
       reqVO.setSensingMethod(null);

       // 调用
       PageResult<PhotoDO> pageResult = photoService.getPhotoPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPhoto, pageResult.getList().get(0));
    }

}
