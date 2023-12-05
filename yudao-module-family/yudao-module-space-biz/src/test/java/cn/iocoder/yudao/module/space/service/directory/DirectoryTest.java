package cn.iocoder.yudao.module.space.service.directory;

import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * {@link DirectoryServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Slf4j
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


    @SneakyThrows
    @Test
    public void testDirectoryFile() {
        String filePath = "/Volumes/d_qzh/photo/底湖亲人/IMG_20180911_102212.JPG";
        File file = new File(filePath);
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        if (metadata == null) {
            log.info("cannot find metadata");
            return;
        }

        for (Directory directory : metadata.getDirectories()) {
            if (directory == null) {
                continue;
            }
            log.info(".........{}",directory.getName());

            for (Tag tag : directory.getTags()) {
                log.info("tagType:{}, tagName:{}, description: {}",tag.getTagType(), tag.getTagName(), tag.getDescription());
            }
        }
    }

}
