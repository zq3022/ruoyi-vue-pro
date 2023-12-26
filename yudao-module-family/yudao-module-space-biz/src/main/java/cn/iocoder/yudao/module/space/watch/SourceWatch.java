package cn.iocoder.yudao.module.space.watch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.space.controller.admin.source.vo.SourcePageReqVO;
import cn.iocoder.yudao.module.space.dal.dataobject.source.SourceDO;
import cn.iocoder.yudao.module.space.service.source.SourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SourceWatch {

    @Resource
    private SourceService sourceService;

    private final Map<Integer, FileAlterationMonitor> monitorMap = new ConcurrentHashMap<>();
    private final Map<Long, FileAlterationObserver> observerMap = new ConcurrentHashMap<>();
    private final Map<Long, FileListener> listenerMap = new ConcurrentHashMap<>();
    private final Long interval = 1000L;

    @PostConstruct
    public void init() {
        List<Integer> types = sourceService.listSourceTypes();
        types.forEach(t -> monitorMap.put(t, new FileAlterationMonitor(interval)));

        PageResult<SourceDO> sourcePage = sourceService.getSourcePage((SourcePageReqVO) new SourcePageReqVO().setPageSize(Integer.MAX_VALUE));
        sourcePage.getList().forEach(source -> {
            FileAlterationMonitor monitor = monitorMap.get(source.getType());
            FileAlterationObserver observer = new FileAlterationObserver(new File(source.getPath()), FileFilterUtils.and(HiddenFileFilter.VISIBLE));
            FileListener fileListener = new FileListener(source.getId(), source.getType(), source.getPath());

            observer.addListener(fileListener);
            try {
                log.info("[init][type({}) 开启监控]", source.getType());
                monitor.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            monitor.addObserver(observer);

            observerMap.put(source.getId(), observer);
            listenerMap.put(source.getId(), fileListener);
        });

    }

    @PreDestroy
    public void destroy() {
        monitorMap.forEach((type, monitor) -> {
            try {
                log.info("[destroy][type({}) 关闭监控]", type);
                monitor.stop();
            } catch (Exception e) {
//                throw new RuntimeException(e);
            }
        });
    }

    public void addListener(Long sourceId, Integer type, String path) {
        FileListener fileListener = Optional.ofNullable(listenerMap.get(sourceId))
                .orElseGet(() -> {
                    listenerMap.put(sourceId, new FileListener(sourceId, type, path));
                    return listenerMap.get(sourceId);
                });

        FileAlterationObserver observer = Optional.ofNullable(observerMap.get(sourceId))
                .orElseGet(() -> {
                    FileAlterationObserver ob = new FileAlterationObserver(new File(path), FileFilterUtils.and(HiddenFileFilter.VISIBLE));
                    ob.addListener(fileListener);
                    observerMap.put(sourceId, ob);
                    return ob;
                });

        Optional.ofNullable(monitorMap.get(type)).orElseGet(() -> {
            FileAlterationMonitor mon = new FileAlterationMonitor(interval);
            monitorMap.put(type, mon);
            try {
                mon.start();
            } catch (Exception e) {
                log.error("fileMonitor start failed", e.getCause());
            }
            return mon;
        }).addObserver(observer);
    }

    public void removeListener(Long sourceId, Integer sourceType) {
        FileListener fileListener = listenerMap.get(sourceId);
        if (fileListener == null) return;
        listenerMap.remove(sourceId);

        FileAlterationObserver observer = observerMap.get(sourceId);
        if (observer == null) return;
        observer.removeListener(fileListener);
        observerMap.remove(sourceId);

        FileAlterationMonitor monitor = monitorMap.get(sourceType);
        if (monitor == null) return;
        monitor.removeObserver(observer);
    }

    public void stop(Integer type) throws Exception {
        FileAlterationMonitor value = monitorMap.get(type);
        if (value != null) value.stop();
    }

    public void start(Integer type) throws Exception {
        FileAlterationMonitor value = monitorMap.get(type);
        if (value != null) value.start();
    }

}
