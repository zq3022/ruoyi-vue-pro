package cn.iocoder.yudao.module.cf.service.album;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cf.controller.admin.album.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.album.AlbumDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 专辑 Service 接口
 *
 * @author 芋道源码
 */
public interface AlbumService {

    /**
     * 创建专辑
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlbum(@Valid AlbumSaveReqVO createReqVO);

    /**
     * 更新专辑
     *
     * @param updateReqVO 更新信息
     */
    void updateAlbum(@Valid AlbumSaveReqVO updateReqVO);

    /**
     * 删除专辑
     *
     * @param id 编号
     */
    void deleteAlbum(Long id);

    /**
     * 获得专辑
     *
     * @param id 编号
     * @return 专辑
     */
    AlbumDO getAlbum(Long id);

    /**
     * 获得专辑分页
     *
     * @param pageReqVO 分页查询
     * @return 专辑分页
     */
    PageResult<AlbumDO> getAlbumPage(AlbumPageReqVO pageReqVO);

}