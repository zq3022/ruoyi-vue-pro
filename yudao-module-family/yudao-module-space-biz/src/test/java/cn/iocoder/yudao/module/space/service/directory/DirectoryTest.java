package cn.iocoder.yudao.module.space.service.directory;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * {@link DirectoryServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
public class DirectoryTest extends BaseMockitoUnitTest {
    @SneakyThrows
    @Test
    public void testFileWalk() {
        String filePath = "/Users/qzh/Downloads/PhotoSwipe-master";
        Files.walkFileTree(Paths.get(filePath), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException
            {
                System.out.println(dir.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException
            {
                System.out.println(dir.toString());
                if (exc != null)
                    throw exc;
                return FileVisitResult.CONTINUE;
            }

        });
    }

}
