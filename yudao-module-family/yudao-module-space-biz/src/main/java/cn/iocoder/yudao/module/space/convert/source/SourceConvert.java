package cn.iocoder.yudao.module.space.convert.source;

import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SourceConvert {

    SourceConvert INSTANCE = Mappers.getMapper(SourceConvert.class);

    SourceMessageVO convert(SourceDO sourceDO);

}
