package cn.iocoder.yudao.module.space.dal.mysql.directory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.space.controller.admin.directory.vo.DirectoryPageReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.directory.DirectoryDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 目录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DirectoryMapper extends BaseMapperX<DirectoryDO> {

    default PageResult<DirectoryDO> selectPage(DirectoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DirectoryDO>()
                .eqIfPresent(DirectoryDO::getSourceId, reqVO.getSourceId())
                .eqIfPresent(DirectoryDO::getLft, reqVO.getLft())
                .eqIfPresent(DirectoryDO::getRgt, reqVO.getRgt())
                .eqIfPresent(DirectoryDO::getLevel, reqVO.getLevel())
                .likeIfPresent(DirectoryDO::getName, reqVO.getName())
                .betweenIfPresent(DirectoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DirectoryDO::getId));
    }

    /**
     * 删除整颗树
     * @param sourceId 源目录id
     */
    default void deleteBySource(Long sourceId) {
        delete(new LambdaQueryWrapperX<DirectoryDO>().eq(DirectoryDO::getSourceId, sourceId));
    }


    /**
     * 删除当前目录节点及其所有子节点
     * 注意：删除之后并不调整subTree的lft、rgt、level
     * @param directory 目录节点
     */
    default List<DirectoryDO> deleteSubTree(DirectoryDO directory) {
        LambdaQueryWrapper<DirectoryDO> queryWrapperX = new LambdaQueryWrapperX<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, directory.getSourceId())
                .and(w -> w.ge(DirectoryDO::getLft, directory.getLft())
                        .or()
                        .le(DirectoryDO::getRgt, directory.getRgt()));
        List<DirectoryDO> deletedList = selectList(queryWrapperX);
        delete(queryWrapperX);
        return deletedList;
    }
    /**
     * 用于调整树, after删除子树之后
     * @param sourceId 源id
     * @param leftOffset lft偏移量
     */
    default void reconstructedOffsetAfterDeleteSubTree(Long sourceId, Long leftOffset){
        update(new LambdaUpdateWrapper<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, sourceId)
                .setSql("lft = lft - {0}", leftOffset)
                .setSql("rgt = rgt - {0}", leftOffset)
        );
    }

    /**
     * 删除所有的父节点和兄弟节点
     * 注意：删除之后并不调整subTree的lft、rgt、level
     * @param subTreeRoot 子树的根节点
     */
    default List<DirectoryDO> deleteParentsAndSiblingsBySubTreeRoot(DirectoryDO subTreeRoot) {
        LambdaQueryWrapper<DirectoryDO> queryWrapperX = new LambdaQueryWrapperX<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, subTreeRoot.getSourceId())
                .and(w -> w.lt(DirectoryDO::getLft, subTreeRoot.getLft())
                        .or()
                        .gt(DirectoryDO::getRgt, subTreeRoot.getRgt()));
        List<DirectoryDO> deletedList = selectList(queryWrapperX);
        delete(queryWrapperX);
        return deletedList;
    }
    /**
     * 用于调整树, after删除所有的父节点和兄弟节点
     * @param sourceId 源id
     * @param leftOffset lft偏移量
     * @param levelOffset level偏移量
     */
    default void reconstructedOffsetByRoot(Long sourceId, Long leftOffset, Integer levelOffset){
        update(new LambdaUpdateWrapper<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, sourceId)
                .setSql("lft = lft - {0}", leftOffset)
                .setSql("rgt = rgt - {0}", leftOffset)
                .setSql("level = level - {0}", levelOffset)
        );
    }

    default List<DirectoryDO> selectBySource(Long sourceId){
        return selectList(new LambdaUpdateWrapper<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, sourceId));
    }

    default DirectoryDO selectBySubDirectoryName(Long sourceId, Path oldSourceRootPath, Path newPath) {
        // 要找的节点所在的level,注意根节点level为1
        return selectOne(new LambdaQueryWrapperX<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, sourceId)
                .eq(DirectoryDO::getLevel, newPath.getNameCount() - oldSourceRootPath.getNameCount() + 1) // 目录所在层级
                .eq(DirectoryDO::getName, newPath.getFileName().toString())  // 目录名
                .last("limit 1")
        );
    }

    default String getFullPath(DirectoryDO directory){
        List<DirectoryDO> list = selectList(new LambdaQueryWrapperX<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, directory.getSourceId())
                .le(DirectoryDO::getLft, directory.getLft())
                .ge(DirectoryDO::getRgt, directory.getRgt())
                .ne(DirectoryDO::getLft, 1)
                .orderByAsc(DirectoryDO::getLft)
        );
        return list.stream().map(DirectoryDO::getName).collect(Collectors.joining(File.separator));
    }

    default DirectoryDO selectRoot(Long sourceId){
        // 要找的节点所在的level,注意根节点level为1
        return selectOne(new LambdaQueryWrapperX<DirectoryDO>()
                .eq(DirectoryDO::getSourceId, sourceId)
                .eq(DirectoryDO::getLevel, 1) // 目录所在层级
                .eq(DirectoryDO::getLft, 1L)  // root目录的左值lft
                .last("limit 1")
        );
    }
}
