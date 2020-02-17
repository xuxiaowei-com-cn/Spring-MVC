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

 Date: 18/02/2020 01:21:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mybatis_plus_test
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_plus_test`;
CREATE TABLE `mybatis_plus_test`  (
  `test_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`test_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'MyBatis Plus 测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mybatis_plus_test
-- ----------------------------
INSERT INTO `mybatis_plus_test` VALUES (1, '2', '3');

SET FOREIGN_KEY_CHECKS = 1;
