package cn.iocoder.yudao.module.cf.service.playlist;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cf.controller.admin.playlist.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.playlist.PlaylistDO;
import cn.iocoder.yudao.module.cf.dal.mysql.playlist.PlaylistMapper;
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
 * {@link PlaylistServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(PlaylistServiceImpl.class)
public class PlaylistServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PlaylistServiceImpl playlistService;

    @Resource
    private PlaylistMapper playlistMapper;

    @Test
    public void testCreatePlaylist_success() {
        // 准备参数
        PlaylistSaveReqVO createReqVO = randomPojo(PlaylistSaveReqVO.class).setId(null);

        // 调用
        Long playlistId = playlistService.createPlaylist(createReqVO);
        // 断言
        assertNotNull(playlistId);
        // 校验记录的属性是否正确
        PlaylistDO playlist = playlistMapper.selectById(playlistId);
        assertPojoEquals(createReqVO, playlist, "id");
    }

    @Test
    public void testUpdatePlaylist_success() {
        // mock 数据
        PlaylistDO dbPlaylist = randomPojo(PlaylistDO.class);
        playlistMapper.insert(dbPlaylist);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PlaylistSaveReqVO updateReqVO = randomPojo(PlaylistSaveReqVO.class, o -> {
            o.setId(dbPlaylist.getId()); // 设置更新的 ID
        });

        // 调用
        playlistService.updatePlaylist(updateReqVO);
        // 校验是否更新正确
        PlaylistDO playlist = playlistMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, playlist);
    }

    @Test
    public void testUpdatePlaylist_notExists() {
        // 准备参数
        PlaylistSaveReqVO updateReqVO = randomPojo(PlaylistSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> playlistService.updatePlaylist(updateReqVO), PLAYLIST_NOT_EXISTS);
    }

    @Test
    public void testDeletePlaylist_success() {
        // mock 数据
        PlaylistDO dbPlaylist = randomPojo(PlaylistDO.class);
        playlistMapper.insert(dbPlaylist);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPlaylist.getId();

        // 调用
        playlistService.deletePlaylist(id);
       // 校验数据不存在了
       assertNull(playlistMapper.selectById(id));
    }

    @Test
    public void testDeletePlaylist_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> playlistService.deletePlaylist(id), PLAYLIST_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPlaylistPage() {
       // mock 数据
       PlaylistDO dbPlaylist = randomPojo(PlaylistDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setName(null);
           o.setCoverImgId(null);
           o.setCoverImgUrl(null);
           o.setAdType(null);
           o.setStatus(null);
           o.setOpRecommend(null);
           o.setHighQuality(null);
           o.setNewImported(null);
           o.setTrackCount(null);
           o.setPrivacy(null);
           o.setTrackUpdateTime(null);
           o.setCommentThreadId(null);
           o.setPlayCount(null);
           o.setTrackNumberUpdateTime(null);
           o.setSubscribedCount(null);
           o.setCloudTrackCount(null);
           o.setOrdered(null);
           o.setDescription(null);
           o.setTags(null);
           o.setBackgroundCoverId(null);
           o.setTitleImage(null);
           o.setSubscribed(null);
           o.setShareCount(null);
           o.setCommentCount(null);
           o.setCreateTime(null);
           o.setSpecialType(null);
       });
       playlistMapper.insert(dbPlaylist);
       // 测试 userId 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setUserId(null)));
       // 测试 name 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setName(null)));
       // 测试 coverImgId 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setCoverImgId(null)));
       // 测试 coverImgUrl 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setCoverImgUrl(null)));
       // 测试 adType 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setAdType(null)));
       // 测试 status 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setStatus(null)));
       // 测试 opRecommend 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setOpRecommend(null)));
       // 测试 highQuality 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setHighQuality(null)));
       // 测试 newImported 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setNewImported(null)));
       // 测试 trackCount 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setTrackCount(null)));
       // 测试 privacy 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setPrivacy(null)));
       // 测试 trackUpdateTime 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setTrackUpdateTime(null)));
       // 测试 commentThreadId 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setCommentThreadId(null)));
       // 测试 playCount 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setPlayCount(null)));
       // 测试 trackNumberUpdateTime 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setTrackNumberUpdateTime(null)));
       // 测试 subscribedCount 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setSubscribedCount(null)));
       // 测试 cloudTrackCount 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setCloudTrackCount(null)));
       // 测试 ordered 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setOrdered(null)));
       // 测试 description 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setDescription(null)));
       // 测试 tags 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setTags(null)));
       // 测试 backgroundCoverId 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setBackgroundCoverId(null)));
       // 测试 titleImage 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setTitleImage(null)));
       // 测试 subscribed 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setSubscribed(null)));
       // 测试 shareCount 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setShareCount(null)));
       // 测试 commentCount 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setCommentCount(null)));
       // 测试 createTime 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setCreateTime(null)));
       // 测试 specialType 不匹配
       playlistMapper.insert(cloneIgnoreId(dbPlaylist, o -> o.setSpecialType(null)));
       // 准备参数
       PlaylistPageReqVO reqVO = new PlaylistPageReqVO();
       reqVO.setUserId(null);
       reqVO.setName(null);
       reqVO.setCoverImgId(null);
       reqVO.setCoverImgUrl(null);
       reqVO.setAdType(null);
       reqVO.setStatus(null);
       reqVO.setOpRecommend(null);
       reqVO.setHighQuality(null);
       reqVO.setNewImported(null);
       reqVO.setTrackCount(null);
       reqVO.setPrivacy(null);
//       reqVO.setTrackUpdateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCommentThreadId(null);
       reqVO.setPlayCount(null);
//       reqVO.setTrackNumberUpdateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setSubscribedCount(null);
       reqVO.setCloudTrackCount(null);
       reqVO.setOrdered(null);
       reqVO.setDescription(null);
       reqVO.setTags(null);
       reqVO.setBackgroundCoverId(null);
       reqVO.setTitleImage(null);
       reqVO.setSubscribed(null);
       reqVO.setShareCount(null);
       reqVO.setCommentCount(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setSpecialType(null);

       // 调用
       PageResult<PlaylistDO> pageResult = playlistService.getPlaylistPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPlaylist, pageResult.getList().get(0));
    }

}
