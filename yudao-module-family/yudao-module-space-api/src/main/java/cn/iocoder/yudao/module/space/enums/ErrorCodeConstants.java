package cn.iocoder.yudao.module.space.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Album 错误码枚举类
 * <p>
 * crm 系统，使用 1_101_001_000 段
 */
public interface ErrorCodeConstants {
    // ========== 目录管理 1_101_001_000 ==========
    ErrorCode DIRECTORY_NOT_EXISTS = new ErrorCode(1_101_001_000, "目录不存在");

    // ========== 目录权限 1_101_002_000 ==========
    ErrorCode DIRECTORY_PERMISSION_NOT_EXISTS = new ErrorCode(1_101_002_000, "目录权限不存在");

    // ========== 目录源 1_101_003_000 ==========
    ErrorCode SOURCE_NOT_EXISTS = new ErrorCode(1_101_003_000, "目录源不存在");
    ErrorCode SOURCE_EXISTS_INCLUSION = new ErrorCode(1_101_003_001, "存在包含关系的目录源");

}
