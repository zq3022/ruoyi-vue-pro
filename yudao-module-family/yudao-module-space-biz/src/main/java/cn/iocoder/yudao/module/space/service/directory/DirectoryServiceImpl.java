package cn.iocoder.yudao.module.space.service.directory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.space.dal.mysql.directory.DirectoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.*;

/**
 * 目录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DirectoryServiceImpl implements DirectoryService {

    @Resource
    private DirectoryMapper directoryMapper;

    @Override
    public Long createDirectory(DirectorySaveReqVO createReqVO) {
        // 插入
        DirectoryDO directory = BeanUtils.toBean(createReqVO, DirectoryDO.class);
        directoryMapper.insert(directory);
        // 返回
        return directory.getId();
    }

    @Override
    public void updateDirectory(DirectorySaveReqVO updateReqVO) {
        // 校验存在
        validateDirectoryExists(updateReqVO.getId());
        // 更新
        DirectoryDO updateObj = BeanUtils.toBean(updateReqVO, DirectoryDO.class);
        directoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteDirectory(Long id) {
        // 校验存在
        validateDirectoryExists(id);
        // 删除
        directoryMapper.deleteById(id);
    }

    private void validateDirectoryExists(Long id) {
        if (directoryMapper.selectById(id) == null) {
            throw exception(DIRECTORY_NOT_EXISTS);
        }
    }

    @Override
    public DirectoryDO getDirectory(Long id) {
        return directoryMapper.selectById(id);
    }

    @Override
    public PageResult<DirectoryDO> getDirectoryPage(DirectoryPageReqVO pageReqVO) {
        return directoryMapper.selectPage(pageReqVO);
    }

}