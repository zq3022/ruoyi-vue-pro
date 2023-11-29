package cn.iocoder.yudao.module.space.convert.source;

import cn.iocoder.yudao.module.space.controller.admin.source.vo.SourceSaveReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpaceSourceConvert {

    SpaceSourceConvert INSTANCE = Mappers.getMapper(SpaceSourceConvert.class);

    SourceMessage convert(SourceDO sourceDO);

    SourceMessage convert(SourceSaveReqVO updateReqVO);
}
