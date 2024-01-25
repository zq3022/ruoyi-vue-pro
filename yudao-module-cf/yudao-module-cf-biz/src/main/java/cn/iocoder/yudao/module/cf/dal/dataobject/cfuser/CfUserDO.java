package cn.iocoder.yudao.module.cf.dal.dataobject.cfuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * cf用户 DO
 *
 * @author koi
 */
@TableName("cf_user")
@KeySequence("cf_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CfUserDO extends BaseDO {

    /**
     * 歌曲id
     */
    @TableId
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 背景图片URL
     */
    private String backgroundUrl;
    /**
     * 用户签名
     */
    private String signature;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 账户类型
     */
    private Integer accountType;
    /**
     * 用户简称
     */
    private String shortUserName;
    /**
     * 生日
     */
    private LocalDateTime birthday;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 账号状态
     */
    private Integer accountStatus;
    /**
     * 省份
     */
    private Integer province;
    /**
     * 城市
     */
    private Integer city;
    /**
     * 授权状态
     */
    private Integer authStatus;
    /**
     * 描述
     */
    private String description;
    /**
     * 默认头像
     */
    private Integer defaultAvatar;
    /**
     * dj状态
     */
    private Integer djStatus;
    /**
     * 本地状态
     */
    private Integer locationStatus;
    /**
     * vip类型
     */
    private Integer vipType;
    /**
     * 粉丝数
     */
    private Integer followed;
    /**
     * 点赞数
     */
    private Integer mutual;
    /**
     * 最近登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 最近登录ip
     */
    private String lastLoginIp;
    /**
     * vip类型乐观锁
     */
    private Long viptypeVersion;

}