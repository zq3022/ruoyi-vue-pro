package cn.iocoder.yudao.module.space.service.source;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.space.controller.admin.source.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 目录源 Service 接口
 *
 * @author 芋道源码
 */
public interface SourceService {

    /**
     * 创建目录源
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSource(@Valid SourceSaveReqVO createReqVO);

    /**
     * 更新目录源
     *
     * @param updateReqVO 更新信息
     */
    void updateSource(@Valid SourceSaveReqVO updateReqVO);

    /**
     * 删除目录源
     *
     * @param id 编号
     */
    void deleteSource(Long id);

    /**
     * 获得目录源
     *
     * @param id 编号
     * @return 目录源
     */
    SourceDO getSource(Long id);

    /**
     * 获得目录源分页
     *
     * @param pageReqVO 分页查询
     * @return 目录源分页
     */
    PageResult<SourceDO> getSourcePage(SourcePageReqVO pageReqVO);

}