package cn.iocoder.yudao.module.space.dal.redis.consumer;

import cn.iocoder.yudao.module.space.api.directory.DirectoryConsumerLockRedisApi;
import cn.iocoder.yudao.module.space.dal.redis.RedisKeyConstants;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * mq消费者消费的锁 Redis DAO
 *
 * @author koiup
 */
@Repository
public class DirectoryConsumerLockRedisDAO implements DirectoryConsumerLockRedisApi {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public void lock(String messageNo, Long timeoutMillis, Runnable runnable) {
        String lockKey = formatKey(messageNo);
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock(timeoutMillis, TimeUnit.MILLISECONDS);
            // 执行逻辑
            runnable.run();
        } finally {
            lock.unlock();
        }
    }

    private static String formatKey(String messageNo) {
        return String.format(RedisKeyConstants.DIRECTORY_CONSUMER_LOCK, messageNo);
    }

}
