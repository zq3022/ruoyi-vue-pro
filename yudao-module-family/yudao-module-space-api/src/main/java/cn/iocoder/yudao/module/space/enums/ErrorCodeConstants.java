package cn.iocoder.yudao.module.space.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Album 错误码枚举类
 * <p>
 * crm 系统，使用 1-101-001-000 段
 */
public interface ErrorCodeConstants {
    // ========== 目录管理 1-101-001-000 ==========
    ErrorCode DIRECTORY_NOT_EXISTS = new ErrorCode(1-101-001-000, "目录不存在");

    // ========== 目录权限 1-101-002-000 ==========
    ErrorCode DIRECTORY_PERMISSION_NOT_EXISTS = new ErrorCode(1-101-002-000, "目录权限不存在");

    // ========== 目录源 1-101-003-000 ==========
    ErrorCode SOURCE_NOT_EXISTS = new ErrorCode(1-101-003-000, "目录源不存在");
}
