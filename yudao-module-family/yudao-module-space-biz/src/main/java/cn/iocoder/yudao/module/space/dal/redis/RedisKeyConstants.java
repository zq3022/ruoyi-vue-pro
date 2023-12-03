package cn.iocoder.yudao.module.space.dal.redis;

/**
 * 支付 Redis Key 枚举类
 *
 * @author koiup
 */
public interface RedisKeyConstants {

    // ----- consumer lock--------------------
    /**
     * 消费者消费源变更消息的分布式锁*
     * KEY 格式：source_consumer:lock:%s // 参数来自 DefaultLockKeyBuilder 类
     * VALUE 数据格式：HASH // RLock.class：Redisson 的 Lock 锁，使用 Hash 数据结构
     * 过期时间：不固定
     */
    String SOURCE_CONSUMER_LOCK = "source_consumer:lock:%s";

    /**
     * 消费者消费目录变更消息的分布式锁*
     * KEY 格式：directory_consumer:lock:%s // 参数来自 DefaultLockKeyBuilder 类
     * VALUE 数据格式：HASH // RLock.class：Redisson 的 Lock 锁，使用 Hash 数据结构
     * 过期时间：不固定
     */
    String DIRECTORY_CONSUMER_LOCK = "directory_consumer:lock:%s";

    // ----- message no generator--------------------
    /**
     * Source 消息序号的缓存
     * KEY 格式：source_no:{prefix}
     * VALUE 数据格式：编号自增
     */
    String SOURCE_MESSAGE_NO = "source_no:";

    /**
     * Directory 消息序号的缓存
     * KEY 格式：directory_no:{prefix}
     * VALUE 数据格式：编号自增
     */
    String DIRECTORY_MESSAGE_NO = "directory_no:";

    // ----- 用于打断当前正在执行的文件夹缓存服务 --------------------

    /**
     * 正在处理文件夹的文件信息，message标识
     * KEY 格式：directory_caching:{directoryId}
     * VALUE 数据格式 {directory_message_no}
     */
    String DIRECTORY_SCANNING_NO = "directory_caching:";


}
