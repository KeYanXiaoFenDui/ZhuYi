/*
Navicat MySQL Data Transfer

Source Server         : mty
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : zhuyi

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-03-04 18:18:55
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------

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
  `film_style_id` int(11) DEFAULT NULL COMMENT '影视风格ID',
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
