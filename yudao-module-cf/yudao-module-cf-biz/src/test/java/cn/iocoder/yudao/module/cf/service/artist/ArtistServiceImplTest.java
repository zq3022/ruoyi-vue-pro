package cn.iocoder.yudao.module.cf.service.artist;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cf.controller.admin.artist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.artist.ArtistDO;
import cn.iocoder.yudao.module.cf.dal.mysql.artist.ArtistMapper;
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
 * {@link ArtistServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ArtistServiceImpl.class)
public class ArtistServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ArtistServiceImpl artistService;

    @Resource
    private ArtistMapper artistMapper;

    @Test
    public void testCreateArtist_success() {
        // 准备参数
        ArtistSaveReqVO createReqVO = randomPojo(ArtistSaveReqVO.class).setId(null);

        // 调用
        Long artistId = artistService.createArtist(createReqVO);
        // 断言
        assertNotNull(artistId);
        // 校验记录的属性是否正确
        ArtistDO artist = artistMapper.selectById(artistId);
        assertPojoEquals(createReqVO, artist, "id");
    }

    @Test
    public void testUpdateArtist_success() {
        // mock 数据
        ArtistDO dbArtist = randomPojo(ArtistDO.class);
        artistMapper.insert(dbArtist);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ArtistSaveReqVO updateReqVO = randomPojo(ArtistSaveReqVO.class, o -> {
            o.setId(dbArtist.getId()); // 设置更新的 ID
        });

        // 调用
        artistService.updateArtist(updateReqVO);
        // 校验是否更新正确
        ArtistDO artist = artistMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, artist);
    }

    @Test
    public void testUpdateArtist_notExists() {
        // 准备参数
        ArtistSaveReqVO updateReqVO = randomPojo(ArtistSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> artistService.updateArtist(updateReqVO), ARTIST_NOT_EXISTS);
    }

    @Test
    public void testDeleteArtist_success() {
        // mock 数据
        ArtistDO dbArtist = randomPojo(ArtistDO.class);
        artistMapper.insert(dbArtist);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbArtist.getId();

        // 调用
        artistService.deleteArtist(id);
       // 校验数据不存在了
       assertNull(artistMapper.selectById(id));
    }

    @Test
    public void testDeleteArtist_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> artistService.deleteArtist(id), ARTIST_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetArtistPage() {
       // mock 数据
       ArtistDO dbArtist = randomPojo(ArtistDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setBriefDesc(null);
           o.setMusicSize(null);
           o.setAlbumSize(null);
           o.setMvSize(null);
           o.setPicId(null);
           o.setPicUrl(null);
           o.setCreateTime(null);
       });
       artistMapper.insert(dbArtist);
       // 测试 name 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setName(null)));
       // 测试 briefDesc 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setBriefDesc(null)));
       // 测试 musicSize 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setMusicSize(null)));
       // 测试 albumSize 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setAlbumSize(null)));
       // 测试 mvSize 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setMvSize(null)));
       // 测试 picId 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setPicId(null)));
       // 测试 picUrl 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setPicUrl(null)));
       // 测试 createTime 不匹配
       artistMapper.insert(cloneIgnoreId(dbArtist, o -> o.setCreateTime(null)));
       // 准备参数
       ArtistPageReqVO reqVO = new ArtistPageReqVO();
       reqVO.setName(null);
       reqVO.setBriefDesc(null);
       reqVO.setMusicSize(null);
       reqVO.setAlbumSize(null);
       reqVO.setMvSize(null);
       reqVO.setPicId(null);
       reqVO.setPicUrl(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ArtistDO> pageResult = artistService.getArtistPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbArtist, pageResult.getList().get(0));
    }

}