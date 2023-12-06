package cn.iocoder.yudao.module.space.api.directory;

import cn.iocoder.yudao.module.space.service.directory.DirectoryService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 目录 API 接口实现类
 *
 * @author owen
 */
@Service
@Validated
public class DirectoryApiImpl implements DirectoryApi {
    @Resource
    private DirectoryService directoryService;

    @Override
    public String getFullPath(Long directoryId) {
        return directoryService.getFullPath(directoryId);
    }
}
