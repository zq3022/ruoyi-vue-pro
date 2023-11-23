package cn.iocoder.yudao.module.space.service.directory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.space.controller.admin.directory.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import cn.iocoder.yudao.module.space.dal.mysql.directory.DirectoryMapper;
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
 * {@link DirectoryServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DirectoryServiceImpl.class)
public class DirectoryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DirectoryServiceImpl directoryService;

    @Resource
    private DirectoryMapper directoryMapper;

    @Test
    public void testCreateDirectory_success() {
        // 准备参数
        DirectorySaveReqVO createReqVO = randomPojo(DirectorySaveReqVO.class).setId(null);

        // 调用
        Long directoryId = directoryService.createDirectory(createReqVO);
        // 断言
        assertNotNull(directoryId);
        // 校验记录的属性是否正确
        DirectoryDO directory = directoryMapper.selectById(directoryId);
        assertPojoEquals(createReqVO, directory, "id");
    }

    @Test
    public void testUpdateDirectory_success() {
        // mock 数据
        DirectoryDO dbDirectory = randomPojo(DirectoryDO.class);
        directoryMapper.insert(dbDirectory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DirectorySaveReqVO updateReqVO = randomPojo(DirectorySaveReqVO.class, o -> {
            o.setId(dbDirectory.getId()); // 设置更新的 ID
        });

        // 调用
        directoryService.updateDirectory(updateReqVO);
        // 校验是否更新正确
        DirectoryDO directory = directoryMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, directory);
    }

    @Test
    public void testUpdateDirectory_notExists() {
        // 准备参数
        DirectorySaveReqVO updateReqVO = randomPojo(DirectorySaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> directoryService.updateDirectory(updateReqVO), DIRECTORY_NOT_EXISTS);
    }

    @Test
    public void testDeleteDirectory_success() {
        // mock 数据
        DirectoryDO dbDirectory = randomPojo(DirectoryDO.class);
        directoryMapper.insert(dbDirectory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDirectory.getId();

        // 调用
        directoryService.deleteDirectory(id);
       // 校验数据不存在了
       assertNull(directoryMapper.selectById(id));
    }

    @Test
    public void testDeleteDirectory_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> directoryService.deleteDirectory(id), DIRECTORY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDirectoryPage() {
       // mock 数据
       DirectoryDO dbDirectory = randomPojo(DirectoryDO.class, o -> { // 等会查询到
           o.setSourceId(null);
           o.setLft(null);
           o.setRgt(null);
           o.setLevel(null);
           o.setName(null);
           o.setCreateTime(null);
       });
       directoryMapper.insert(dbDirectory);
       // 测试 sourceId 不匹配
       directoryMapper.insert(cloneIgnoreId(dbDirectory, o -> o.setSourceId(null)));
       // 测试 lft 不匹配
       directoryMapper.insert(cloneIgnoreId(dbDirectory, o -> o.setLft(null)));
       // 测试 rgt 不匹配
       directoryMapper.insert(cloneIgnoreId(dbDirectory, o -> o.setRgt(null)));
       // 测试 level 不匹配
       directoryMapper.insert(cloneIgnoreId(dbDirectory, o -> o.setLevel(null)));
       // 测试 name 不匹配
       directoryMapper.insert(cloneIgnoreId(dbDirectory, o -> o.setName(null)));
       // 测试 createTime 不匹配
       directoryMapper.insert(cloneIgnoreId(dbDirectory, o -> o.setCreateTime(null)));
       // 准备参数
       DirectoryPageReqVO reqVO = new DirectoryPageReqVO();
       reqVO.setSourceId(null);
       reqVO.setLft(null);
       reqVO.setRgt(null);
       reqVO.setLevel(null);
       reqVO.setName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DirectoryDO> pageResult = directoryService.getDirectoryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDirectory, pageResult.getList().get(0));
    }

}