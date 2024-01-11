package cn.iocoder.yudao.module.space.watch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Slf4j
public class FileWatchService {
    private WatchService watchService;

    private FileWatchedListener listener;

    /**
     *
     * @param path 要监听的目录，注意该 Path 只能是目录，否则会报错 java.nio.file.NotDirectoryException: /Users/zhibo/logs/a.log
     * @param listener 自定义的 listener，用来处理监听到的创建、修改、删除事件
     * @throws IOException
     */
    public FileWatchService(Path path, FileWatchedListener listener) throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService,
                /// 监听文件创建事件
                StandardWatchEventKinds.ENTRY_CREATE,
                /// 监听文件删除事件
                StandardWatchEventKinds.ENTRY_DELETE,
                /// 监听文件修改事件
                StandardWatchEventKinds.ENTRY_MODIFY);

//            path.register(watchService,
//                    new WatchEvent.Kind[]{
//                            StandardWatchEventKinds.ENTRY_MODIFY,
//                            StandardWatchEventKinds.ENTRY_CREATE,
//                            StandardWatchEventKinds.ENTRY_DELETE
//                    },
//                    SensitivityWatchEventModifier.HIGH);

        this.listener = listener;
    }

    private void watch() throws InterruptedException {
        while (true) {
            WatchKey watchKey = watchService.take();
            List<WatchEvent<?>> watchEventList = watchKey.pollEvents();
            for (WatchEvent<?> watchEvent : watchEventList) {
                WatchEvent.Kind kind = watchEvent.kind();

                WatchEvent<Path> curEvent = (WatchEvent<Path>) watchEvent;
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    listener.onOverflowed(curEvent);
                    continue;
                } else if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    listener.onCreated(curEvent);
                    continue;
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    listener.onModified(curEvent);
                    continue;
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    listener.onDeleted(curEvent);
                    continue;
                }
            }

            /**
             * WatchKey 有两个状态：
             * {@link sun.nio.fs.AbstractWatchKey.State.READY ready} 就绪状态：表示可以监听事件
             * {@link sun.nio.fs.AbstractWatchKey.State.SIGNALLED signalled} 有信息状态：表示已经监听到事件，不可以接续监听事件
             * 每次处理完事件后，必须调用 reset 方法重置 watchKey 的状态为 ready，否则 watchKey 无法继续监听事件
             */
            if (!watchKey.reset()) {
                break;
            }

        }
    }

    public static void main(String[] args) {
        try {
            Path path = Paths.get("/Users/zhibo/logs/");
            FileWatchService fileWatchService = new FileWatchService(path, new FileWatchedAdapter());
            fileWatchService.watch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
