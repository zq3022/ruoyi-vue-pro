package cn.iocoder.yudao.module.cf.service.album;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cf.controller.admin.album.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.album.AlbumDO;
import cn.iocoder.yudao.module.cf.dal.mysql.album.AlbumMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.cf.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link AlbumServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(AlbumServiceImpl.class)
public class AlbumServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AlbumServiceImpl albumService;

    @Resource
    private AlbumMapper albumMapper;

    @Test
    public void testCreateAlbum_success() {
        // 准备参数
        AlbumSaveReqVO createReqVO = randomPojo(AlbumSaveReqVO.class).setId(null);

        // 调用
        Long albumId = albumService.createAlbum(createReqVO);
        // 断言
        assertNotNull(albumId);
        // 校验记录的属性是否正确
        AlbumDO album = albumMapper.selectById(albumId);
        assertPojoEquals(createReqVO, album, "id");
    }

    @Test
    public void testUpdateAlbum_success() {
        // mock 数据
        AlbumDO dbAlbum = randomPojo(AlbumDO.class);
        albumMapper.insert(dbAlbum);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AlbumSaveReqVO updateReqVO = randomPojo(AlbumSaveReqVO.class, o -> {
            o.setId(dbAlbum.getId()); // 设置更新的 ID
        });

        // 调用
        albumService.updateAlbum(updateReqVO);
        // 校验是否更新正确
        AlbumDO album = albumMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, album);
    }

    @Test
    public void testUpdateAlbum_notExists() {
        // 准备参数
        AlbumSaveReqVO updateReqVO = randomPojo(AlbumSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> albumService.updateAlbum(updateReqVO), ALBUM_NOT_EXISTS);
    }

    @Test
    public void testDeleteAlbum_success() {
        // mock 数据
        AlbumDO dbAlbum = randomPojo(AlbumDO.class);
        albumMapper.insert(dbAlbum);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbAlbum.getId();

        // 调用
        albumService.deleteAlbum(id);
       // 校验数据不存在了
       assertNull(albumMapper.selectById(id));
    }

    @Test
    public void testDeleteAlbum_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> albumService.deleteAlbum(id), ALBUM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAlbumPage() {
       // mock 数据
       AlbumDO dbAlbum = randomPojo(AlbumDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPicUrl(null);
           o.setPic(null);
           o.setAlia(null);
           o.setPublishTime(null);
           o.setCompany(null);
           o.setDescription(null);
           o.setCommentCount(null);
           o.setLikedCount(null);
           o.setShareCount(null);
           o.setSongCount(null);
           o.setCreateTime(null);
       });
       albumMapper.insert(dbAlbum);
       // 测试 name 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setName(null)));
       // 测试 picUrl 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setPicUrl(null)));
       // 测试 pic 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setPic(null)));
       // 测试 alia 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setAlia(null)));
       // 测试 publishTime 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setPublishTime(null)));
       // 测试 company 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setCompany(null)));
       // 测试 description 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setDescription(null)));
       // 测试 commentCount 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setCommentCount(null)));
       // 测试 likedCount 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setLikedCount(null)));
       // 测试 shareCount 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setShareCount(null)));
       // 测试 songCount 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setSongCount(null)));
       // 测试 createTime 不匹配
       albumMapper.insert(cloneIgnoreId(dbAlbum, o -> o.setCreateTime(null)));
       // 准备参数
       AlbumPageReqVO reqVO = new AlbumPageReqVO();
       reqVO.setName(null);
       reqVO.setPicUrl(null);
       reqVO.setPic(null);
       reqVO.setAlia(null);
       reqVO.setPublishTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCompany(null);
       reqVO.setDescription(null);
       reqVO.setCommentCount(null);
       reqVO.setLikedCount(null);
       reqVO.setShareCount(null);
       reqVO.setSongCount(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<AlbumDO> pageResult = albumService.getAlbumPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbAlbum, pageResult.getList().get(0));
    }

}