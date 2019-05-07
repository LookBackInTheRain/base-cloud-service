/*
Navicat MySQL Data Transfer

Source Server         : sysMySQL
Source Server Version : 80012
Source Host           : 172.16.2.103:3306
Source Database       : sys_base_db

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-05-05 16:05:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('2c90825e6a57e3bf016a57f741050031', '2019-04-26 12:46:23', '普通用户', '2019-04-26 15:06:56', 'AUTH_BASE');
INSERT INTO `authority` VALUES ('JKDGH9877212', '2019-04-26 09:33:10', '基础管理', '2019-04-26 15:06:39', 'AUTHS_SYS_MANGER');

-- ----------------------------
-- Table structure for clients
-- ----------------------------
DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
  `id` varchar(255) NOT NULL,
  `accessTokenValiditySeconds` int(11) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `authorizedGrantTypes` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `clientSecret` varchar(255) DEFAULT NULL,
  `isAutoApprove` bit(1) DEFAULT NULL,
  `isScoped` bit(1) DEFAULT NULL,
  `isSecretRequired` bit(1) DEFAULT NULL,
  `refreshTokenValiditySeconds` int(11) DEFAULT NULL,
  `registeredRedirectUri` varchar(255) DEFAULT NULL,
  `resourceIds` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clients
-- ----------------------------
INSERT INTO `clients` VALUES ('JKGJHGJHFGH89867', '3600', 'SYS_CLIENT', 'password,refresh_token', 'sys_base_manager', '$2a$10$oQU.1tCM6hJc4ZoWtwsL3eALxm8BzAuHHZLwcTpn4TacufT8Q3zQC', '\0', '', '', '36000', 'http://localhost:9000', 'auth-service-resource,resources-service-resource', 'ALL');

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `id` varchar(32) NOT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `group` int(1) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `modifyTime` timestamp NULL DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `text` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('1', '2018-09-05 09:55:39', '', '1', '', '', '2019-01-06 11:24:12', '', '3', '平台管理');
INSERT INTO `menus` VALUES ('2', '2018-09-05 09:55:39', '', '0', 'anticon anticon-pie-chart', '/monitor', '2019-01-06 11:40:22', '40283a826820bf510168213e4d270035', '-1', '首页');
INSERT INTO `menus` VALUES ('2c90825e6a59e118016a59e56f900000', '2019-04-26 21:46:10', 'asdasd', '0', null, '/dasdasd', '2019-04-26 21:47:03', '40283a826820bf510168213e4d270035', '-1', 'TTT');
INSERT INTO `menus` VALUES ('2c9082b86a59943e016a5995f15b0001', '2019-04-26 20:19:20', null, '0', 'anticon anticon-cloud', 'statusMsg', '2019-04-26 20:19:20', '40283a826820bf510168213e4d270035', '-1', 'TopicList');
INSERT INTO `menus` VALUES ('2c9082e8699de4ce01699eceeccf0001', '2019-03-21 13:52:35', '', '0', '', '/main/user_behavior', '2019-03-21 13:52:35', '40283a826820bf510168211e3b8c0000', '-1', '用户行为监测');
INSERT INTO `menus` VALUES ('3', '2018-09-05 09:55:39', 'sdvdsvsdvsdv', '0', 'anticon anticon-appstore', '', '2019-01-05 18:06:26', '1', '1', '基础信息管理');
INSERT INTO `menus` VALUES ('4', '2018-09-05 09:55:39', '', '0', 'anticon anticon-switcher', '/base/user', '2019-04-26 17:36:14', '3', '0', '用户信息');
INSERT INTO `menus` VALUES ('40283a826820bf51016821213c370003', '2019-01-06 11:07:33', '数据分布监测', '0', '', '/main/data_distribution', '2019-01-07 09:31:54', '40283a826820bf510168211e3b8c0000', '1', '数据分布监测');
INSERT INTO `menus` VALUES ('40283a826820bf51016821224ef10005', '2019-01-06 11:08:43', '使用规律监测', '0', '', '/main/data_usage', '2019-01-07 10:01:29', '40283a826820bf510168211e3b8c0000', '1', '使用规律监测');
INSERT INTO `menus` VALUES ('40283a826820bf510168213e4d270035', '2019-01-06 11:39:18', '', '1', '', '', '2019-01-06 11:40:03', '', '-1', '主导航');
INSERT INTO `menus` VALUES ('7', '2018-09-06 14:34:37', '', '0', '', '/base/resources', '2018-09-20 15:12:51', '3', '1', '资源管理');
INSERT INTO `menus` VALUES ('8', '2018-09-07 10:29:49', '', '0', '', '/base/role', '2018-09-20 15:12:51', '3', '3', '角色管理');
INSERT INTO `menus` VALUES ('9', '2018-09-07 10:35:36', '', '0', '', '/base/authority', '2018-09-20 15:12:51', '3', '4', '权限管理');
INSERT INTO `menus` VALUES ('df92cc4ed13447a4b5139f5df96565ff', '2018-09-05 16:31:39', '', '0', '', '/base/menus', '2018-09-20 15:12:51', '3', '5', '菜单管理');

-- ----------------------------
-- Table structure for menus_authority
-- ----------------------------
DROP TABLE IF EXISTS `menus_authority`;
CREATE TABLE `menus_authority` (
  `id` varchar(255) NOT NULL,
  `authorityId` varchar(255) DEFAULT NULL,
  `menuId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `authorityId` (`authorityId`),
  KEY `menuId` (`menuId`),
  CONSTRAINT `menus_authority_ibfk_1` FOREIGN KEY (`authorityId`) REFERENCES `authority` (`id`) ON DELETE CASCADE,
  CONSTRAINT `menus_authority_ibfk_2` FOREIGN KEY (`menuId`) REFERENCES `menus` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus_authority
-- ----------------------------
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e791e40001', '2c90825e6a57e3bf016a57f741050031', '40283a826820bf510168213e4d270035');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e791e40002', '2c90825e6a57e3bf016a57f741050031', '2');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e791e40003', '2c90825e6a57e3bf016a57f741050031', '2c90825e6a59e118016a59e56f900000');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e791e40004', '2c90825e6a57e3bf016a57f741050031', '2c9082b86a59943e016a5995f15b0001');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b4800005', 'JKDGH9877212', '40283a826820bf510168213e4d270035');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b4800006', 'JKDGH9877212', '2');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b4800007', 'JKDGH9877212', '2c90825e6a59e118016a59e56f900000');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b4800008', 'JKDGH9877212', '2c9082b86a59943e016a5995f15b0001');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b4800009', 'JKDGH9877212', '1');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b480000a', 'JKDGH9877212', '3');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b480000b', 'JKDGH9877212', '4');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b480000c', 'JKDGH9877212', '7');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b480000d', 'JKDGH9877212', '8');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b480000e', 'JKDGH9877212', '9');
INSERT INTO `menus_authority` VALUES ('2c90825e6a59e118016a59e7b481000f', 'JKDGH9877212', 'df92cc4ed13447a4b5139f5df96565ff');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('2c90825e6a5772f9016a5782f7f50001', '2019-04-26 10:39:22', '获取所有权限', 'GET', '2019-04-26 10:39:22', '所有权限', '/authority');
INSERT INTO `resources` VALUES ('2c90825e6a5772f9016a578412660002', '2019-04-26 10:40:34', '添加权限', 'POST', '2019-04-26 10:40:34', '添加权限', '/authority');
INSERT INTO `resources` VALUES ('2c90825e6a5772f9016a5784686e0003', '2019-04-26 10:40:56', '修改权限', 'PUT', '2019-04-26 10:40:56', '修改权限', '/authority');
INSERT INTO `resources` VALUES ('2c90825e6a5772f9016a578504960004', '2019-04-26 10:41:36', '根据ID获取权限', 'GET', '2019-04-26 10:41:36', '获取一个权限', '/authority/*');
INSERT INTO `resources` VALUES ('2c90825e6a57aa20016a57ac12330000', '2019-04-26 11:24:16', '根据ID删除', 'DELETE', '2019-04-26 11:24:16', '删除权限', '/authority/*');
INSERT INTO `resources` VALUES ('2c90825e6a57aa20016a57ac749c0001', '2019-04-26 11:24:41', '批量删除', 'DELETE', '2019-04-26 11:24:41', '删除权限', '/authority/batch');
INSERT INTO `resources` VALUES ('2c90825e6a57aa20016a57acdbdb0002', '2019-04-26 11:25:07', '分页获取权限', 'GET', '2019-04-26 11:25:07', '分页获取权限', '/authority/page/*/*');
INSERT INTO `resources` VALUES ('2c90825e6a57aa20016a57ad7f1a0003', '2019-04-26 11:25:49', '根据role的id获取', 'GET', '2019-04-26 11:25:49', '获取role对应的权限', '/authority/role/*');
INSERT INTO `resources` VALUES ('2c90825e6a57aa20016a57aefaa80005', '2019-04-26 11:27:26', '给权限添加菜单', 'POST', '2019-04-26 11:27:26', '给权限添加菜单', '/menu_authority/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b4dd8c0000', '2019-04-26 11:33:52', '获取用户对应的菜单信息', 'GET', '2019-04-26 11:33:52', '获取菜单', '/menus');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b559a30001', '2019-04-26 11:34:24', '添加菜单并分配权限', 'POST', '2019-04-26 11:34:24', '添加菜单', '/menus');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b5bb190002', '2019-04-26 11:34:49', '修改菜单信息', 'PUT', '2019-04-26 11:34:49', '修改菜单', '/menus');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b630d90003', '2019-04-26 11:35:19', '根据ID删除', 'DELETE', '2019-04-26 11:35:19', '删除菜单', '/menus');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b6f33a0004', '2019-04-26 11:36:09', '根据权限ID获取', 'GET', '2019-04-26 11:36:09', '获取权限对应的菜单', '/menus/auth/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b769310005', '2019-04-26 11:36:39', '批量删除', 'DELETE', '2019-04-26 11:36:39', '批量删除', '/menus/batch');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b876f40006', '2019-04-26 11:37:48', '所有菜单项信息', 'GET', '2019-04-26 11:37:48', '所有菜单项信息', '/menus/sort');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b959ba0007', '2019-04-26 11:38:46', '给权限添加资源', 'POST', '2019-04-26 11:38:46', '给权限添加资源', '/resources_authority/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57b9e2a90008', '2019-04-26 11:39:21', '给权限移除资源', 'POST', '2019-04-26 11:39:21', '给权限添移除资源', '/resources_authority/remove/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57bb648a0009', '2019-04-26 11:41:00', '添加资源', 'POST', '2019-04-26 11:41:00', '添加资源', '/resources');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57bb9ee4000a', '2019-04-26 11:41:15', '修改资源', 'PUT', '2019-04-26 11:41:15', '修改资源', '/resources');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57bc71b5000b', '2019-04-26 11:42:09', '通过ID删除资源', 'DELETE', '2019-04-26 11:42:09', '删除资源', '/resources/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57bd7e36000c', null, '权限已拥有相关资源', 'GET', '2019-04-26 11:43:45', '权限相关资源', '/resources/auth/page/*/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57be9f21000d', '2019-04-26 11:44:32', '权限未拥有资源', 'GET', '2019-04-26 11:44:32', '权限未拥有资源', '/resources/auth/not/*/*/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57bf2324000e', '2019-04-26 11:45:05', '批量删除资源', 'DELETE', '2019-04-26 11:45:05', '批量删除资源', '/resources/batch');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57bf90fe000f', '2019-04-26 11:45:33', '分页获取资源', 'GET', '2019-04-26 11:45:33', '分页获取资源', '/resources/page/*/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57c0566e0010', '2019-04-26 11:46:24', '添加角色', 'POST', '2019-04-26 11:46:24', '添加角色', '/role');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57c0ee720011', '2019-04-26 11:47:03', '批量删除角色', 'DELETE', '2019-04-26 11:47:03', '批量删除角色', '/role/batch');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57c1e3790012', '2019-04-26 11:48:06', '通过ID删除角色', 'DELETE', '2019-04-26 11:48:06', '删除角色', '/role/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57c26b520013', '2019-04-26 11:48:40', '角色授权', 'POST', '2019-04-26 11:48:40', '角色授权', '/role/auths');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57c2d2d00014', '2019-04-26 11:49:07', '分页获取角色', 'GET', '2019-04-26 11:49:07', '分页获取角色', '/role/page/*/*');
INSERT INTO `resources` VALUES ('2c90825e6a57b3bd016a57c3b2cb0015', '2019-04-26 11:50:04', '获取用户角色', 'GET', '2019-04-26 11:50:04', '获取用户角色', '/role/users/*');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57e791a50000', '2019-04-26 12:29:15', '添加用户', 'POST', '2019-04-26 12:29:15', '添加用户', '/user');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57e7e7fd0001', '2019-04-26 12:29:37', '修改用户信息', 'PUT', '2019-04-26 12:29:37', '修改用户信息', '/user');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57e89ad10002', '2019-04-26 12:30:23', '通过ID获取', 'GET', '2019-04-26 12:30:23', '获取用户信息', '/user/*');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57e8de160003', '2019-04-26 12:30:40', '通过ID删除', 'DELETE', '2019-04-26 12:30:40', '删除用户信息', '/user/*');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57e981420004', '2019-04-26 12:31:22', '用户授权', 'POST', '2019-04-26 12:31:22', '用户授权', '/user/auths');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57ea208f0005', '2019-04-26 12:32:03', '批量删除', 'DELETE', '2019-04-26 12:32:03', '批量删除', '/user/batch');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57ea968c0006', '2019-04-26 12:32:33', '分页获取用户信息 ', 'GET', '2019-04-26 12:32:33', '分页获取用户信息 ', '/user/page/*/*');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57eb3c910007', '2019-04-26 12:33:15', '修改用户密码', 'PUT', '2019-04-26 12:33:15', '修改用户密码', '/user/psw');
INSERT INTO `resources` VALUES ('2c90825e6a57e3bf016a57ec2a140008', '2019-04-26 12:34:16', '通过用户名搜索分页返回', 'GET', '2019-04-26 12:34:16', '搜索用户', '/user/search/*/*/*');
INSERT INTO `resources` VALUES ('2c90825e6a580f17016a581b22490009', '2019-04-26 13:25:34', '获取当前用户', 'GET', '2019-04-26 13:25:34', '获取当前用户', '/user/current/*');

-- ----------------------------
-- Table structure for resources_authority
-- ----------------------------
DROP TABLE IF EXISTS `resources_authority`;
CREATE TABLE `resources_authority` (
  `id` varchar(255) NOT NULL,
  `authorityId` varchar(255) DEFAULT NULL,
  `resourceId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `authorityId` (`authorityId`),
  KEY `resourceId` (`resourceId`),
  CONSTRAINT `resources_authority_ibfk_1` FOREIGN KEY (`authorityId`) REFERENCES `authority` (`id`) ON DELETE CASCADE,
  CONSTRAINT `resources_authority_ibfk_2` FOREIGN KEY (`resourceId`) REFERENCES `resources` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources_authority
-- ----------------------------
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7820009', 'JKDGH9877212', '2c90825e6a57e3bf016a57ec2a140008');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e782000a', 'JKDGH9877212', '2c90825e6a57e3bf016a57eb3c910007');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e782000b', 'JKDGH9877212', '2c90825e6a57e3bf016a57ea968c0006');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e783000c', 'JKDGH9877212', '2c90825e6a57e3bf016a57ea208f0005');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e783000d', 'JKDGH9877212', '2c90825e6a57e3bf016a57e981420004');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e783000e', 'JKDGH9877212', '2c90825e6a57e3bf016a57e8de160003');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e783000f', 'JKDGH9877212', '2c90825e6a57e3bf016a57e89ad10002');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840010', 'JKDGH9877212', '2c90825e6a57e3bf016a57e7e7fd0001');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840011', 'JKDGH9877212', '2c90825e6a57e3bf016a57e791a50000');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840012', 'JKDGH9877212', '2c90825e6a57b3bd016a57c3b2cb0015');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840013', 'JKDGH9877212', '2c90825e6a57b3bd016a57c2d2d00014');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840014', 'JKDGH9877212', '2c90825e6a57b3bd016a57c26b520013');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840015', 'JKDGH9877212', '2c90825e6a57b3bd016a57c1e3790012');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840016', 'JKDGH9877212', '2c90825e6a57b3bd016a57c0ee720011');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840017', 'JKDGH9877212', '2c90825e6a57b3bd016a57c0566e0010');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840018', 'JKDGH9877212', '2c90825e6a57b3bd016a57bf90fe000f');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e7840019', 'JKDGH9877212', '2c90825e6a57b3bd016a57bf2324000e');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e784001a', 'JKDGH9877212', '2c90825e6a57b3bd016a57be9f21000d');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e784001b', 'JKDGH9877212', '2c90825e6a57b3bd016a57bd7e36000c');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4e784001c', 'JKDGH9877212', '2c90825e6a57b3bd016a57bc71b5000b');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2b9001d', 'JKDGH9877212', '2c90825e6a57b3bd016a57bb9ee4000a');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2b9001e', 'JKDGH9877212', '2c90825e6a57b3bd016a57bb648a0009');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2b9001f', 'JKDGH9877212', '2c90825e6a57b3bd016a57b9e2a90008');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2b90020', 'JKDGH9877212', '2c90825e6a57b3bd016a57b959ba0007');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2b90021', 'JKDGH9877212', '2c90825e6a57b3bd016a57b876f40006');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2b90022', 'JKDGH9877212', '2c90825e6a57b3bd016a57b769310005');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2b90023', 'JKDGH9877212', '2c90825e6a57b3bd016a57b6f33a0004');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba0024', 'JKDGH9877212', '2c90825e6a57b3bd016a57b630d90003');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba0025', 'JKDGH9877212', '2c90825e6a57b3bd016a57b5bb190002');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba0026', 'JKDGH9877212', '2c90825e6a57b3bd016a57b559a30001');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba0027', 'JKDGH9877212', '2c90825e6a57b3bd016a57b4dd8c0000');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba0028', 'JKDGH9877212', '2c90825e6a57aa20016a57aefaa80005');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba0029', 'JKDGH9877212', '2c90825e6a57aa20016a57ad7f1a0003');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba002a', 'JKDGH9877212', '2c90825e6a57aa20016a57acdbdb0002');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba002b', 'JKDGH9877212', '2c90825e6a57aa20016a57ac749c0001');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba002c', 'JKDGH9877212', '2c90825e6a57aa20016a57ac12330000');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba002d', 'JKDGH9877212', '2c90825e6a5772f9016a578504960004');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba002e', 'JKDGH9877212', '2c90825e6a5772f9016a5784686e0003');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba002f', 'JKDGH9877212', '2c90825e6a5772f9016a578412660002');
INSERT INTO `resources_authority` VALUES ('2c90825e6a57e3bf016a57f4f2ba0030', 'JKDGH9877212', '2c90825e6a5772f9016a5782f7f50001');
INSERT INTO `resources_authority` VALUES ('2c90825e6a580f17016a581c0254000a', '2c90825e6a57e3bf016a57f741050031', '2c90825e6a580f17016a581b22490009');
INSERT INTO `resources_authority` VALUES ('2c90825e6a580f17016a581c0255000b', '2c90825e6a57e3bf016a57f741050031', '2c90825e6a57e3bf016a57eb3c910007');
INSERT INTO `resources_authority` VALUES ('2c90825e6a584ffa016a58630a3a0000', '2c90825e6a57e3bf016a57f741050031', '2c90825e6a57b3bd016a57b4dd8c0000');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(255) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2c90825e6a580f17016a5815ac760002', '2019-04-26 13:19:37', '普通用户', '2019-04-26 13:19:37', 'BASE_USER');
INSERT INTO `role` VALUES ('2c9082e8699de4ce01699f72195b00ac', '2018-10-30 21:26:40', '系统管理员', '2018-10-30 21:26:40', 'SYS_ADMIN');

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `id` varchar(255) NOT NULL,
  `authorityId` varchar(255) DEFAULT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `authorityId` (`authorityId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `role_authority_ibfk_1` FOREIGN KEY (`authorityId`) REFERENCES `authority` (`id`) ON DELETE CASCADE,
  CONSTRAINT `role_authority_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES ('1', 'JKDGH9877212', '2c9082e8699de4ce01699f72195b00ac');
INSERT INTO `role_authority` VALUES ('2c90825e6a5874a1016a58789fe30002', '2c90825e6a57e3bf016a57f741050031', '2c90825e6a580f17016a5815ac760002');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `isEnable` bit(1) DEFAULT NULL,
  `isExpired` bit(1) DEFAULT NULL,
  `isLocked` bit(1) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2c90825e6a57590a016a57606cea0000', '18', '2019-04-26 10:01:38', '..............', 'admin@sys.com', 'admin-real', '1', '', null, null, '2019-04-26 10:22:22', '$2a$10$UpmImoNqjfneLsH.kH9xzuaro.ArzH2PKqCjB.ADBro4oRpnIVsdC', 'admin');
INSERT INTO `sys_user` VALUES ('2c90825e6a580f17016a5817650d0005', '18', '2019-04-26 13:21:29', 'aasdasd', 'wzr@sys.com', 'wzr', '1', '', '\0', '\0', '2019-04-26 14:26:21', '$2a$10$VZfZ75xzfh0Rz9SlHvv51eKoTcrfOTBb9DCD/dRbGqLsxBF3Ft11W', 'wzr');
INSERT INTO `sys_user` VALUES ('40283a82684c83da01684c867e800000', '20', '2018-09-05 13:54:02', '..............', 'yyy@sys.com', '系统管理员', '1', '', '\0', '\0', '2019-03-21 14:01:49', '$2a$10$oQU.1tCM6hJc4ZoWtwsL3eALxm8BzAuHHZLwcTpn4TacufT8Q3zQC', 'yuit');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(255) NOT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  KEY `userId` (`userId`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('123dfFDFFF', '2c9082e8699de4ce01699f72195b00ac', '40283a82684c83da01684c867e800000');
INSERT INTO `user_role` VALUES ('2c90825e6a580f17016a581371b20000', '2c9082e8699de4ce01699f72195b00ac', '2c90825e6a57590a016a57606cea0000');
INSERT INTO `user_role` VALUES ('2c90825e6a580f17016a5817aa870006', '2c90825e6a580f17016a5815ac760002', '2c90825e6a580f17016a5817650d0005');
