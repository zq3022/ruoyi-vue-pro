package cn.iocoder.yudao.module.space.test.directory;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.test.core.ut.BaseMockitoUnitTest;
import cn.iocoder.yudao.module.space.service.directory.DirectoryServiceImpl;
import com.alibaba.druid.util.StringUtils;
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
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println(dir.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
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

    @SneakyThrows
    @Test
    public void testImageMetadata() {
        File file = new File("/Users/qzh/Documents/图片/WechatIMG2431.jpg");
//        File file = new File("/Users/qzh/Documents/图片/头像/0.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        log.info("listFile: {}", file.getName());
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String tagDesc = tag.getDescription();
                log.info("tagName: {}, tagDesc: {}", tagName, tagDesc);
                if (StringUtils.equals(tagName, "Image Description")) {
                    // 图片描述
                    log.info("图片描述: {}", tagDesc);
                }
            }
        }
    }

    @SneakyThrows
    @Test
    public void testImageMetadataByImageIo() {
//        File file = new File("/Users/qzh/Documents/图片/WechatIMG2431.jpg");
        File file = new File("/Users/qzh/Desktop/WechatIMG694.jpg");

//        File file = new File("/Users/qzh/Documents/图片/头像/0.jpg");
//        Detected MIME Type

//        Metadata metadata = ImageMetadataReader.readMetadata(file);
//        Collection<FileTypeDirectory> directoriesOfType = metadata.getDirectoriesOfType(FileTypeDirectory.class);
        Metadata metadata = ImageMetadataReader.readMetadata(file);
//        JpegImageMetadataReader JPEGMetadataReader = new JpegImageMetadata();

//        JpegImageMetadataReader.readMetadata(file);



        log.info("listFile: {}", file.getName());
        for (Directory directory : metadata.getDirectories()) {
            log.info("--------{}", directory.getName());
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String tagDesc = tag.getDescription();
                log.info("tagName: {}[{}], tagDesc: {}", tagName, StrUtil.toCamelCase(tagName,' '), tagDesc);
                if (StringUtils.equals(tagName, "Image Description")) {
                    // 图片描述
                    log.info("图片描述: {}", tagDesc);
                }


            }
        }



    }

}
