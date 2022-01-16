/*
 Navicat Premium Data Transfer

 Source Server         : local-host
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : butte-facade

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 19/11/2021 20:48:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_auth_white
-- ----------------------------
DROP TABLE IF EXISTS `tb_auth_white`;
CREATE TABLE `tb_auth_white` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `auth_sign` varchar(200) DEFAULT '' COMMENT '授权标识',
  `auth_type` int(1) DEFAULT '0' COMMENT '授权类型：1IP地址，2接口',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(1) DEFAULT '1' COMMENT '状态-1启用，2禁用，3删除',
  `remark` varchar(200) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='授权白名单';

-- ----------------------------
-- Records of tb_auth_white
-- ----------------------------
BEGIN;
INSERT INTO `tb_auth_white` VALUES (1, '/user/register', 2, '2021-09-05 20:54:19', '2021-09-05 20:54:21', 1, '');
INSERT INTO `tb_auth_white` VALUES (2, '/webjars', 2, '2021-09-05 20:54:38', '2021-09-05 20:54:40', 1, '');
INSERT INTO `tb_auth_white` VALUES (3, '/swagger', 2, '2021-09-05 20:55:00', '2021-09-05 20:55:02', 1, '');
INSERT INTO `tb_auth_white` VALUES (4, '/v2/api-docs', 2, '2021-09-05 20:55:17', '2021-09-05 20:55:18', 1, '');
INSERT INTO `tb_auth_white` VALUES (5, '/user/login', 2, '2021-09-12 12:25:49', '2021-09-12 12:25:51', 1, '');
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
