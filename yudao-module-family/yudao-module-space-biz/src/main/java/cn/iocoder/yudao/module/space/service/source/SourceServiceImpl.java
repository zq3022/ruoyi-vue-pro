package cn.iocoder.yudao.module.space.service.source;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.space.controller.admin.source.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.space.dal.mysql.source.SourceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.*;

/**
 * 目录源 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SourceServiceImpl implements SourceService {

    @Resource
    private SourceMapper sourceMapper;

    @Override
    public Long createSource(SourceSaveReqVO createReqVO) {
        // 插入
        SourceDO source = BeanUtils.toBean(createReqVO, SourceDO.class);
        sourceMapper.insert(source);
        // 返回
        return source.getId();
    }

    @Override
    public void updateSource(SourceSaveReqVO updateReqVO) {
        // 校验存在
        validateSourceExists(updateReqVO.getId());
        // 更新
        SourceDO updateObj = BeanUtils.toBean(updateReqVO, SourceDO.class);
        sourceMapper.updateById(updateObj);
    }

    @Override
    public void deleteSource(Long id) {
        // 校验存在
        validateSourceExists(id);
        // 删除
        sourceMapper.deleteById(id);
    }

    private void validateSourceExists(Long id) {
        if (sourceMapper.selectById(id) == null) {
            throw exception(SOURCE_NOT_EXISTS);
        }
    }

    @Override
    public SourceDO getSource(Long id) {
        return sourceMapper.selectById(id);
    }

    @Override
    public PageResult<SourceDO> getSourcePage(SourcePageReqVO pageReqVO) {
        return sourceMapper.selectPage(pageReqVO);
    }

}