package cn.iocoder.yudao.module.space.convert.source;

import cn.iocoder.yudao.module.space.controller.admin.source.vo.SourceSaveReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.mq.message.source.SourceChangeMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpaceSourceConvert {

    SpaceSourceConvert INSTANCE = Mappers.getMapper(SpaceSourceConvert.class);

    SourceChangeMessage convert(SourceDO sourceDO);

    SourceChangeMessage convert(SourceSaveReqVO updateReqVO);
}
