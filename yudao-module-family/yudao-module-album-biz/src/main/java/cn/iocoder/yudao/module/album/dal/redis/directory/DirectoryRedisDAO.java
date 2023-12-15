package cn.iocoder.yudao.module.album.dal.redis.directory;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.album.dal.redis.RedisKeyConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Repository
public class DirectoryRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setDirectoryOperated(Long directoryId, Integer messageType) {
        String redisKey = String.format(RedisKeyConstants.DIRECTORY_OPERATED , directoryId);
        stringRedisTemplate.opsForValue().set(redisKey, String.valueOf(messageType), 1L , TimeUnit.HOURS);
    }

    public boolean hasDirectoryOperated(Long directoryId, String messageType) {
        String redisKey = String.format(RedisKeyConstants.DIRECTORY_OPERATED , directoryId);
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(redisKey))){
            return false;
        }
        ValueOperations<String, String> redisOperator = stringRedisTemplate.opsForValue();
        return StrUtil.equals(redisOperator.get(redisKey), messageType);
    }
}
