package cn.iocoder.yudao.module.space.service.directory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectoryPageReqVO;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectorySaveReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;

import javax.validation.Valid;
import java.io.IOException;

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

    /**
     * 处理目录源的消息
     *
     * @param message 源变更 消息
     */
    void doSourceMessage(SourceMessage message) throws IOException;

    /**
     * 获取目录的全路径
     *
     * @param directoryId 目录id
     */
    String getFullPath(Long directoryId);
}
