/*
 Navicat Premium Data Transfer

 Source Server         : local-host
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : butte-nacos

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/11/2021 22:55:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (2, 'application.yml', 'DEFAULT_GROUP', 'mybatis-plus:\n  mapper-locations: classpath*:/mapper/**/*.xml\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    call-setters-on-nulls: true\n    jdbc-type-for-null: \'null\'', '1d3d7770eac661e8ccd555aeca2d02ff', '2021-06-23 06:25:03', '2021-11-14 08:17:35', NULL, '0:0:0:0:0:0:0:1', '', 'af8d549a-296e-4539-962f-d09e480185f5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (3, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/${data.name.mysql}?${spring.datasource.db-param}\n    username: root\n    password: 123456\n    db-param: useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&useSSL=false\n    hikari:\n      minimumIdle: 5\n      maximumPoolSize: 10\n      idleTimeout: 300000\n      maxLifetime: 500000\n      connectionTimeout: 30000\n    ', '6c47b079d4e753aaa869f66e52f8da8c', '2021-06-23 06:26:41', '2021-11-14 08:18:45', NULL, '0:0:0:0:0:0:0:1', '', 'af8d549a-296e-4539-962f-d09e480185f5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (7, 'application-gateway.yml', 'DEFAULT_GROUP', 'data:\n  name:\n    mysql: butte-nacos', 'b12234fd4fe2da2dae0f0303a7515900', '2021-06-23 10:49:54', '2021-11-14 08:19:02', NULL, '0:0:0:0:0:0:0:1', '', 'af8d549a-296e-4539-962f-d09e480185f5', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (21, 'application.yml', 'DEFAULT_GROUP', 'mybatis-plus:\n  mapper-locations: classpath*:/mapper/**/*.xml\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    map-underscore-to-camel-case: true\n    cache-enabled: false\n    call-setters-on-nulls: true\n    jdbc-type-for-null: \'null\'', '1d3d7770eac661e8ccd555aeca2d02ff', '2021-06-24 08:22:10', '2021-06-24 08:22:29', NULL, '0:0:0:0:0:0:0:1', '', '57db44eb-0c6a-46a8-bf5d-ff889c684594', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (22, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/${data.name.mysql}?${spring.datasource.db-param}\n    username: root\n    password: 123456\n    db-param: useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&useSSL=false\n    hikari:\n      minimumIdle: 5\n      maximumPoolSize: 10\n      idleTimeout: 300000\n      maxLifetime: 500000\n      connectionTimeout: 30000\n    ', '6c47b079d4e753aaa869f66e52f8da8c', '2021-06-24 08:25:05', '2021-10-31 06:13:40', NULL, '0:0:0:0:0:0:0:1', '', '57db44eb-0c6a-46a8-bf5d-ff889c684594', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (23, 'application-quartz.yml', 'DEFAULT_GROUP', 'data:\n  name:\n    mysql: butte-quartz\n\nspring:\n  quartz:\n    job-store-type: jdbc\n    properties:\n      org:\n        quartz:\n          scheduler:\n            instanceName: ButteScheduler\n            instanceId: AUTO\n          jobStore:\n            class: org.quartz.impl.jdbcjobstore.JobStoreTX\n            driverDelegateClass: org.quartz.impl.jdbcjobstore.StdJDBCDelegate\n            tablePrefix: qrtz_\n            isClustered: true\n            clusterCheckinInterval: 15000\n            useProperties: false\n          threadPool:\n            class: org.quartz.simpl.SimpleThreadPool\n            threadPriority: 5\n            threadCount: 10\n            threadsInheritContextClassLoaderOfInitializingThread: true', '5be0800b18ab5433afb82cf53c88856e', '2021-06-24 08:21:23', '2021-06-24 08:26:34', NULL, '0:0:0:0:0:0:0:1', '', '57db44eb-0c6a-46a8-bf5d-ff889c684594', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (25, 'application-facade.yml', 'DEFAULT_GROUP', 'data:\n  name:\n    mysql: butte-facade\n\nspring:\n  redis:\n    host: 192.168.37.134\n    port: 6379\n    database: 2\n\nlogstash:\n  destination: \n    uri: 192.168.37.136\n    port: 5044\n\n## Seata分布式事务\nseata:\n  enabled: true\n  application-id: ${spring.application.name}\n  tx-service-group: butte-seata-group\n  config:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.config.server-addr}\n      namespace: de5900d5-cdca-4411-b8f7-a788e753d7a0\n      group: DEFAULT_GROUP\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.config.server-addr}\n      application: seata-server\n      group: DEFAULT_GROUP', '1394bf06e192028fdc2e022fc6e18458', '2021-06-24 08:56:59', '2021-11-14 09:33:20', NULL, '0:0:0:0:0:0:0:1', '', '57db44eb-0c6a-46a8-bf5d-ff889c684594', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (27, 'store.mode', 'DEFAULT_GROUP', 'db', 'd77d5e503ad1439f585ac494268b351b', '2020-05-09 14:27:38', '2020-05-09 14:33:11', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (28, 'store.db.datasource', 'DEFAULT_GROUP', 'druid', '3d650fb8a5df01600281d48c47c9fa60', '2020-05-09 14:27:38', '2020-05-09 14:33:11', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (29, 'store.db.dbType', 'DEFAULT_GROUP', 'mysql', '81c3b080dad537de7e10e0987a4bf52e', '2020-05-09 14:27:38', '2020-05-09 14:33:11', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (30, 'store.db.url', 'DEFAULT_GROUP', 'jdbc:mysql://127.0.0.1:3306/butte-seata?useUnicode=true', 'dfc5c9d8c7ee13803568ed717d544b6', '2020-05-09 14:27:38', '2020-05-09 14:33:12', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (31, 'store.db.user', 'DEFAULT_GROUP', 'root', '63a9f0ea7bb98050796b649e85481845', '2020-05-09 14:27:38', '2020-05-09 14:33:12', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (32, 'store.db.password', 'DEFAULT_GROUP', '123456', '6848786a325396c0a10b2114fbf22b0c', '2020-05-09 14:27:38', '2020-05-09 14:33:12', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (33, 'service.vgroupMapping.butte-seata-group', 'DEFAULT_GROUP', 'butte-seata-cluster', '5676d2a74c8165ee3d89ea791ca5a29e', '2020-05-09 18:00:35', '2021-02-19 15:47:11', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', '', '', '', '', '');
INSERT INTO `config_info` VALUES (35, 'store.db.driverClassName', 'DEFAULT_GROUP', 'com.mysql.jdbc.Driver', '683cf0c3a5a56cec94dfac94ca16d760', '2020-10-12 09:06:43', '2020-10-12 09:10:54', NULL, '127.0.0.1', '', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (36, 'application-account.yml', 'DEFAULT_GROUP', 'data:\n  name:\n    mysql: butte-account\n\nlogstash:\n  destination: \n    uri: 192.168.37.136\n    port: 5044\n\n## Seata分布式事务\nseata:\n  enabled: true\n  application-id: ${spring.application.name}\n  tx-service-group: butte-seata-group\n  config:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.config.server-addr}\n      namespace: de5900d5-cdca-4411-b8f7-a788e753d7a0\n      group: DEFAULT_GROUP\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.config.server-addr}\n      application: seata-server\n      group: DEFAULT_GROUP', 'f4fa02312dac2d49cd5c231633f44cc7', '2021-09-05 08:32:07', '2021-11-14 09:33:50', NULL, '0:0:0:0:0:0:0:1', '', '57db44eb-0c6a-46a8-bf5d-ff889c684594', '', '', '', 'yaml', '');
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Table structure for config_route
-- ----------------------------
DROP TABLE IF EXISTS `config_route`;
CREATE TABLE `config_route` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `route_id` varchar(100) NOT NULL COMMENT '路由ID',
  `uri` varchar(100) NOT NULL COMMENT '资源标识',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `predicates` text NOT NULL COMMENT '判断器',
  `filters` text COMMENT '过滤器',
  `metadata` varchar(200) NOT NULL COMMENT '诠释数据',
  `state` int(1) DEFAULT '1' COMMENT '状态：1有效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='网关路由表';

-- ----------------------------
-- Records of config_route
-- ----------------------------
BEGIN;
INSERT INTO `config_route` VALUES (1, 'quartz', 'lb://quartz', 99, '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/quartz*/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', '{}', 1, '2020-04-21 17:00:32', '2020-04-21 17:00:32');
INSERT INTO `config_route` VALUES (2, 'facade', 'lb://facade', 99, '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/facade*/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', '{}', 1, '2020-04-21 17:00:32', '2020-04-21 17:00:32');
COMMIT;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
INSERT INTO `tenant_info` VALUES (1, '1', 'de5900d5-cdca-4411-b8f7-a788e753d7a0', 'butte-seata', 'Seata全局事务组件', 'nacos', 1624427563869, 1624427563869);
INSERT INTO `tenant_info` VALUES (2, '1', '57db44eb-0c6a-46a8-bf5d-ff889c684594', 'butte-serve', '业务服务配置', 'nacos', 1624427670110, 1624427670110);
INSERT INTO `tenant_info` VALUES (3, '1', 'af8d549a-296e-4539-962f-d09e480185f5', 'butte-gateway', '网关控制层', 'nacos', 1624428435112, 1624428435112);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
