package cn.iocoder.yudao.module.space.convert.directory;

import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectorySaveReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DirectoryConvert {

    DirectoryConvert INSTANCE = Mappers.getMapper(DirectoryConvert.class);

    DirectorySaveReqVO convert(DirectoryDO directoryDO);

}
