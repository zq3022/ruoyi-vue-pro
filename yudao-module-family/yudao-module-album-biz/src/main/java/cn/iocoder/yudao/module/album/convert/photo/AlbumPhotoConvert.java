package cn.iocoder.yudao.module.album.convert.photo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.album.controller.admin.photo.vo.PhotoRespVO;
import cn.iocoder.yudao.module.album.dal.dataobject.photo.PhotoDO;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface AlbumPhotoConvert {

    AlbumPhotoConvert INSTANCE = Mappers.getMapper(AlbumPhotoConvert.class);

    PhotoRespVO convert(PhotoDO bean);

    static PhotoDO convert(Metadata metadata){
        if (metadata == null) {
            return new PhotoDO();
        }
        Map<String, Object> tagMap = new HashMap<>();

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                tagMap.put(StrUtil.toCamelCase(tag.getTagName(),' '), tag.getDescription());
            }
        }

        PhotoDO photoDO = BeanUtil.toBean(tagMap, PhotoDO.class, CopyOptions.create().setIgnoreNullValue(true));
        return photoDO;

    }
}
