package cn.iocoder.yudao.module.space.enums;

import cn.hutool.core.util.ArrayUtil;
import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 源类型
 */
@AllArgsConstructor
@Getter
public enum SourceTypeEnum implements IntArrayValuable {
    ALBUM(1, "相册"), // 相册
    VIDEO(2, "剧集"), // 剧集
    MUSIC(3, "音乐"), // 音乐
    DOCUMENT(4, "文档"); // 文档

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(SourceTypeEnum::getValue).toArray();

    /**
     * 相册
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    public static SourceTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(messageType -> messageType.getValue().equals(value), SourceTypeEnum.values());
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}

