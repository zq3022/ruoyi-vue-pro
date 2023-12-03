package cn.iocoder.yudao.module.space.dal.redis.no;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.space.dal.redis.RedisKeyConstants;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import cn.iocoder.yudao.module.space.mq.message.source.SourceMessage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Source消息的序号 Redis DAO
 *
 * @author koiup
 */
@Repository
public class MessageNoRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private String generate(String prefix, String messageNoRedisKey) {
        // 递增序号
        String noPrefix = prefix + DateUtil.format(LocalDateTime.now(), DatePattern.PURE_DATETIME_PATTERN);
        String key = messageNoRedisKey + noPrefix;
        Long no = stringRedisTemplate.opsForValue().increment(key);
        // 设置过期时间
        stringRedisTemplate.expire(key, Duration.ofMinutes(1L));
        return noPrefix + no;
    }

    /**
     * 生成【目录源】消息的序号
     *
     * @return 序号
     */
    public String generateSourceMessageNo() {
        return generate(SourceMessage.MESSAGE_NO_PREFIX, RedisKeyConstants.SOURCE_MESSAGE_NO);
    }

    /**
     * 生成【目录】消息的序号
     *
     * @return 序号
     */
    public String generateDirectoryMessageNo() {
        return generate(DirectoryMessage.MESSAGE_NO_PREFIX, RedisKeyConstants.DIRECTORY_MESSAGE_NO);
    }

    public void setDirectoryScanning(Long directoryId, String messageTempNo) {
        String redisKey = RedisKeyConstants.DIRECTORY_SCANNING_NO + directoryId;
        stringRedisTemplate.opsForValue().set(redisKey, messageTempNo, 1L, TimeUnit.DAYS);
    }

    public String getDirectoryScanning(Long directoryId) {
        String redisKey = RedisKeyConstants.DIRECTORY_SCANNING_NO + directoryId;
        ValueOperations<String, String> redisOperator = stringRedisTemplate.opsForValue();
        return redisOperator.get(redisKey);
    }
}
