package cn.iocoder.yudao.module.cf.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Family 错误码
 * @author koiup
 */
public interface ErrorCodeConstants {
    // ========== cf player 专辑 1_111_001_000 ==========
    ErrorCode ALBUM_NOT_EXISTS = new ErrorCode(1_111_001_000, "专辑不存在");

    // ========== 艺术家 1_111_002_000 ==========
    ErrorCode ARTIST_NOT_EXISTS = new ErrorCode(1_111_002_000, "艺术家不存在");

    // ========== 歌单 1_111_003_000 ==========
    ErrorCode PLAYLIST_NOT_EXISTS = new ErrorCode(1_111_003_000, "歌单不存在");
    // ========== 歌曲 1_111_004_000 ==========
    ErrorCode SONG_NOT_EXISTS = new ErrorCode(1_111_004_000, "歌曲不存在");
    // ========== 歌曲 1_111_005_000 ==========
    ErrorCode USER_NOT_EXISTS = new ErrorCode(1_111_005_000, "cf用户不存在");
}
