/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : xuxiaowei

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 06/02/2020 15:23:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cron
-- ----------------------------
DROP TABLE IF EXISTS `cron`;
CREATE TABLE `cron`  (
  `cron_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '定时器 ID',
  `expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '定时器表达式，与 cronDate 二选一',
  `cron_date` datetime(0) NULL DEFAULT NULL COMMENT '定时器执行的具体时间，与 expression 二选一',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '定时器是否删除，0 未删除，1 删除，默认值未删除 0',
  PRIMARY KEY (`cron_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cron
-- ----------------------------
INSERT INTO `cron` VALUES (1, '0/5 * * * * *', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
