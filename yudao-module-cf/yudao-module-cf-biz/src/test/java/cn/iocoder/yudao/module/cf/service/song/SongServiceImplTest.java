package cn.iocoder.yudao.module.cf.service.song;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cf.controller.admin.song.vo.*;
import cn.iocoder.yudao.module.cf.dal.dataobject.song.SongDO;
import cn.iocoder.yudao.module.cf.dal.mysql.song.SongMapper;
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
 * {@link SongServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(SongServiceImpl.class)
public class SongServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SongServiceImpl songService;

    @Resource
    private SongMapper songMapper;

    @Test
    public void testCreateSong_success() {
        // 准备参数
        SongSaveReqVO createReqVO = randomPojo(SongSaveReqVO.class).setId(null);

        // 调用
        Long songId = songService.createSong(createReqVO);
        // 断言
        assertNotNull(songId);
        // 校验记录的属性是否正确
        SongDO song = songMapper.selectById(songId);
        assertPojoEquals(createReqVO, song, "id");
    }

    @Test
    public void testUpdateSong_success() {
        // mock 数据
        SongDO dbSong = randomPojo(SongDO.class);
        songMapper.insert(dbSong);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SongSaveReqVO updateReqVO = randomPojo(SongSaveReqVO.class, o -> {
            o.setId(dbSong.getId()); // 设置更新的 ID
        });

        // 调用
        songService.updateSong(updateReqVO);
        // 校验是否更新正确
        SongDO song = songMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, song);
    }

    @Test
    public void testUpdateSong_notExists() {
        // 准备参数
        SongSaveReqVO updateReqVO = randomPojo(SongSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> songService.updateSong(updateReqVO), SONG_NOT_EXISTS);
    }

    @Test
    public void testDeleteSong_success() {
        // mock 数据
        SongDO dbSong = randomPojo(SongDO.class);
        songMapper.insert(dbSong);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSong.getId();

        // 调用
        songService.deleteSong(id);
       // 校验数据不存在了
       assertNull(songMapper.selectById(id));
    }

    @Test
    public void testDeleteSong_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> songService.deleteSong(id), SONG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSongPage() {
       // mock 数据
       SongDO dbSong = randomPojo(SongDO.class, o -> { // 等会查询到
           o.setAlbumId(null);
           o.setName(null);
           o.setPst(null);
           o.setT(null);
           o.setPop(null);
           o.setSt(null);
           o.setRt(null);
           o.setFee(null);
           o.setV(null);
           o.setCf(null);
           o.setDt(null);
           o.setCd(null);
           o.setNo(null);
           o.setFtype(null);
           o.setDjId(null);
           o.setCopyright(null);
           o.setSId(null);
           o.setMark(null);
           o.setOriginCoverType(null);
           o.setSingle(null);
           o.setMst(null);
           o.setCp(null);
           o.setMv(null);
           o.setRtype(null);
           o.setPublishTime(null);
           o.setCreateTime(null);
       });
       songMapper.insert(dbSong);
       // 测试 albumId 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setAlbumId(null)));
       // 测试 name 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setName(null)));
       // 测试 pst 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setPst(null)));
       // 测试 t 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setT(null)));
       // 测试 pop 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setPop(null)));
       // 测试 st 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setSt(null)));
       // 测试 rt 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setRt(null)));
       // 测试 fee 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setFee(null)));
       // 测试 v 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setV(null)));
       // 测试 cf 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setCf(null)));
       // 测试 dt 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setDt(null)));
       // 测试 cd 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setCd(null)));
       // 测试 no 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setNo(null)));
       // 测试 ftype 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setFtype(null)));
       // 测试 djId 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setDjId(null)));
       // 测试 copyright 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setCopyright(null)));
       // 测试 sId 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setSId(null)));
       // 测试 mark 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setMark(null)));
       // 测试 originCoverType 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setOriginCoverType(null)));
       // 测试 single 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setSingle(null)));
       // 测试 mst 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setMst(null)));
       // 测试 cp 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setCp(null)));
       // 测试 mv 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setMv(null)));
       // 测试 rtype 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setRtype(null)));
       // 测试 publishTime 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setPublishTime(null)));
       // 测试 createTime 不匹配
       songMapper.insert(cloneIgnoreId(dbSong, o -> o.setCreateTime(null)));
       // 准备参数
       SongPageReqVO reqVO = new SongPageReqVO();
       reqVO.setAlbumId(null);
       reqVO.setName(null);
       reqVO.setPst(null);
       reqVO.setT(null);
       reqVO.setPop(null);
       reqVO.setSt(null);
       reqVO.setRt(null);
       reqVO.setFee(null);
       reqVO.setV(null);
       reqVO.setCf(null);
       reqVO.setDt(null);
       reqVO.setCd(null);
       reqVO.setNo(null);
       reqVO.setFtype(null);
       reqVO.setDjId(null);
       reqVO.setCopyright(null);
       reqVO.setSId(null);
       reqVO.setMark(null);
       reqVO.setOriginCoverType(null);
       reqVO.setSingle(null);
       reqVO.setMst(null);
       reqVO.setCp(null);
       reqVO.setMv(null);
       reqVO.setRtype(null);
       reqVO.setPublishTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SongDO> pageResult = songService.getSongPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSong, pageResult.getList().get(0));
    }

}