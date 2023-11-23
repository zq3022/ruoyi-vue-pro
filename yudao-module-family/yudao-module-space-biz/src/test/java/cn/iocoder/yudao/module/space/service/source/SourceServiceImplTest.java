package cn.iocoder.yudao.module.space.service.source;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.space.controller.admin.source.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.dal.mysql.source.SourceMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link SourceServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(SourceServiceImpl.class)
public class SourceServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SourceServiceImpl sourceService;

    @Resource
    private SourceMapper sourceMapper;

    @Test
    public void testCreateSource_success() {
        // 准备参数
        SourceSaveReqVO createReqVO = randomPojo(SourceSaveReqVO.class).setId(null);

        // 调用
        Long sourceId = sourceService.createSource(createReqVO);
        // 断言
        assertNotNull(sourceId);
        // 校验记录的属性是否正确
        SourceDO source = sourceMapper.selectById(sourceId);
        assertPojoEquals(createReqVO, source, "id");
    }

    @Test
    public void testUpdateSource_success() {
        // mock 数据
        SourceDO dbSource = randomPojo(SourceDO.class);
        sourceMapper.insert(dbSource);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SourceSaveReqVO updateReqVO = randomPojo(SourceSaveReqVO.class, o -> {
            o.setId(dbSource.getId()); // 设置更新的 ID
        });

        // 调用
        sourceService.updateSource(updateReqVO);
        // 校验是否更新正确
        SourceDO source = sourceMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, source);
    }

    @Test
    public void testUpdateSource_notExists() {
        // 准备参数
        SourceSaveReqVO updateReqVO = randomPojo(SourceSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> sourceService.updateSource(updateReqVO), SOURCE_NOT_EXISTS);
    }

    @Test
    public void testDeleteSource_success() {
        // mock 数据
        SourceDO dbSource = randomPojo(SourceDO.class);
        sourceMapper.insert(dbSource);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSource.getId();

        // 调用
        sourceService.deleteSource(id);
       // 校验数据不存在了
       assertNull(sourceMapper.selectById(id));
    }

    @Test
    public void testDeleteSource_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> sourceService.deleteSource(id), SOURCE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSourcePage() {
       // mock 数据
       SourceDO dbSource = randomPojo(SourceDO.class, o -> { // 等会查询到
           o.setPath(null);
           o.setType(null);
           o.setCreateTime(null);
       });
       sourceMapper.insert(dbSource);
       // 测试 path 不匹配
       sourceMapper.insert(cloneIgnoreId(dbSource, o -> o.setPath(null)));
       // 测试 type 不匹配
       sourceMapper.insert(cloneIgnoreId(dbSource, o -> o.setType(null)));
       // 测试 createTime 不匹配
       sourceMapper.insert(cloneIgnoreId(dbSource, o -> o.setCreateTime(null)));
       // 准备参数
       SourcePageReqVO reqVO = new SourcePageReqVO();
       reqVO.setPath(null);
       reqVO.setType(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SourceDO> pageResult = sourceService.getSourcePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSource, pageResult.getList().get(0));
    }

}