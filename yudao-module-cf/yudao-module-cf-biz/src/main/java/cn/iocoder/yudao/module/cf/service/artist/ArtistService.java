package cn.iocoder.yudao.module.cf.service.artist;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cf.controller.admin.artist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.artist.ArtistDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 艺术家 Service 接口
 *
 * @author 芋道源码
 */
public interface ArtistService {

    /**
     * 创建艺术家
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArtist(@Valid ArtistSaveReqVO createReqVO);

    /**
     * 更新艺术家
     *
     * @param updateReqVO 更新信息
     */
    void updateArtist(@Valid ArtistSaveReqVO updateReqVO);

    /**
     * 删除艺术家
     *
     * @param id 编号
     */
    void deleteArtist(Long id);

    /**
     * 获得艺术家
     *
     * @param id 编号
     * @return 艺术家
     */
    ArtistDO getArtist(Long id);

    /**
     * 获得艺术家分页
     *
     * @param pageReqVO 分页查询
     * @return 艺术家分页
     */
    PageResult<ArtistDO> getArtistPage(ArtistPageReqVO pageReqVO);

}