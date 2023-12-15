package cn.iocoder.yudao.module.album.dal.redis;

/**
 * 支付 Redis Key 枚举类
 *
 * @author koiup
 */
public interface RedisKeyConstants {
    // ----- 用于打断当前正在执行的文件夹缓存服务 --------------------

    /**
     * 正在处理文件夹的文件信息，message标识
     * KEY 格式：directory_operated:{directoryId}
     * VALUE 数据格式 {directory_message_type}
     */
    String DIRECTORY_OPERATED = "directory_operated:%s";

}
