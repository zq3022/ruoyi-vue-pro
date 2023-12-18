package cn.iocoder.yudao.module.space.test.permission;

import cn.iocoder.yudao.module.space.service.permission.DirectoryPermissionServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.space.controller.admin.permission.vo.*;
import cn.iocoder.yudao.module.space.dal.dataobject.permission.DirectoryPermissionDO;
import cn.iocoder.yudao.module.space.dal.mysql.permission.DirectoryPermissionMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;
import static cn.iocoder.yudao.module.space.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link DirectoryPermissionServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DirectoryPermissionServiceImpl.class)
public class DirectoryPermissionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DirectoryPermissionServiceImpl directoryPermissionService;

    @Resource
    private DirectoryPermissionMapper directoryPermissionMapper;

    @Test
    public void testCreateDirectoryPermission_success() {
        // 准备参数
        DirectoryPermissionSaveReqVO createReqVO = randomPojo(DirectoryPermissionSaveReqVO.class).setId(null);

        // 调用
        Long directoryPermissionId = directoryPermissionService.createDirectoryPermission(createReqVO);
        // 断言
        assertNotNull(directoryPermissionId);
        // 校验记录的属性是否正确
        DirectoryPermissionDO directoryPermission = directoryPermissionMapper.selectById(directoryPermissionId);
        assertPojoEquals(createReqVO, directoryPermission, "id");
    }

    @Test
    public void testUpdateDirectoryPermission_success() {
        // mock 数据
        DirectoryPermissionDO dbDirectoryPermission = randomPojo(DirectoryPermissionDO.class);
        directoryPermissionMapper.insert(dbDirectoryPermission);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DirectoryPermissionSaveReqVO updateReqVO = randomPojo(DirectoryPermissionSaveReqVO.class, o -> {
            o.setId(dbDirectoryPermission.getId()); // 设置更新的 ID
        });

        // 调用
        directoryPermissionService.updateDirectoryPermission(updateReqVO);
        // 校验是否更新正确
        DirectoryPermissionDO directoryPermission = directoryPermissionMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, directoryPermission);
    }

    @Test
    public void testUpdateDirectoryPermission_notExists() {
        // 准备参数
        DirectoryPermissionSaveReqVO updateReqVO = randomPojo(DirectoryPermissionSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> directoryPermissionService.updateDirectoryPermission(updateReqVO), DIRECTORY_PERMISSION_NOT_EXISTS);
    }

    @Test
    public void testDeleteDirectoryPermission_success() {
        // mock 数据
        DirectoryPermissionDO dbDirectoryPermission = randomPojo(DirectoryPermissionDO.class);
        directoryPermissionMapper.insert(dbDirectoryPermission);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDirectoryPermission.getId();

        // 调用
        directoryPermissionService.deleteDirectoryPermission(id);
       // 校验数据不存在了
       assertNull(directoryPermissionMapper.selectById(id));
    }

    @Test
    public void testDeleteDirectoryPermission_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> directoryPermissionService.deleteDirectoryPermission(id), DIRECTORY_PERMISSION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDirectoryPermissionPage() {
       // mock 数据
       DirectoryPermissionDO dbDirectoryPermission = randomPojo(DirectoryPermissionDO.class, o -> { // 等会查询到
           o.setDirectoryId(null);
           o.setUserId(null);
           o.setManage(null);
           o.setWritable(null);
           o.setReadable(null);
           o.setCreateTime(null);
       });
       directoryPermissionMapper.insert(dbDirectoryPermission);
       // 测试 directoryId 不匹配
       directoryPermissionMapper.insert(cloneIgnoreId(dbDirectoryPermission, o -> o.setDirectoryId(null)));
       // 测试 userId 不匹配
       directoryPermissionMapper.insert(cloneIgnoreId(dbDirectoryPermission, o -> o.setUserId(null)));
       // 测试 manage 不匹配
       directoryPermissionMapper.insert(cloneIgnoreId(dbDirectoryPermission, o -> o.setManage(null)));
       // 测试 writable 不匹配
       directoryPermissionMapper.insert(cloneIgnoreId(dbDirectoryPermission, o -> o.setWritable(null)));
       // 测试 readable 不匹配
       directoryPermissionMapper.insert(cloneIgnoreId(dbDirectoryPermission, o -> o.setReadable(null)));
       // 测试 createTime 不匹配
       directoryPermissionMapper.insert(cloneIgnoreId(dbDirectoryPermission, o -> o.setCreateTime(null)));
       // 准备参数
       DirectoryPermissionPageReqVO reqVO = new DirectoryPermissionPageReqVO();
       reqVO.setDirectoryId(null);
       reqVO.setUserId(null);
       reqVO.setManage(null);
       reqVO.setWritable(null);
       reqVO.setReadable(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DirectoryPermissionDO> pageResult = directoryPermissionService.getDirectoryPermissionPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDirectoryPermission, pageResult.getList().get(0));
    }

}
