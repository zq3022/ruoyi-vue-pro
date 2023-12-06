package cn.iocoder.yudao.module.space.api.directory;

public interface DirectoryConsumerLockRedisApi {

    void lock(String messageNo, Long timeoutMillis, Runnable runnable) ;
}
