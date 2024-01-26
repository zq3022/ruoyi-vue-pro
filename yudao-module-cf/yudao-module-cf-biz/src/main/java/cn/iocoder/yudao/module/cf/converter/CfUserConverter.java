package cn.iocoder.yudao.module.cf.converter;

import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserPointRespVO;
import cn.iocoder.yudao.module.cf.controller.app.cfuser.vo.AppCfUserProfileRespVO;
import cn.iocoder.yudao.module.cf.dal.dataobject.cfuser.CfUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * cfUser Convert
 *
 * @author koi
 */
@Mapper
public interface CfUserConverter {
    CfUserConverter INSTANCE = Mappers.getMapper(CfUserConverter.class);

//    @Mapping(source = "areaId", target = "areaName", qualifiedByName = "convertAreaIdToAreaName")
    AppCfUserProfileRespVO convert(CfUserDO bean);
}
