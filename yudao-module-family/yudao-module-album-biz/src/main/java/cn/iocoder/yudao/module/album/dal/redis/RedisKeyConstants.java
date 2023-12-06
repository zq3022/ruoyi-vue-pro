package cn.iocoder.yudao.module.album.dal.redis;

/**
 * 支付 Redis Key 枚举类
 *
 * @author koiup
 */
public interface RedisKeyConstants {

    // ----- consumer lock--------------------

    // ----- message no generator--------------------
    /**
     * Source 消息序号的缓存
     * KEY 格式：source_no:{prefix}
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
