package cn.iocoder.yudao.module.space.test.source;

import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbAndRedisUnitTest;
import cn.iocoder.yudao.module.space.dal.redis.no.MessageNoRedisDAO;
import cn.iocoder.yudao.module.space.mq.producer.SourceProducer;
import cn.iocoder.yudao.module.space.service.source.SourceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;


import cn.iocoder.yudao.module.space.controller.admin.source.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.dal.mysql.source.SourceMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;

import java.util.*;
import java.util.concurrent.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SourceServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import({SourceServiceImpl.class, SourceProducer.class, RocketMQTemplate.class, MessageNoRedisDAO.class})
@Slf4j
public class SourceServiceImplTest extends BaseDbAndRedisUnitTest {

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


    @Test
    public void testSourceChange() {
        String basePath = "/Users/qzh/private/";
        String[] sources = new String[]{
                "15号周沁晨",
                "SONY",
                "SONY/VOICE",
                "SONY/VOICE/FOLDER01",
                "SONY/eg",
                "SONY/ep",
                "testPhoto",
                "testPhoto/ycy",
                "testPhoto/周沁晨🌹"
        };
        List<Long> ids = new ArrayList<>();

        Long tenantId = 1L;

        TenantContextHolder.setTenantId(tenantId);
        TenantContextHolder.setIgnore(false);

        int times= 30;
        CountDownLatch countDownLatch = new CountDownLatch(times);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        try {
            for (int i = 0; i < sources.length; i++) {
                for (int j = 1; j <= 3; j++) {
                    Long id = sourceService.createSource(new SourceSaveReqVO().setPath(basePath + sources[i]).setType(j));
                    if (id != null) {
                        ids.add(id);
                    }
                }
            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        if (ids.size() == 0){
            return;
        }

        for (int i = 0; i < times; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    log.info("开始....{}", finalI);
                    TenantContextHolder.setTenantId(tenantId);
                    TenantContextHolder.setIgnore(false);
                    // mock 数据
                    randomSourceAction(randomSourcePojo(ids, sources, basePath));
                    log.info("结束....{}", finalI);
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
            log.info("结束....");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void randomSourceAction(SourceSaveReqVO dbSource) {
        int i = randomInt(1,3);
        switch (i) {
            case 1:
                sourceService.createSource(dbSource.setId(null));
                break;
            case 2:
                sourceService.updateSource(dbSource);
                break;
            case 3:
                sourceService.deleteSource(dbSource.getId());
        }
    }

    private SourceSaveReqVO randomSourcePojo(List<Long> ids, String[] sources, String basePath) {
        return new SourceSaveReqVO().setId(randomEle(ids)).setPath(basePath+randomEle(sources)).setType(randomInt(1,3));
    }

}
