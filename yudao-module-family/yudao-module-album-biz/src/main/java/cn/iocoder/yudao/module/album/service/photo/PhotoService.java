package cn.iocoder.yudao.module.album.service.photo;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.album.controller.admin.photo.vo.*;
import cn.iocoder.yudao.module.album.dal.dataobject.photo.PhotoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;

/**
 * 相册照片 Service 接口
 *
 * @author koiup
 */
public interface PhotoService {

    /**
     * 创建相册照片
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPhoto(@Valid PhotoSaveReqVO createReqVO);

    /**
     * 更新相册照片
     *
     * @param updateReqVO 更新信息
     */
    void updatePhoto(@Valid PhotoSaveReqVO updateReqVO);

    /**
     * 删除相册照片
     *
     * @param id 编号
     */
    void deletePhoto(Long id);

    /**
     * 获得相册照片
     *
     * @param id 编号
     * @return 相册照片
     */
    PhotoDO getPhoto(Long id);

    /**
     * 获得相册照片分页
     *
     * @param pageReqVO 分页查询
     * @return 相册照片分页
     */
    PageResult<PhotoDO> getPhotoPage(PhotoPageReqVO pageReqVO);


    /**
     * 处理相册目录变更的消息
     *
     * @param message 目录变更 消息
     */
    void doAlbumDirectoryMessage(DirectoryMessage message);

}
