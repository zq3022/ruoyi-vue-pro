/*
 Navicat Premium Data Transfer

 Source Server         : mysql-81
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : 172.16.71.81:3306
 Source Schema         : ruoyi-vue-pro

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 23/11/2023 16:47:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for space_directory
-- ----------------------------
DROP TABLE IF EXISTS `space_directory`;
CREATE TABLE `space_directory` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '目录编号',
  `source_id` bigint NOT NULL COMMENT '源编号',
  `lft` bigint NOT NULL COMMENT '左索引',
  `rgt` bigint NOT NULL COMMENT '右索引',
  `level` int NOT NULL COMMENT '层级',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci COMMENT='目录';

-- ----------------------------
-- Records of space_directory
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for space_directory_permission
-- ----------------------------
DROP TABLE IF EXISTS `space_directory_permission`;
CREATE TABLE `space_directory_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `directory_id` bigint NOT NULL COMMENT '目录编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `manage` bit(1) DEFAULT NULL COMMENT '管理',
  `writable` bit(1) DEFAULT NULL COMMENT '可写',
  `readable` bit(1) DEFAULT NULL COMMENT '可读',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci COMMENT='目录权限';

-- ----------------------------
-- Records of space_directory_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for space_source
-- ----------------------------
DROP TABLE IF EXISTS `space_source`;
CREATE TABLE `space_source` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '源编号',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '目录路径',
  `type` tinyint DEFAULT NULL COMMENT '源类型',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci COMMENT='目录源';

-- ----------------------------
-- Records of space_source
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
