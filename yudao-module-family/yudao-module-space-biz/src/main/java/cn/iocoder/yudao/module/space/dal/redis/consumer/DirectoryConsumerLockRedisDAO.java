package cn.iocoder.yudao.module.space.dal.redis.consumer;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static cn.iocoder.yudao.module.space.dal.redis.RedisKeyConstants.DIRECTORY_CONSUMER_LOCK;

/**
 * mq消费者消费的锁 Redis DAO
 *
 * @author koiup
 */
@Repository
public class DirectoryConsumerLockRedisDAO {

    @Resource
    private RedissonClient redissonClient;

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
        return String.format(DIRECTORY_CONSUMER_LOCK, messageNo);
    }

}
