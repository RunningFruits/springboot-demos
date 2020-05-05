/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : mydemo

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2020-03-09 15:31:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `system_action`
-- ----------------------------
DROP TABLE IF EXISTS `system_action`;
CREATE TABLE `system_action` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `index_of` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `display_name` varchar(100) NOT NULL COMMENT '显示名称',
  `name` varchar(100) NOT NULL COMMENT '实际名称英文',
  `parent_id` bigint(10) DEFAULT NULL COMMENT '父节点id',
  `button` tinyint(1) DEFAULT NULL,
  `open_type` varchar(30) DEFAULT NULL,
  `memo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_action
-- ----------------------------
INSERT INTO `system_action` VALUES ('1', '0', '菜单', '/#', '0', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('2', '99', '系统策略配置', '/#', '1', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('3', '0', '用户管理', '/systemUser', '2', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('4', '1', '修改', 'systemUser_edit', '3', '1', null, null);
INSERT INTO `system_action` VALUES ('5', '0', '新增', 'systemUser_add', '3', '1', null, null);
INSERT INTO `system_action` VALUES ('14', '0', '首页', '/default', '1', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('15', '1', '角色管理', '/systemRole', '2', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('16', '2', '删除', 'systemUser_delete', '3', '1', null, null);
INSERT INTO `system_action` VALUES ('18', '2', '菜单管理', '/systemAction', '2', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('22', '0', '授权', 'systemRole_grantMenu', '15', '1', null, null);
INSERT INTO `system_action` VALUES ('24', '0', '新增', 'systemAction_add', '18', '1', null, null);
INSERT INTO `system_action` VALUES ('25', '1', '编辑', 'systemAction_edit', '18', '1', null, null);
INSERT INTO `system_action` VALUES ('26', '2', '删除', 'systemAction_delete', '18', '1', null, null);
INSERT INTO `system_action` VALUES ('27', '3', '重置密码', 'systemUser_reset', '3', '1', null, null);
INSERT INTO `system_action` VALUES ('28', '4', '编辑角色', 'systemUser_editRole', '3', '1', null, null);
INSERT INTO `system_action` VALUES ('29', '2', '编辑', 'systemRole_edit', '15', '1', null, null);
INSERT INTO `system_action` VALUES ('30', '3', '删除', 'systemRole_delete', '15', '1', null, null);
INSERT INTO `system_action` VALUES ('33', '1', '新增', 'systemRole_add', '15', '1', null, null);
INSERT INTO `system_action` VALUES ('34', '5', '密码修改', '/systemUser/editPasswordPage', '2', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('35', '6', '枚举管理', '/systemEnum', '2', '0', 'iframe', null);
INSERT INTO `system_action` VALUES ('36', '0', '新增', 'systemEnum_add', '35', '1', null, null);
INSERT INTO `system_action` VALUES ('37', '1', '编辑', 'systemEnum_edit', '35', '1', null, null);
INSERT INTO `system_action` VALUES ('38', '2', '删除', 'systemEnum_delete', '35', '1', null, null);

-- ----------------------------
-- Table structure for `system_enum`
-- ----------------------------
DROP TABLE IF EXISTS `system_enum`;
CREATE TABLE `system_enum` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `value` int(2) NOT NULL,
  `parent_id` bigint(10) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_enum
-- ----------------------------
INSERT INTO `system_enum` VALUES ('1', '枚举', '0', '0', 'enum');
INSERT INTO `system_enum` VALUES ('2', '密级', '0', '1', 'secret');
INSERT INTO `system_enum` VALUES ('3', '内部', '0', '2', 'secret::inside');
INSERT INTO `system_enum` VALUES ('4', '秘密', '1', '2', 'secret::secret');
INSERT INTO `system_enum` VALUES ('5', '人员状态', '0', '1', 'oeState');
INSERT INTO `system_enum` VALUES ('6', '在职', '1', '5', 'oeState::incumbency');
INSERT INTO `system_enum` VALUES ('7', '离职', '2', '5', 'oeState::quit');
INSERT INTO `system_enum` VALUES ('10', '机密', '2', '2', 'secret::confidential');

-- ----------------------------
-- Table structure for `system_log`
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `time` int(8) DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('063e38fa-91be-42e2-8dbe-5942277d4eb3', '1', 'admin', '登录成功', '0', '/login', 'admin', '0:0:0:0:0:0:0:1', '2020-03-04 18:27:01');
INSERT INTO `system_log` VALUES ('1236825818622558210', '1', 'admin', '重置密码', '0', 'com.init.demo.system.controller.SystemUserController.resetPassword()', '  id: 1', '192.168.30.189', '2020-03-09 09:27:08');
INSERT INTO `system_log` VALUES ('1236852316775567362', '1', 'admin', '保存菜单', '16', 'com.init.demo.system.controller.SystemActionController.save()', '  systemAction: SystemAction(id=39, indexOf=7, displayName=测试, name=/test, parentId=2, button=false, openType=iframe, memo=null)', '192.168.30.189', '2020-03-09 11:12:26');
INSERT INTO `system_log` VALUES ('1236852890082471938', '1', 'admin', '保存菜单', '15', 'com.init.demo.system.controller.SystemActionController.save()', '  systemAction: SystemAction(id=40, indexOf=0, displayName=测试按钮, name=testbtn, parentId=39, button=true, openType=null, memo=null)', '192.168.30.189', '2020-03-09 11:14:43');
INSERT INTO `system_log` VALUES ('1236852921011269634', '1', 'admin', '删除菜单', '33', 'com.init.demo.system.controller.SystemActionController.deleteAction()', '  id: 40', '192.168.30.189', '2020-03-09 11:14:50');
INSERT INTO `system_log` VALUES ('1236852991001620481', '1', 'admin', '删除菜单', '17', 'com.init.demo.system.controller.SystemActionController.deleteAction()', '  id: 39', '192.168.30.189', '2020-03-09 11:15:07');
INSERT INTO `system_log` VALUES ('1236853054088146945', '1', 'admin', '删除菜单', '24', 'com.init.demo.system.controller.SystemActionController.deleteAction()', '  id: 23', '192.168.30.189', '2020-03-09 11:15:22');
INSERT INTO `system_log` VALUES ('1236853196665122818', '1', 'admin', '编辑/添加枚举', '1', 'com.init.demo.system.controller.SystemEnumController.save()', '  SystemEnum: SystemEnum(id=11, name=其他, value=3, parentId=5, remark=other)', '192.168.30.189', '2020-03-09 11:15:56');
INSERT INTO `system_log` VALUES ('1236853264868700161', '1', 'admin', '编辑/添加枚举', '4', 'com.init.demo.system.controller.SystemEnumController.save()', '  SystemEnum: SystemEnum(id=11, name=其他, value=3, parentId=5, remark=oeState::other)', '192.168.30.189', '2020-03-09 11:16:12');
INSERT INTO `system_log` VALUES ('1236853278735069185', '1', 'admin', '删除枚举', '52', 'com.init.demo.system.controller.SystemEnumController.deleteEnum()', '  id: 11', '192.168.30.189', '2020-03-09 11:16:15');
INSERT INTO `system_log` VALUES ('1236854257555599361', '1', 'admin', '删除枚举', '48039', 'com.init.demo.system.controller.SystemEnumController.deleteEnum()', '  id: 11', '192.168.30.189', '2020-03-09 11:20:09');
INSERT INTO `system_log` VALUES ('1236855728242135041', '1', 'admin', '删除枚举', '30', 'com.init.demo.system.controller.SystemEnumController.deleteEnum()', '  id: 11', '192.168.30.189', '2020-03-09 11:25:59');
INSERT INTO `system_log` VALUES ('1f21a90e-d422-4281-b127-487f34f88cb5', '1', 'admin', '保存人员信息', '13', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi13, userName=ceshi13, password=$2a$04$JwJ5dQuxUvQGy17BAAF0SOZYskbvP02YVmA2p5jXskUF6jvrKod3m, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:53');
INSERT INTO `system_log` VALUES ('2134256b-79cd-48ed-8afa-dae234eb707f', '1', 'admin', '登录成功', '0', '/login', 'admin', '0:0:0:0:0:0:0:1', '2020-03-04 18:30:41');
INSERT INTO `system_log` VALUES ('22cefefe-7b39-4ec6-8c0f-08ad28f1f81d', '1', 'admin', '登录成功', '0', '/login', 'admin', '0:0:0:0:0:0:0:1', '2020-03-04 18:18:21');
INSERT INTO `system_log` VALUES ('26c451be-e06f-4dd3-8e91-fa54208434e3', '1', 'admin', '保存人员信息', '14', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi18, userName=ceshi18, password=$2a$04$3JXSG0N5JG/Wk/R7FdoNN.oEfR348HiWdoqroOWVUQQhMn4adIQHO, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:35:24');
INSERT INTO `system_log` VALUES ('284821e1-7b05-42ca-84f2-904776f5c86e', '1', 'admin', '保存人员信息', '13', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi16, userName=ceshi16, password=$2a$04$wjkoQJf6majjxhAuAy1Spe4X.5L.0amujsS.MlJE.G/pbIW6Pilmu, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:35:12');
INSERT INTO `system_log` VALUES ('28777c04-152b-4c8b-a9a0-467c7cd335c5', '1', 'admin', '登录成功', '0', '/login', 'admin', '0:0:0:0:0:0:0:1', '2020-03-04 18:32:55');
INSERT INTO `system_log` VALUES ('2d042ad1-5a10-41f3-91ee-23938deffeda', '1', 'admin', '保存人员信息', '12', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi14, userName=ceshi14, password=$2a$04$FNJwHmGSYgZ94RsPpQv2s.xRHt8AO5KvHcRwwjydnVWnzh.wcRv9e, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:59');
INSERT INTO `system_log` VALUES ('31623b81-57ed-467f-9f73-c607fa5ee0f0', '1', 'admin', '保存人员信息', '14', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi5, userName=ceshi5, password=$2a$04$kf1qixBUmBQoI.ySlaoST.A9SirLQDLUegS8LPBv2uHyb.MLR05Qq, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:33:53');
INSERT INTO `system_log` VALUES ('348d2e95-640c-4ef5-bb73-53d66a40e481', '1', 'admin', '保存人员信息', '5', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi6, userName=ceshi6, password=$2a$04$a9lY4Db9rIMeeEL57A7UiuJ0exgoLO7oauWNc6btn6QbU9.nT2GOO, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:00');
INSERT INTO `system_log` VALUES ('3f97f87c-bab4-4d18-8cdc-e19134c0fcf8', '1', 'admin', '登录成功', '0', '/login', 'admin', '192.168.30.189', '2020-03-03 11:47:24');
INSERT INTO `system_log` VALUES ('4279f983-22c2-4761-966f-18d4d241b8d6', '1', 'admin', '保存人员信息', '13', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi12, userName=ceshi12, password=$2a$04$1v7sbBngW3pOUj2TsXjq1evyH8If1tEnlclX/DsUvyhJzRDoZFDsy, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:47');
INSERT INTO `system_log` VALUES ('46c03f86-1b5e-4f80-9a90-ca17e63b201e', '1', 'admin', '保存人员信息', '7', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi3, userName=ceshi3, password=$2a$04$htwdvwaf340gV.j8Q4noLuoaSTGREmMtd3TK3pVCA0LrcrEdoqm6S, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:33:31');
INSERT INTO `system_log` VALUES ('515f8149-dfd8-4776-ba8e-345059b77099', '1', 'admin', '保存人员信息', '4', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi4, userName=ceshi3, password=$2a$04$29IZrwSKdSvjXV6TjmtQjuK2L/i785fmjse7/M7clDxTM217aTNay, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:33:38');
INSERT INTO `system_log` VALUES ('5f7bf59e-ff1e-4dd1-bef9-7170688f9db5', '1', 'admin', '保存人员信息', '4', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi17, userName=ceshi17, password=$2a$04$8QmJ1wk238fpDvxF.iW6d.RijzIAMYgIMHoAzs7uf8ON3IS1/GkXm, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:35:18');
INSERT INTO `system_log` VALUES ('71904f00-76f1-46d6-978a-3a18a6d359ac', '1', 'admin', '保存人员信息', '12', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi19, userName=ceshi19, password=$2a$04$.c54Pz89p83bozPrhIy.6OfeiWXW/ttfeh0hGnbVLGvfp3XOW7kqS, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:35:31');
INSERT INTO `system_log` VALUES ('757f5ace-356a-4aa4-97cc-6ee004d6198f', '1', 'admin', '保存人员信息', '12', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi20, userName=ceshi20, password=$2a$04$jTvM.0dHywtzS/rwp4EHzu8DbkQZdIawfFFy3cjJKyTbfErRi34c2, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:35:40');
INSERT INTO `system_log` VALUES ('89a827bb-e78c-4244-991b-654ac4de5dfc', '1', 'admin', '登录成功', '0', '/login', 'admin', '0:0:0:0:0:0:0:1', '2020-03-04 18:28:18');
INSERT INTO `system_log` VALUES ('8bb0cb70-a2cf-4981-8749-0aa992be4250', '1', 'admin', '保存人员信息', '13', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi11, userName=ceshi11, password=$2a$04$wau2PXv8Z7nbYwINZClKoOsnHBeFXm8uQtaaF2DH4QrLipEEByk52, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:39');
INSERT INTO `system_log` VALUES ('92c83dff-473e-44ea-81ce-2f144d27699e', '1', 'admin', '登录成功', '0', '/login', 'admin', '192.168.30.189', '2020-03-03 13:21:59');
INSERT INTO `system_log` VALUES ('936eb113-ca7c-4145-abb8-fdadcd67259d', null, null, '登出成功', '0', '/logout', 'admin', '192.168.30.189', '2020-03-03 11:47:54');
INSERT INTO `system_log` VALUES ('9e9a6d63-a415-43ed-b01d-e73b73f97d5b', '1', 'admin', '保存人员信息', '14', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi7, userName=ceshi7, password=$2a$04$ff29Iey72EStX6ug9KT7Ye5zzAYol4.OM4wDKtmWuwbrp2IID44S2, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:12');
INSERT INTO `system_log` VALUES ('a55caf66-eed1-4a33-9d4e-1430714e08f2', '1', 'admin', '保存人员信息', '10', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi1, userName=ceshi1, password=$2a$04$BLxKcPebz/B5o4siM3DFz.580.l7cFyRIqpVJxsvB7Y6zXq39ZPye, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:33:15');
INSERT INTO `system_log` VALUES ('ab7ba866-df2a-40cf-85c8-992035614936', '1', 'admin', '保存人员信息', '4', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi15, userName=ceshi15, password=$2a$04$EWmpO5HDtA0ukUSrmlnrBO9AtV2icKBPFRqpq6LinM88w/DtrxIqm, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:35:05');
INSERT INTO `system_log` VALUES ('b4af411f-1418-40fe-885f-428dc86f1488', '1', 'admin', '保存人员信息', '14', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi8, userName=ceshi8, password=$2a$04$ZkYotxT26gnlJ.oi51wVVeT0K8Pw7XwczLTOFRmVe6ddepClHPmF6, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:18');
INSERT INTO `system_log` VALUES ('cd33d68c-e314-4eb3-85c4-cd015dbfc4ef', '1', 'admin', '保存人员信息', '5', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi21, userName=ceshi21, password=$2a$04$wfNWaGZ6uJEycasPGNqsoussO9fQTVGQ94I3u9w0Hyyk143FgobSC, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:35:47');
INSERT INTO `system_log` VALUES ('dc98aa74-8cf4-40fd-8fb9-a375032a5eb6', '1', 'admin', '保存人员信息', '13', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi9, userName=ceshi9, password=$2a$04$U7luPA14Lgs41EpACTbq8O6.VpytEmJ0iIN/yUoJQiNS5l5KEHB6G, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:24');
INSERT INTO `system_log` VALUES ('e339b58b-04c5-4c31-bf88-42b3cf12d330', '1', 'admin', '保存人员信息', '13', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi2, userName=ceshi2, password=$2a$04$vEmUnHIGNBCDfauhGAs9WelyLD1kz6kOTt3wc42RZkS/JgUqFK.r., createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:33:25');
INSERT INTO `system_log` VALUES ('e9525326-8c3b-4d0b-99c2-2b2cb4fa8407', '1', 'admin', '登录成功', '0', '/login', 'admin', '192.168.30.189', '2020-03-03 11:48:02');
INSERT INTO `system_log` VALUES ('ee34301f-6b02-4dae-ac5f-14c94cc21644', '1', 'admin', '保存人员信息', '13', 'SystemUserController.save()', '  systemUser: SystemUser(id=, loginName=ceshi10, userName=ceshi10, password=$2a$04$Es/XjjefEdj.oS5Nrhru8u3a84uNXoEDTXCu2YaNu/AGKjB2S.dJm, createTime=null, modifyTime=null, remark=, del=false)', '0:0:0:0:0:0:0:1', '2020-03-04 18:34:32');
INSERT INTO `system_log` VALUES ('fed0d870-01c0-434f-886e-9ca4ff72ee0e', '1', 'admin', '登录成功', '0', '/login', 'admin', '0:0:0:0:0:0:0:1', '2020-03-04 18:22:55');
INSERT INTO `system_log` VALUES ('ff644a91-5126-4019-92e2-08eb9ccd8ee5', '1', 'admin', '登录成功', '0', '/login', 'admin', '192.168.30.189', '2020-03-03 11:43:14');

-- ----------------------------
-- Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `display_name` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', 'admin', '高级管理员', null, null, null);

-- ----------------------------
-- Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` varchar(36) NOT NULL,
  `login_name` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL COMMENT '员工编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(100) DEFAULT NULL,
  `del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('08d8ea70-60e5-4678-9e7c-3c0dca6efee7', 'ceshi2', 'ceshi2', '$2a$04$vEmUnHIGNBCDfauhGAs9WelyLD1kz6kOTt3wc42RZkS/JgUqFK.r.', '2020-03-04 18:33:25', '2020-03-04 18:33:25', '', '0');
INSERT INTO `system_user` VALUES ('1', 'admin', 'admin', '$2a$04$743jmjDMIQu8e/OqtFFKWeCKOdyKgjKsvbN7fBMm1ynjm98WCI4E6', '2020-02-11 13:47:12', '2020-03-09 09:27:08', '', '0');
INSERT INTO `system_user` VALUES ('19fef54a-a66d-49f4-9784-0d06db8d4565', 'ceshi21', 'ceshi21', '$2a$04$wfNWaGZ6uJEycasPGNqsoussO9fQTVGQ94I3u9w0Hyyk143FgobSC', '2020-03-04 18:35:47', '2020-03-04 18:35:47', '', '0');
INSERT INTO `system_user` VALUES ('1d2a7368-fc39-4881-a98e-2db2c83f3b40', 'ceshi16', 'ceshi16', '$2a$04$wjkoQJf6majjxhAuAy1Spe4X.5L.0amujsS.MlJE.G/pbIW6Pilmu', '2020-03-04 18:35:12', '2020-03-04 18:35:12', '', '0');
INSERT INTO `system_user` VALUES ('396be0de-af41-4b21-8c1e-bb46ad462a9d', 'ceshi1', 'ceshi1', '$2a$04$BLxKcPebz/B5o4siM3DFz.580.l7cFyRIqpVJxsvB7Y6zXq39ZPye', '2020-03-04 18:33:15', '2020-03-04 18:33:15', '', '0');
INSERT INTO `system_user` VALUES ('50f90324-4ff2-483c-b8ea-31e77f2982b7', 'ceshi19', 'ceshi19', '$2a$04$.c54Pz89p83bozPrhIy.6OfeiWXW/ttfeh0hGnbVLGvfp3XOW7kqS', '2020-03-04 18:35:31', '2020-03-04 18:35:31', '', '0');
INSERT INTO `system_user` VALUES ('52debfb1-ae8b-491e-8f5d-d2bf66e1f37d', 'ceshi18', 'ceshi18', '$2a$04$3JXSG0N5JG/Wk/R7FdoNN.oEfR348HiWdoqroOWVUQQhMn4adIQHO', '2020-03-04 18:35:24', '2020-03-04 18:35:24', '', '0');
INSERT INTO `system_user` VALUES ('54d0417c-367f-4d7f-be35-4e475b2e31cb', 'ceshi17', 'ceshi17', '$2a$04$8QmJ1wk238fpDvxF.iW6d.RijzIAMYgIMHoAzs7uf8ON3IS1/GkXm', '2020-03-04 18:35:18', '2020-03-04 18:35:18', '', '0');
INSERT INTO `system_user` VALUES ('5860b6cf-d627-4f17-9c25-b05c41006d0f', 'ceshi7', 'ceshi7', '$2a$04$ff29Iey72EStX6ug9KT7Ye5zzAYol4.OM4wDKtmWuwbrp2IID44S2', '2020-03-04 18:34:12', '2020-03-04 18:34:12', '', '0');
INSERT INTO `system_user` VALUES ('5ae49caf-9279-415f-8955-a3a6f24d4a2e', 'ceshi4', 'ceshi3', '$2a$04$29IZrwSKdSvjXV6TjmtQjuK2L/i785fmjse7/M7clDxTM217aTNay', '2020-03-04 18:33:38', '2020-03-04 18:33:38', '', '0');
INSERT INTO `system_user` VALUES ('605e90ef-3c8d-4f32-8f9e-22c04b69527f', 'ceshi3', 'ceshi3', '$2a$04$htwdvwaf340gV.j8Q4noLuoaSTGREmMtd3TK3pVCA0LrcrEdoqm6S', '2020-03-04 18:33:31', '2020-03-04 18:33:31', '', '0');
INSERT INTO `system_user` VALUES ('64bd93ed-73e2-45f4-b109-2462651f1e42', 'ceshi15', 'ceshi15', '$2a$04$EWmpO5HDtA0ukUSrmlnrBO9AtV2icKBPFRqpq6LinM88w/DtrxIqm', '2020-03-04 18:35:05', '2020-03-04 18:35:05', '', '0');
INSERT INTO `system_user` VALUES ('701d386f-7a6d-4a33-b553-018ff730ef37', 'ceshi9', 'ceshi9', '$2a$04$U7luPA14Lgs41EpACTbq8O6.VpytEmJ0iIN/yUoJQiNS5l5KEHB6G', '2020-03-04 18:34:24', '2020-03-04 18:34:24', '', '0');
INSERT INTO `system_user` VALUES ('804d2f08-3683-4070-bb38-3e5a6800445f', 'ceshi10', 'ceshi10', '$2a$04$Es/XjjefEdj.oS5Nrhru8u3a84uNXoEDTXCu2YaNu/AGKjB2S.dJm', '2020-03-04 18:34:32', '2020-03-04 18:34:32', '', '0');
INSERT INTO `system_user` VALUES ('8e83ca60-de77-4086-ab09-e487851101e1', 'ceshi14', 'ceshi14', '$2a$04$FNJwHmGSYgZ94RsPpQv2s.xRHt8AO5KvHcRwwjydnVWnzh.wcRv9e', '2020-03-04 18:34:59', '2020-03-04 18:34:59', '', '0');
INSERT INTO `system_user` VALUES ('99461545-24d9-4a96-940b-13c1e48d8310', 'ceshi6', 'ceshi6', '$2a$04$a9lY4Db9rIMeeEL57A7UiuJ0exgoLO7oauWNc6btn6QbU9.nT2GOO', '2020-03-04 18:34:00', '2020-03-04 18:34:00', '', '0');
INSERT INTO `system_user` VALUES ('acec85a5-8a16-4b4c-92b4-2e35d61cabe9', 'ceshi8', 'ceshi8', '$2a$04$ZkYotxT26gnlJ.oi51wVVeT0K8Pw7XwczLTOFRmVe6ddepClHPmF6', '2020-03-04 18:34:18', '2020-03-04 18:34:18', '', '0');
INSERT INTO `system_user` VALUES ('b44e35a2-7410-4998-9e56-666fe78ddb95', 'ceshi20', 'ceshi20', '$2a$04$jTvM.0dHywtzS/rwp4EHzu8DbkQZdIawfFFy3cjJKyTbfErRi34c2', '2020-03-04 18:35:40', '2020-03-04 18:35:40', '', '0');
INSERT INTO `system_user` VALUES ('cf8508d7-2aac-4ce5-af0b-7e282c2a6eac', 'ceshi12', 'ceshi12', '$2a$04$1v7sbBngW3pOUj2TsXjq1evyH8If1tEnlclX/DsUvyhJzRDoZFDsy', '2020-03-04 18:34:47', '2020-03-04 18:34:47', '', '0');
INSERT INTO `system_user` VALUES ('d4d540ea-483a-48e5-bfc6-5b3cf8d0b6e7', 'ceshi13', 'ceshi13', '$2a$04$JwJ5dQuxUvQGy17BAAF0SOZYskbvP02YVmA2p5jXskUF6jvrKod3m', '2020-03-04 18:34:53', '2020-03-04 18:34:53', '', '0');
INSERT INTO `system_user` VALUES ('d4ed16ab-6db0-49a3-8ef9-28e9d239abb5', 'ceshi11', 'ceshi11', '$2a$04$wau2PXv8Z7nbYwINZClKoOsnHBeFXm8uQtaaF2DH4QrLipEEByk52', '2020-03-04 18:34:39', '2020-03-04 18:34:39', '', '0');
INSERT INTO `system_user` VALUES ('fc3fb783-a771-42de-b4c3-d7ae5371d2ca', 'ceshi5', 'ceshi5', '$2a$04$kf1qixBUmBQoI.ySlaoST.A9SirLQDLUegS8LPBv2uHyb.MLR05Qq', '2020-03-04 18:33:53', '2020-03-04 18:33:53', '', '0');

-- ----------------------------
-- Table structure for `sys_role_action`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_action`;
CREATE TABLE `sys_role_action` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `action_id` bigint(10) NOT NULL,
  `role_id` bigint(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_role_action
-- ----------------------------
INSERT INTO `sys_role_action` VALUES ('1', '1', '1');
INSERT INTO `sys_role_action` VALUES ('3', '2', '1');
INSERT INTO `sys_role_action` VALUES ('4', '3', '1');
INSERT INTO `sys_role_action` VALUES ('5', '5', '1');
INSERT INTO `sys_role_action` VALUES ('6', '4', '1');
INSERT INTO `sys_role_action` VALUES ('7', '16', '1');
INSERT INTO `sys_role_action` VALUES ('8', '27', '1');
INSERT INTO `sys_role_action` VALUES ('9', '28', '1');
INSERT INTO `sys_role_action` VALUES ('10', '15', '1');
INSERT INTO `sys_role_action` VALUES ('11', '22', '1');
INSERT INTO `sys_role_action` VALUES ('12', '33', '1');
INSERT INTO `sys_role_action` VALUES ('13', '29', '1');
INSERT INTO `sys_role_action` VALUES ('14', '30', '1');
INSERT INTO `sys_role_action` VALUES ('15', '18', '1');
INSERT INTO `sys_role_action` VALUES ('16', '24', '1');
INSERT INTO `sys_role_action` VALUES ('17', '25', '1');
INSERT INTO `sys_role_action` VALUES ('18', '26', '1');
INSERT INTO `sys_role_action` VALUES ('20', '34', '1');
INSERT INTO `sys_role_action` VALUES ('21', '35', '1');
INSERT INTO `sys_role_action` VALUES ('22', '36', '1');
INSERT INTO `sys_role_action` VALUES ('23', '37', '1');
INSERT INTO `sys_role_action` VALUES ('24', '38', '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(36) NOT NULL,
  `role_id` bigint(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
