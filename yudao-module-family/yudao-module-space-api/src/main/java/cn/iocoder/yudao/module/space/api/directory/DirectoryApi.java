package cn.iocoder.yudao.module.space.api.directory;

public interface DirectoryApi {

    /**
     * 获取目录的全路径
     *
     * @param directoryId 目录id
     */
    String getFullPath(Long directoryId);
}
