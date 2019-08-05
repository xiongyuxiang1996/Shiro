/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-08-05 17:53:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permissionId` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(255) NOT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*；button例子：role:create,role:update,role:delete,role:view',
  `resourceType` enum('menu','button') DEFAULT NULL COMMENT '资源类型，[menu|button]',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父编号',
  `parentIds` varchar(255) DEFAULT NULL COMMENT '父编号列表',
  `available` bit(1) DEFAULT NULL COMMENT '是否可用,如果不可用将不会添加给用户',
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户管理', 'user/userList', 'user:view', 'menu', '0', '0/', '\0');
INSERT INTO `permission` VALUES ('2', '用户添加', 'user/userAdd', 'user:add', 'button', '1', '0/1', '\0');
INSERT INTO `permission` VALUES ('3', '用户删除', 'user/userDel', 'user:del', 'button', '1', '0/1', '\0');
INSERT INTO `permission` VALUES ('4', '角色管理', 'role/roleList', 'role:view', 'menu', '0', '0/', '\0');
INSERT INTO `permission` VALUES ('5', '权限管理', 'permission/permissionList', 'permission:view', 'menu', '0', '0/', '\0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的:',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `available` bit(1) DEFAULT NULL COMMENT '是否可用,如果不可用将不会添加给用户',
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `UK_33x416oeil31hpge9a6qc8jau` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员', '\0');
INSERT INTO `role` VALUES ('2', 'test', '测试', '\0');

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  KEY `FKoucecfqc8mdel2gdbo0e62mv` (`roleId`),
  KEY `FKed6d8k3dnq28rvnoboj6y6dg3` (`permissionId`),
  CONSTRAINT `FKed6d8k3dnq28rvnoboj6y6dg3` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`permissionId`),
  CONSTRAINT `FKoucecfqc8mdel2gdbo0e62mv` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolepermission
-- ----------------------------
INSERT INTO `rolepermission` VALUES ('1', '1');
INSERT INTO `rolepermission` VALUES ('1', '4');
INSERT INTO `rolepermission` VALUES ('1', '5');
INSERT INTO `rolepermission` VALUES ('1', '3');
INSERT INTO `rolepermission` VALUES ('1', '2');
INSERT INTO `rolepermission` VALUES ('2', '1');
INSERT INTO `rolepermission` VALUES ('2', '2');
INSERT INTO `rolepermission` VALUES ('2', '3');

-- ----------------------------
-- Table structure for syspermission
-- ----------------------------
DROP TABLE IF EXISTS `syspermission`;
CREATE TABLE `syspermission` (
  `permissionId` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `parentIds` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `permissionName` varchar(255) NOT NULL,
  `resourceType` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syspermission
-- ----------------------------

-- ----------------------------
-- Table structure for sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `UK_8sggqkp1sv8guimk979mf6anf` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysrole
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `privilege` varchar(255) NOT NULL COMMENT '权限名称（管理员等）',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '密码盐',
  `state` tinyint(4) NOT NULL COMMENT '用户状态（0：创建未认证；1：正常状态；2：用户被锁定）',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_jreodf78a7pl5qidfh43axdfb` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '管理员', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '2019-08-01 15:52:20');
INSERT INTO `user` VALUES ('2', '测试', 'test', 'edcb78ff1df79647affccd8241b260e7', 's6a54d3aw4d81c32as1d98a4w3w98e4d', '1', '2019-08-01 15:52:20');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `uid` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  KEY `FK63vxkaqx2q3q577qvuqhbtqs8` (`roleId`),
  KEY `FK3n0q6m9cc8sh3pg1r7meb8alv` (`uid`),
  CONSTRAINT `FK3n0q6m9cc8sh3pg1r7meb8alv` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `FK63vxkaqx2q3q577qvuqhbtqs8` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES ('1', '1');
INSERT INTO `userrole` VALUES ('2', '2');
