package cn.iocoder.yudao.module.space.service.directory;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 目录 Service 接口
 *
 * @author 芋道源码
 */
public interface DirectoryService {

    /**
     * 创建目录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDirectory(@Valid DirectorySaveReqVO createReqVO);

    /**
     * 更新目录
     *
     * @param updateReqVO 更新信息
     */
    void updateDirectory(@Valid DirectorySaveReqVO updateReqVO);

    /**
     * 删除目录
     *
     * @param id 编号
     */
    void deleteDirectory(Long id);

    /**
     * 获得目录
     *
     * @param id 编号
     * @return 目录
     */
    DirectoryDO getDirectory(Long id);

    /**
     * 获得目录分页
     *
     * @param pageReqVO 分页查询
     * @return 目录分页
     */
    PageResult<DirectoryDO> getDirectoryPage(DirectoryPageReqVO pageReqVO);

}