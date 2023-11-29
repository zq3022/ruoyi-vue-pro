package cn.iocoder.yudao.module.space.enums;

import cn.hutool.core.util.ArrayUtil;
import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 队列消息业务类型
 */
@AllArgsConstructor
@Getter
public enum MessageTypeEnum implements IntArrayValuable {
    ADD(1, "新增"), // 面向 c 端，普通用户
    UPDATE(2, "修改"), // 面向 b 端，管理后台
    DELETE(3, "删除"); // 面向 b 端，管理后台

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(MessageTypeEnum::getValue).toArray();

    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    public static MessageTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(messageType -> messageType.getValue().equals(value), MessageTypeEnum.values());
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
