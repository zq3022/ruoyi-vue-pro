package cn.iocoder.yudao.module.space.service.source;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.space.controller.admin.source.vo.SourcePageReqVO;
import cn.iocoder.yudao.module.space.controller.admin.source.vo.SourceSaveReqVO;
import cn.iocoder.yudao.module.space.convert.source.SpaceSourceConvert;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.dal.mysql.source.SourceMapper;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;
import cn.iocoder.yudao.module.space.mq.producer.SourceProducer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.io.File;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.SOURCE_EXISTS_INCLUSION;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.SOURCE_NOT_EXISTS;

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

    @Resource
    private SourceProducer sourceChangeProducer;

    @Override
    public Long createSource(SourceSaveReqVO createReqVO) {
        // 插入
        checkSourceExistSub(null, createReqVO.getPath(), createReqVO.getType());
        SourceDO source = BeanUtils.toBean(createReqVO, SourceDO.class);
        sourceMapper.insert(source);
        // 发送`新增源`的mq消息
        sourceChangeProducer.sendSourceAddMessage(SpaceSourceConvert.INSTANCE.convert(source).setSourceId(source.getId()));
        // 返回
        return source.getId();
    }

    @Override
    public void updateSource(SourceSaveReqVO updateReqVO) {
        // 校验存在
        SourceDO oldSource = validateSourceExists(updateReqVO.getId());
        checkSourceExistSub(updateReqVO.getId(), updateReqVO.getPath(), updateReqVO.getType());
        // 更新
        SourceDO updateObj = BeanUtils.toBean(updateReqVO, SourceDO.class);
        sourceMapper.updateById(updateObj);
        // 发送`修改源`mq消息
        SourceMessage sourceMessage = SpaceSourceConvert.INSTANCE.convert(updateObj);
        sourceMessage.setSourceId(updateObj.getId());
        sourceMessage.setOldPath(oldSource.getPath());
        sourceChangeProducer.sendSourceUpdateMessage(sourceMessage);
    }

    @Override
    public void deleteSource(Long id) {
        // 校验存在
        SourceDO oldSource = validateSourceExists(id);
        // 删除
        sourceMapper.deleteById(id);
        // 发送`删除源`mq消息
        sourceChangeProducer.sendSourceDeleteMessage(SpaceSourceConvert.INSTANCE.convert(oldSource).setSourceId(oldSource.getId()));
    }

    private SourceDO validateSourceExists(Long id) {
        SourceDO sourceDO = sourceMapper.selectById(id);
        if (sourceDO == null) {
            throw exception(SOURCE_NOT_EXISTS);
        }
        return sourceDO;
    }

    // 检查需要新增/修改的目录源是否存在包含关系的源
    private void checkSourceExistSub(Long oldSourceId, String newPath, Integer newType) {
        String newPathStr = new File(newPath).getAbsolutePath();
        LambdaQueryWrapper<SourceDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SourceDO::getType, newType)
                .ne(oldSourceId != null, SourceDO::getId, oldSourceId)
               .and(w -> w.likeRight(SourceDO::getPath, newPathStr).or().apply(" path = left({0}, char_length(path)) ",newPath));
        boolean exists = sourceMapper.exists(queryWrapper);
        if (exists) {
            throw exception(SOURCE_EXISTS_INCLUSION);
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
