/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 22/07/2019 15:00:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `authority_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称，ROLE_开头，全大写',
  `authority_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限描述',
  PRIMARY KEY (`authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('1', 'ROLE_SA', '超级管理员权限');
INSERT INTO `sys_authority` VALUES ('2', 'ROLE_ADMIN', '管理员权限');
INSERT INTO `sys_authority` VALUES ('3', 'ROLE_USER', '普通用户权限');

SET FOREIGN_KEY_CHECKS = 1;


/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 22/07/2019 15:01:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单路径',
  `menu_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '/sys', NULL);
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '/sys/user', '1');
INSERT INTO `sys_menu` VALUES ('3', '权限管理', '/sys/authority', '1');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '/sys/menu', '1');
INSERT INTO `sys_menu` VALUES ('5', 'XXX菜单', '/menu/xxx', '');
INSERT INTO `sys_menu` VALUES ('6', 'XXX菜单1', '/menu/xxx1', '5');
INSERT INTO `sys_menu` VALUES ('7', 'XXX菜单2', '/menu/xxx2', '5');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 22/07/2019 15:04:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_shortcut_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_shortcut_menu`;
CREATE TABLE `sys_shortcut_menu`  (
  `shortcut_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单id',
  `shortcut_menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单名称',
  `shortcut_menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单路径',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `shortcut_menu_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`shortcut_menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户快捷菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_shortcut_menu
-- ----------------------------
INSERT INTO `sys_shortcut_menu` VALUES ('s1', '百度', 'https://www.baidu.com', '2', NULL);
INSERT INTO `sys_shortcut_menu` VALUES ('s2', 'layui', 'https://www.layui.com/', '3', NULL);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 22/07/2019 14:59:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '软删除标识，Y/N',
  `limited_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '限制允许登录的IP集合',
  `limited_mac` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更高级别的安全限制，限制允许登录的mac地址集合',
  `expired_time` datetime NULL DEFAULT NULL COMMENT '账号失效时间，超过时间将不能登录系统',
  `last_change_pwd_time` datetime NOT NULL COMMENT '最近修改密码时间，超出时间间隔，提示用户修改密码',
  `limit_multi_login` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否允许账号同一个时刻多人在线，Y/N',
  `greate_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'xxx_sa', 'sa', '123456', 'Y', NULL, NULL, '2020-09-01 16:35:16', '2019-07-19 16:35:46', 'N', '2019-07-19 16:36:03', '2019-07-19 16:36:07');
INSERT INTO `sys_user` VALUES ('2', 'xxx_admin', 'admin', '123456', 'Y', NULL, NULL, '2020-09-01 16:35:16', '2019-07-19 16:35:46', 'N', '2019-07-19 16:36:03', '2019-07-19 16:36:07');
INSERT INTO `sys_user` VALUES ('3', 'xxx_huanzi', 'huanzi', '123456', 'Y', NULL, NULL, '2020-09-01 16:35:16', '2019-07-19 16:35:46', 'N', '2019-07-19 16:36:03', '2019-07-19 16:36:07');

SET FOREIGN_KEY_CHECKS = 1;


/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 22/07/2019 15:03:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_authority`;
CREATE TABLE `sys_user_authority`  (
  `user_authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户权限表id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  PRIMARY KEY (`user_authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_authority
-- ----------------------------
INSERT INTO `sys_user_authority` VALUES ('1', '1', '1');
INSERT INTO `sys_user_authority` VALUES ('2', '2', '2');
INSERT INTO `sys_user_authority` VALUES ('3', '3', '3');

SET FOREIGN_KEY_CHECKS = 1;


/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 22/07/2019 15:03:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu`  (
  `user_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户菜单表id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`user_menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------
INSERT INTO `sys_user_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_user_menu` VALUES ('10', '3', '6');
INSERT INTO `sys_user_menu` VALUES ('11', '3', '7');
INSERT INTO `sys_user_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_user_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_user_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_user_menu` VALUES ('41', '1', '5');
INSERT INTO `sys_user_menu` VALUES ('42', '1', '6');
INSERT INTO `sys_user_menu` VALUES ('43', '1', '7');
INSERT INTO `sys_user_menu` VALUES ('5', '2', '1');
INSERT INTO `sys_user_menu` VALUES ('51', '2', '5');
INSERT INTO `sys_user_menu` VALUES ('52', '2', '6');
INSERT INTO `sys_user_menu` VALUES ('53', '2', '7');
INSERT INTO `sys_user_menu` VALUES ('6', '2', '2');
INSERT INTO `sys_user_menu` VALUES ('7', '2', '3');
INSERT INTO `sys_user_menu` VALUES ('8', '2', '4');
INSERT INTO `sys_user_menu` VALUES ('9', '3', '5');

SET FOREIGN_KEY_CHECKS = 1;
