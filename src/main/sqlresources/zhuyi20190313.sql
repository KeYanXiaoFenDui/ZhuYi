/*
Navicat MySQL Data Transfer

Source Server         : mty
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : zhuyi

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-03-13 19:18:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `acc_status` int(4) DEFAULT NULL COMMENT '账号状态(1有效,,,0无效)',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `role_id` int(11) DEFAULT NULL COMMENT '身份类型Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1', 'admin1', '123456', '超级管理员', '1', '2019-03-12 18:55:04', '1');
INSERT INTO `admin` VALUES ('2', '1', 'xsl', '123123', '审了吗', '2', '2019-03-13 11:00:14', '1');
INSERT INTO `admin` VALUES ('3', '1', 'werwr', '123456', '顺丰', '1', '2019-03-13 11:00:40', '1');
INSERT INTO `admin` VALUES ('4', '1', 'jiasdf', '123456', '路人甲', '2', '2019-03-13 11:01:16', '1');
INSERT INTO `admin` VALUES ('5', '1', 'admin5', '123456', '管理员5', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('6', '1', 'admin6', '123456', '管理员6', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('7', '1', 'admin7', '123456', '管理员7', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('8', '1', 'admin8', '123456', '管理员8', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('9', '1', 'admin9', '123456', '管理员9', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('10', '1', 'admin10', '123456', '管理员10', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('11', '1', 'admin11', '123456', '管理员11', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('12', '1', 'admin12', '123456', '管理员12', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('13', '1', 'admin13', '123456', '管理员13', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('14', '1', 'admin14', '123456', '管理员14', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('15', '1', 'admin15', '123456', '管理员15', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('16', '1', 'admin16', '123456', '管理员16', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('17', '1', 'admin17', '123456', '管理员17', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('18', '1', 'admin18', '123456', '管理员18', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('19', '1', 'admin19', '123456', '管理员19', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('20', '1', 'admin20', '123456', '管理员20', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('21', '1', 'admin21', '123456', '管理员21', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('22', '1', 'admin22', '123456', '管理员22', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('23', '1', 'admin23', '123456', '管理员23', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('24', '1', 'admin24', '123456', '管理员24', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('25', '1', 'admin25', '123456', '管理员25', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('26', '1', 'admin26', '123456', '管理员26', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('27', '1', 'admin27', '123456', '管理员27', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('28', '1', 'admin28', '123456', '管理员28', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('29', '1', 'admin29', '123456', '管理员29', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('30', '1', 'admin30', '123456', '管理员30', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('31', '1', 'admin31', '123456', '管理员31', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('32', '1', 'admin32', '123456', '管理员32', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('33', '1', 'admin33', '123456', '管理员33', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('34', '1', 'admin34', '123456', '管理员34', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('35', '1', 'admin35', '123456', '管理员35', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('36', '1', 'admin36', '123456', '管理员36', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('37', '1', 'admin37', '123456', '管理员37', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('38', '1', 'admin38', '123456', '管理员38', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('39', '1', 'admin39', '123456', '管理员39', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('40', '1', 'admin40', '123456', '管理员40', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('41', '1', 'admin41', '123456', '管理员41', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('42', '1', 'admin42', '123456', '管理员42', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('43', '1', 'admin43', '123456', '管理员43', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('44', '1', 'admin44', '123456', '管理员44', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('45', '1', 'admin45', '123456', '管理员45', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('46', '1', 'admin46', '123456', '管理员46', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('47', '1', 'admin47', '123456', '管理员47', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('48', '1', 'admin48', '123456', '管理员48', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('49', '1', 'admin49', '123456', '管理员49', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('50', '1', 'admin50', '123456', '管理员50', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('51', '1', 'admin51', '123456', '管理员51', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('52', '1', 'admin52', '123456', '管理员52', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('53', '1', 'admin53', '123456', '管理员53', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('54', '1', 'admin54', '123456', '管理员54', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('55', '1', 'admin55', '123456', '管理员55', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('56', '1', 'admin56', '123456', '管理员56', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('57', '1', 'admin57', '123456', '管理员57', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('58', '1', 'admin58', '123456', '管理员58', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('59', '1', 'admin59', '123456', '管理员59', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('60', '1', 'admin60', '123456', '管理员60', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('61', '1', 'admin61', '123456', '管理员61', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('62', '1', 'admin62', '123456', '管理员62', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('63', '1', 'admin63', '123456', '管理员63', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('64', '1', 'admin64', '123456', '管理员64', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('65', '1', 'admin65', '123456', '管理员65', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('66', '1', 'admin66', '123456', '管理员66', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('67', '1', 'admin67', '123456', '管理员67', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('68', '1', 'admin68', '123456', '管理员68', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('69', '1', 'admin69', '123456', '管理员69', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('70', '1', 'admin70', '123456', '管理员70', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('71', '1', 'admin71', '123456', '管理员71', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('72', '1', 'admin72', '123456', '管理员72', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('73', '1', 'admin73', '123456', '管理员73', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('74', '1', 'admin74', '123456', '管理员74', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('75', '1', 'admin75', '123456', '管理员75', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('76', '1', 'admin76', '123456', '管理员76', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('77', '1', 'admin77', '123456', '管理员77', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('78', '1', 'admin78', '123456', '管理员78', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('79', '1', 'admin79', '123456', '管理员79', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('80', '1', 'admin80', '123456', '管理员80', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('81', '1', 'admin81', '123456', '管理员81', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('82', '1', 'admin82', '123456', '管理员82', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('83', '1', 'admin83', '123456', '管理员83', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('84', '1', 'admin84', '123456', '管理员84', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('85', '1', 'admin85', '123456', '管理员85', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('86', '1', 'admin86', '123456', '管理员86', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('87', '1', 'admin87', '123456', '管理员87', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('88', '1', 'admin88', '123456', '管理员88', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('89', '1', 'admin89', '123456', '管理员89', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('90', '1', 'admin90', '123456', '管理员90', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('91', '1', 'admin91', '123456', '管理员91', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('92', '1', 'admin92', '123456', '管理员92', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('93', '1', 'admin93', '123456', '管理员93', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('94', '1', 'admin94', '123456', '管理员94', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('95', '1', 'admin95', '123456', '管理员95', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('96', '1', 'admin96', '123456', '管理员96', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('97', '1', 'admin97', '123456', '管理员97', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('98', '1', 'admin98', '123456', '管理员98', '1', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('99', '1', 'admin99', '123456', '管理员99', '1', '2019-03-13 15:33:09', '1');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态位',
  `name` varchar(200) DEFAULT NULL COMMENT '类型名称',
  `order` int(4) DEFAULT NULL COMMENT '权重',
  `level` int(4) DEFAULT NULL COMMENT '分类级别',
  `parent_id` int(11) DEFAULT NULL COMMENT '所属父类ID（当分类级别为1时，该列值为空）',
  `type` int(4) DEFAULT NULL COMMENT '分类种类（1：影视剧类型；2：场景风格；3：场景类型）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for `crew_service`
-- ----------------------------
DROP TABLE IF EXISTS `crew_service`;
CREATE TABLE `crew_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态位',
  `name` varchar(200) DEFAULT NULL COMMENT '服务名称',
  `order` int(3) DEFAULT NULL COMMENT '权重',
  `service_logo_url` varchar(2000) DEFAULT NULL COMMENT '图标链接',
  `service_pic_url` text COMMENT '服务图片',
  `description` text COMMENT '服务描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务表';

-- ----------------------------
-- Records of crew_service
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `title` varchar(50) DEFAULT NULL COMMENT '菜单标题',
  `menu_no` varchar(20) DEFAULT NULL COMMENT '菜单编号',
  `parent_menu_no` varchar(20) DEFAULT NULL COMMENT '父菜单编号',
  `type` int(4) DEFAULT NULL COMMENT '菜单级别(1父,,,2子)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', 'P001', null, '1', '2019-02-28 16:25:19', '1');
INSERT INTO `menu` VALUES ('2', '场景管理', 'P002', null, '1', '2019-02-28 16:26:13', '1');
INSERT INTO `menu` VALUES ('3', '需求管理', 'P003', null, '1', '2019-02-28 16:26:27', '1');
INSERT INTO `menu` VALUES ('4', '内容管理', 'P004', null, '1', '2019-02-28 16:26:40', '1');
INSERT INTO `menu` VALUES ('5', '分类管理', 'P005', null, '1', '2019-02-28 16:26:51', '1');
INSERT INTO `menu` VALUES ('6', '权限管理', 'P006', null, '1', '2019-02-28 16:27:03', '1');
INSERT INTO `menu` VALUES ('7', '用户列表', 'K001', 'P001', '2', '2019-02-28 16:27:44', '1');
INSERT INTO `menu` VALUES ('8', '场景列表', 'K002', 'P002', '2', '2019-02-28 16:28:11', '1');
INSERT INTO `menu` VALUES ('9', '场景审核列表', 'K003', 'P002', '2', '2019-02-28 16:28:25', '1');
INSERT INTO `menu` VALUES ('10', '需求列表', 'K004', 'P003', '2', '2019-02-28 16:28:43', '1');
INSERT INTO `menu` VALUES ('11', '剧组服务列表', 'K005', 'P004', '2', '2019-02-28 16:29:07', '1');
INSERT INTO `menu` VALUES ('12', '反馈信息', 'K006', 'P004', '2', '2019-02-28 16:29:20', '1');
INSERT INTO `menu` VALUES ('13', '影视剧类型列表', 'K007', 'P005', '2', '2019-02-28 16:29:43', '1');
INSERT INTO `menu` VALUES ('14', '场景风格列表', 'K008', 'P005', '2', '2019-02-28 16:30:07', '1');
INSERT INTO `menu` VALUES ('15', '场景类型列表', 'K009', 'P005', '2', '2019-02-28 16:30:21', '1');
INSERT INTO `menu` VALUES ('16', '管理员列表', 'K010', 'P006', '2', '2019-02-28 16:30:41', '1');
INSERT INTO `menu` VALUES ('17', '身份列表', 'K011', 'P006', '2', '2019-02-28 16:30:55', '1');
INSERT INTO `menu` VALUES ('18', '操作日志', 'K012', 'P006', '2', '2019-02-28 16:31:07', '1');

-- ----------------------------
-- Table structure for `menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `role_id` int(11) DEFAULT NULL COMMENT '身份Id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单身份关系表';

-- ----------------------------
-- Records of menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for `operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员Id',
  `admin_name` varchar(100) DEFAULT NULL COMMENT '管理员名称',
  `menu_no` varchar(50) DEFAULT NULL COMMENT '菜单编号',
  `operate_title` varchar(50) DEFAULT NULL COMMENT '操作菜单',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间(操作时间)',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理后台操作日志表';

-- ----------------------------
-- Records of operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for `recommendation`
-- ----------------------------
DROP TABLE IF EXISTS `recommendation`;
CREATE TABLE `recommendation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `req_id` int(11) DEFAULT NULL COMMENT '需求Id',
  `recommend_status` int(4) DEFAULT NULL COMMENT '推荐状态(0未推荐,,,,1已推荐)',
  `stage_type` int(4) DEFAULT NULL COMMENT '场景分类',
  `stage_title_ch` varchar(100) DEFAULT NULL COMMENT '场景分类中文名称',
  `stage_title_en` varchar(100) DEFAULT NULL COMMENT '场景分类英文名称',
  `stage_id` int(11) DEFAULT NULL COMMENT '场景Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operate_admin_id` int(11) DEFAULT NULL COMMENT '操作管理员Id',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐表';

-- ----------------------------
-- Records of recommendation
-- ----------------------------

-- ----------------------------
-- Table structure for `response_info`
-- ----------------------------
DROP TABLE IF EXISTS `response_info`;
CREATE TABLE `response_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态位',
  `content` text COMMENT '反馈信息',
  `type` varchar(2) DEFAULT NULL COMMENT '反馈类型（1场景，2需求）',
  `object_id` int(11) DEFAULT NULL COMMENT '所属需求/场景ID',
  `user_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求表';

-- ----------------------------
-- Records of response_info
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `title` varchar(50) DEFAULT NULL COMMENT '身份名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='身份表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `stage`
-- ----------------------------
DROP TABLE IF EXISTS `stage`;
CREATE TABLE `stage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  `name` varchar(200) DEFAULT NULL COMMENT '场景名称',
  `country` int(11) DEFAULT NULL COMMENT '国家标识',
  `province` int(11) DEFAULT NULL COMMENT '省级标识',
  `city` int(11) DEFAULT NULL COMMENT '市级标识',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `film_type_id` int(11) DEFAULT NULL COMMENT '影视类型ID',
  `stage_main_type_id` int(11) DEFAULT NULL COMMENT '场景一级类型ID',
  `stage_sub_type_id` int(11) DEFAULT NULL COMMENT '场景二级类型ID',
  `stage_style_id` int(11) DEFAULT NULL COMMENT '场景风格ID',
  `stage_area` int(4) DEFAULT NULL COMMENT '场景面积（1：0-100㎡；2：100-300㎡:3：300-500㎡；4：500-1000㎡:5：1000㎡以上）',
  `work_time` int(4) DEFAULT NULL COMMENT '可拍摄时间（1：工作日；2：周末；3：不限）',
  `parking` int(4) DEFAULT NULL COMMENT '停车位',
  `voltage` int(4) DEFAULT NULL COMMENT '电压',
  `other_note` varchar(500) DEFAULT NULL COMMENT '其他配套',
  `stage_desc` varchar(500) DEFAULT NULL COMMENT '场景描述',
  `pic_url` text COMMENT '图片链接',
  `video_url` varchar(2000) DEFAULT NULL COMMENT '视频链接',
  `file_url` varchar(2000) DEFAULT NULL COMMENT '文件链接',
  `response_info_id` int(11) DEFAULT NULL COMMENT '反馈信息id',
  `process_status` int(4) DEFAULT NULL COMMENT '流程状态',
  `user_id` int(11) DEFAULT NULL COMMENT '发布者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场景表';

-- ----------------------------
-- Records of stage
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `acc_status` int(4) DEFAULT NULL COMMENT '账号状态(0无效,,,,1待激活,,,2激活)',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `telephone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_request`
-- ----------------------------
DROP TABLE IF EXISTS `user_request`;
CREATE TABLE `user_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `req_status` int(4) DEFAULT NULL COMMENT '需求状态(0已终止,,1待推荐,,,2推荐中)',
  `contact_people` varchar(50) DEFAULT NULL COMMENT '剧组对接人',
  `job` varchar(50) DEFAULT NULL COMMENT '对接人职务',
  `telephone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `film_name` varchar(50) DEFAULT NULL COMMENT '影片剧名',
  `film_type` int(4) DEFAULT NULL COMMENT '影片类型',
  `play_platform` int(4) DEFAULT NULL COMMENT '播放平台',
  `country` int(4) DEFAULT NULL COMMENT '国家标识',
  `province` int(4) DEFAULT NULL COMMENT '省级标识',
  `city` int(4) DEFAULT NULL COMMENT '市级标识',
  `series_type` int(4) DEFAULT NULL COMMENT '影视剧类型',
  `stage_type` int(4) DEFAULT NULL COMMENT '场景分类',
  `style` int(4) DEFAULT NULL COMMENT '风格',
  `prepare_date` datetime DEFAULT NULL COMMENT '筹备日期',
  `begin_date` datetime DEFAULT NULL COMMENT '拍摄日期',
  `continue_day` int(4) DEFAULT NULL COMMENT '置景天数',
  `shoot_day` int(4) DEFAULT NULL COMMENT '拍摄天数',
  `group_people_number` int(11) DEFAULT NULL COMMENT '剧组人数',
  `group_car_number` int(11) DEFAULT NULL COMMENT '剧组车辆数',
  `budget` decimal(10,0) DEFAULT NULL COMMENT '制作预算',
  `description` varchar(9999) DEFAULT NULL COMMENT '项目描述',
  `director` varchar(50) DEFAULT NULL COMMENT '导演',
  `performer` varchar(100) DEFAULT NULL COMMENT '主演',
  `prod_company` varchar(100) DEFAULT NULL COMMENT '出品公司',
  `exe_company` varchar(100) DEFAULT NULL COMMENT '制作公司',
  `other_req` varchar(1000) DEFAULT NULL COMMENT '其它需求',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operate_admin_id` int(11) DEFAULT NULL COMMENT '操作管理员Id',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='需求表';

-- ----------------------------
-- Records of user_request
-- ----------------------------
