package cn.iocoder.yudao.module.space.watch;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

@Slf4j
public class FileListener extends FileAlterationListenerAdaptor {

    @Getter
    private Long sourceId;
    @Getter
    private Integer type;
    @Getter
    private String path;

    public FileListener(Long sourceId, Integer type, String path) {
        this.sourceId = sourceId;
        this.type = type;
        this.path = path;
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
//        log.info("onStart,source:{} | {} | {}", this.getSourceId(), this.getType(), this.getPath());
    }

    @Override
    public void onDirectoryCreate(File directory) {
        log.info("新建source[{}]下文件夹{}", this.getSourceId(), directory.getAbsolutePath());
    }

    @Override
    public void onDirectoryChange(File directory) {
        log.info("修改source[{}]下文件夹{}", this.getSourceId(), directory.getAbsolutePath());
    }

    @Override
    public void onDirectoryDelete(File directory) {
        log.info("删除source[{}]下文件夹{}", this.getSourceId(), directory.getAbsolutePath());
    }

    @Override
    public void onFileCreate(File file) {
        String compressedPath = file.getAbsolutePath();
        log.info("创建source[{}]下文件{}", this.getSourceId(), compressedPath);
        if (file.canRead()) {
            // TODO 读取或重新加载文件内容
            log.info("文件变更，进行处理");
        }
    }

    @Override
    public void onFileChange(File file) {
        String compressedPath = file.getAbsolutePath();
        log.info("修改source[{}]下文件{}", this.getSourceId(), compressedPath);
    }

    @Override
    public void onFileDelete(File file) {
        log.info("删除source[{}]下文件{}", this.getSourceId(), file.getAbsolutePath());
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
//        log.info("onStop,source:{} | {} | {}", this.getSourceId(), this.getType(), this.getPath());
    }
}
