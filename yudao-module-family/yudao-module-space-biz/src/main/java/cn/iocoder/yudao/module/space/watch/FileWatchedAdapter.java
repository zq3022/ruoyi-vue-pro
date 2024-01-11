package cn.iocoder.yudao.module.space.watch;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 文件监听适配器
 *
 * @author zhibo
 * @date 2019-07-31 11:07
 */
public class FileWatchedAdapter implements FileWatchedListener {

    @Override
    public void onCreated(WatchEvent<Path> watchEvent) {
        Path fileName = watchEvent.context();
        System.out.println(String.format("文件【%s】被创建，时间：%s", fileName, now()));
    }

    @Override
    public void onDeleted(WatchEvent<Path> watchEvent) {
        Path fileName = watchEvent.context();
        System.out.println(String.format("文件【%s】被删除，时间：%s", fileName, now()));
    }

    @Override
    public void onModified(WatchEvent<Path> watchEvent) {
        Path fileName = watchEvent.context();
        System.out.println(String.format("文件【%s】被修改，时间：%s", fileName, now()));
    }

    @Override
    public void onOverflowed(WatchEvent<Path> watchEvent) {
        Path fileName = watchEvent.context();
        System.out.println(String.format("文件【%s】被丢弃，时间：%s", fileName, now()));
    }

    private String now(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
