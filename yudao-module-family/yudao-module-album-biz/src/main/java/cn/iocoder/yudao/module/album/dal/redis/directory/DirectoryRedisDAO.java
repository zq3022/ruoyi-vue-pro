package cn.iocoder.yudao.module.album.dal.redis.directory;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.album.dal.redis.RedisKeyConstants;
import cn.iocoder.yudao.module.space.mq.message.directory.DirectoryMessage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Repository
public class DirectoryRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成消息的序号
     *
     * @return 序号
     */
    public String generateDirectoryScanningNo() {
        // 递增序号
        String noPrefix = DirectoryMessage.MESSAGE_NO_PREFIX + DateUtil.format(LocalDateTime.now(), DatePattern.PURE_DATETIME_PATTERN);
        String key = RedisKeyConstants.DIRECTORY_MESSAGE_NO + noPrefix;
        Long no = stringRedisTemplate.opsForValue().increment(key);
        // 设置过期时间
        stringRedisTemplate.expire(key, Duration.ofMinutes(1L));
        return noPrefix + no;
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
