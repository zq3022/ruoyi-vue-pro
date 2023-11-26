package cn.iocoder.yudao.module.space.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpaceConvert {

    SpaceConvert INSTANCE = Mappers.getMapper(SpaceConvert.class);

    // 示例
//    SourceChangeMessage convert(SourceDO sourceDO);
//    SourceChangeMessage convert(SourceSaveReqVO updateReqVO);
}
