package cn.iocoder.yudao.module.space.service.directory;

import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;

/**
 * 目录 Service 接口, 主要处理目录中的文件
 *
 * @author 芋道源码
 */
public interface DirectoryFilesService {

    /**
     * 处理目录变更的消息
     *
     * @param message 目录变更 消息
     */
    void doDirectoryMessage(DirectoryMessage message);
}
