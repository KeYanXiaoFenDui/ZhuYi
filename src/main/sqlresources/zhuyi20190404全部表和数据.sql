/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : zhuyi

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-04-04 12:02:57
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
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1', 'admin1', '123456', '超级管理员', '5', '2019-03-26 17:23:44', '2019-03-26 17:23:44', '1');
INSERT INTO `admin` VALUES ('2', '1', 'xsl', '123123', '审了吗', '1', '2019-03-22 16:55:21', '2019-03-22 16:55:42', '0');
INSERT INTO `admin` VALUES ('3', '1', 'werwr', '123456', '顺丰', '1', '2019-03-13 11:00:40', '2019-03-13 11:00:40', '1');
INSERT INTO `admin` VALUES ('4', '1', 'jiasdf', '123456', '路人甲', '5', '2019-03-22 16:54:59', '2019-03-22 16:55:39', '0');
INSERT INTO `admin` VALUES ('5', '1', 'admin5', '123456', '管理员5', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('6', '1', 'admin6', '123456', '管理员6', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('7', '1', 'admin7', '123456', '管理员7', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('8', '1', 'admin8', '123456', '管理员8', '1', '2019-03-22 16:59:35', '2019-03-22 16:59:45', '0');
INSERT INTO `admin` VALUES ('9', '1', 'admin9', '123456', '管理员9', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('10', '1', 'admin10', '123456', '管理员10', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('11', '1', 'admin11', '123456', '管理员11', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('12', '1', 'admin12', '123456', '管理员12', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('13', '1', 'admin13', '123456', '管理员13', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('14', '1', 'admin14', '123456', '管理员14', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('15', '1', 'admin15', '123456', '管理员15', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('16', '1', 'admin16', '123456', '管理员16', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('17', '1', 'admin17', '123456', '管理员17', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('18', '1', 'admin18', '123456', '管理员18', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('19', '1', 'admin19', '123456', '管理员19', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('20', '1', 'admin20', '123456', '管理员20', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('21', '1', 'admin21', '123456', '管理员21', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('22', '1', 'admin22', '123456', '管理员22', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('23', '1', 'admin23', '123456', '管理员23', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('24', '1', 'admin24', '123456', '管理员24', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('25', '1', 'admin25', '123456', '管理员25', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('26', '1', 'admin26', '123456', '管理员26', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('27', '1', 'admin27', '123456', '管理员27', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('28', '1', 'admin28', '123456', '管理员28', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('29', '1', 'admin29', '123456', '管理员29', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('30', '1', 'admin30', '123456', '管理员30', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('31', '1', 'admin31', '123456', '管理员31', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('32', '1', 'admin32', '123456', '管理员32', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('33', '1', 'admin33', '123456', '管理员33', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('34', '1', 'admin34', '123456', '管理员34', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('35', '1', 'admin35', '123456', '管理员35', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('36', '1', 'admin36', '123456', '管理员36', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('37', '1', 'admin37', '123456', '管理员37', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('38', '1', 'admin38', '123456', '管理员38', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('39', '1', 'admin39', '123456', '管理员39', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('40', '1', 'admin40', '123456', '管理员40', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('41', '1', 'admin41', '123456', '管理员41', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('42', '1', 'admin42', '123456', '管理员42', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('43', '1', 'admin43', '123456', '管理员43', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('44', '1', 'admin44', '123456', '管理员44', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('45', '1', 'admin45', '123456', '管理员45', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('46', '1', 'admin46', '123456', '管理员46', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('47', '1', 'admin47', '123456', '管理员47', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('48', '1', 'admin48', '123456', '管理员48', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('49', '1', 'admin49', '123456', '管理员49', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('50', '1', 'admin50', '123456', '管理员50', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('51', '1', 'admin51', '123456', '管理员51', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('52', '1', 'admin52', '123456', '管理员52', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('53', '1', 'admin53', '123456', '管理员53', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('54', '1', 'admin54', '123456', '管理员54', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('55', '1', 'admin55', '123456', '管理员55', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('56', '1', 'admin56', '123456', '管理员56', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('57', '1', 'admin57', '123456', '管理员57', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('58', '1', 'admin58', '123456', '管理员58', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('59', '1', 'admin59', '123456', '管理员59', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('60', '1', 'admin60', '123456', '管理员60', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('61', '1', 'admin61', '123456', '管理员61', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('62', '1', 'admin62', '123456', '管理员62', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('63', '1', 'admin63', '123456', '管理员63', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('64', '1', 'admin64', '123456', '管理员64', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('65', '1', 'admin65', '123456', '管理员65', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('66', '1', 'admin66', '123456', '管理员66', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('67', '1', 'admin67', '123456', '管理员67', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('68', '1', 'admin68', '123456', '管理员68', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('69', '1', 'admin69', '123456', '管理员69', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('70', '1', 'admin70', '123456', '管理员70', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('71', '1', 'admin71', '123456', '管理员71', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('72', '1', 'admin72', '123456', '管理员72', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('73', '1', 'admin73', '123456', '管理员73', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('74', '1', 'admin74', '123456', '管理员74', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('75', '1', 'admin75', '123456', '管理员75', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('76', '1', 'admin76', '123456', '管理员76', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('77', '1', 'admin77', '123456', '管理员77', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('78', '1', 'admin78', '123456', '管理员78', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('79', '1', 'admin79', '123456', '管理员79', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('80', '1', 'admin80', '123456', '管理员80', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('81', '1', 'admin81', '123456', '管理员81', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('82', '1', 'admin82', '123456', '管理员82', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('83', '1', 'admin83', '123456', '管理员83', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('84', '1', 'admin84', '123456', '管理员84', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('85', '1', 'admin85', '123456', '管理员85', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('86', '1', 'admin86', '123456', '管理员86', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('87', '1', 'admin87', '123456', '管理员87', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('88', '1', 'admin88', '123456', '管理员88', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('89', '1', 'admin89', '123456', '管理员89', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('90', '1', 'admin90', '123456', '管理员90', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('91', '1', 'admin91', '123456', '管理员91', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('92', '1', 'admin92', '123456', '管理员92', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('93', '1', 'admin93', '123456', '管理员93', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('94', '1', 'admin94', '123456', '管理员94', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('95', '1', 'admin95', '123456', '管理员95', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('96', '1', 'admin96', '123456', '管理员96', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('97', '1', 'admin97', '123456', '管理员97', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '1');
INSERT INTO `admin` VALUES ('98', '1', 'admin98', '123456', '管理员98', '1', '2019-03-13 15:33:09', '2019-03-18 17:12:39', '0');
INSERT INTO `admin` VALUES ('99', '1', 'admin99', '123456', '管理员99', '1', '2019-03-13 15:33:09', '2019-03-13 15:33:09', '0');
INSERT INTO `admin` VALUES ('100', '1', 'asddsf', '123456', 'qwer', '4', '2019-03-21 15:10:50', '2019-03-21 15:10:50', '1');

-- ----------------------------
-- Table structure for `attachment`
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `file_type` int(4) DEFAULT NULL COMMENT '文件类型(1图片,,2文件,,3视频)',
  `object_type` int(4) DEFAULT NULL COMMENT '对象类型(1场景类型,,,,2剧组服务类型)',
  `object_id` int(11) DEFAULT NULL COMMENT '对象Id',
  `file_name` varchar(100) DEFAULT NULL COMMENT '名称',
  `request_url` varchar(500) DEFAULT NULL COMMENT '请求路径',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

-- ----------------------------
-- Records of attachment
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
  `type` int(4) DEFAULT NULL COMMENT '分类种类（1：影视剧类型；2：场景风格；3：场景类型; 4: 场景地区;）',
  `nameEn` varchar(200) DEFAULT NULL COMMENT '类型名称（英文）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '2019-03-28 14:26:39', '1', '中国', '1', '1', '0', '4', 'China');
INSERT INTO `category` VALUES ('2', '2019-03-28 14:26:39', '1', '英国', '2', '1', '0', '4', 'England');
INSERT INTO `category` VALUES ('3', '2019-03-28 14:26:39', '1', '日本', '2', '1', '0', '4', 'Japan');
INSERT INTO `category` VALUES ('4', '2019-03-28 14:26:39', '1', '上海', '1', '2', '1', '4', 'ShangHai');
INSERT INTO `category` VALUES ('5', '2019-03-28 14:26:39', '1', '北京', '1', '2', '1', '4', 'BeiJing');
INSERT INTO `category` VALUES ('6', '2019-03-28 14:53:52', '1', '美国', '2', '1', '0', '4', 'American');
INSERT INTO `category` VALUES ('7', '2019-03-28 15:03:43', '0', '美国', '2', '1', '0', '4', 'American1');
INSERT INTO `category` VALUES ('8', '2019-03-28 15:17:57', '1', '韩国', '2', '1', '0', '4', 'Korea');
INSERT INTO `category` VALUES ('9', '2019-03-28 15:52:43', '1', '广东省', '1', '2', '1', '4', 'GuangDongSheng');
INSERT INTO `category` VALUES ('10', '2019-03-28 16:09:43', '0', '广东省', '1', '2', '1', '4', 'GuangDongSheng');
INSERT INTO `category` VALUES ('11', '2019-03-28 16:27:05', '1', '深圳', '1', '3', '9', '4', 'ShenZhen');
INSERT INTO `category` VALUES ('12', '2019-03-28 16:27:41', '1', '广州', '1', '3', '9', '4', 'GuangZhou');
INSERT INTO `category` VALUES ('13', '2019-03-28 16:43:01', '1', '现代都市', '1', '1', '0', '1', 'Modern city');
INSERT INTO `category` VALUES ('14', '2019-03-28 16:43:01', '1', '偶像言情', '1', '1', '0', '1', 'Idol love');
INSERT INTO `category` VALUES ('15', '2019-03-28 16:43:01', '1', '古装宫廷', '1', '1', '0', '1', 'old style');
INSERT INTO `category` VALUES ('17', '2019-03-28 16:54:03', '1', '科幻', '1', '1', '0', '1', 'science fiction');
INSERT INTO `category` VALUES ('18', '2019-03-28 16:57:27', '1', '生活观察类', '1', '1', '0', '1', 'life watch');
INSERT INTO `category` VALUES ('19', '2019-03-28 17:08:43', '1', '现代中式', '1', '1', '0', '2', 'chinese modern');
INSERT INTO `category` VALUES ('20', '2019-03-28 17:11:25', '1', '古典欧式', '1', '1', '0', '2', 'Classical European style');
INSERT INTO `category` VALUES ('21', '2019-03-28 17:11:46', '0', 'sdfas', '2', '1', '0', '2', 'asd');
INSERT INTO `category` VALUES ('22', '2019-03-28 17:31:06', '1', '餐饮', '1', '1', '0', '3', 'eating');
INSERT INTO `category` VALUES ('23', '2019-03-28 17:31:25', '1', '住所', '2', '1', '0', '3', 'house');
INSERT INTO `category` VALUES ('24', '2019-03-28 17:31:39', '1', '校园', '3', '1', '0', '3', 'school');
INSERT INTO `category` VALUES ('26', '2019-03-28 17:49:33', '1', '中餐', '1', '2', '22', '3', 'china food');
INSERT INTO `category` VALUES ('27', '2019-03-28 17:49:58', '1', '西餐', '2', '2', '22', '3', 'European food');
INSERT INTO `category` VALUES ('28', '2019-03-28 17:50:25', '1', '日韩料理', '3', '2', '22', '3', 'Japan and Korea food');
INSERT INTO `category` VALUES ('29', '2019-03-28 17:50:44', '0', 'sdf', '2', '2', '22', '3', 'wer');

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
  `nameEn` varchar(200) DEFAULT NULL COMMENT '服务名称（英文）',
  `descriptionEn` varchar(200) DEFAULT NULL COMMENT '服务描述（英文）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='服务表';

-- ----------------------------
-- Records of crew_service
-- ----------------------------
INSERT INTO `crew_service` VALUES ('1', '2019-03-29 19:12:11', '1', '剧组公寓', '1', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/19-3-33/7761891.jpg', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/19-3-51/a1.png,http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/19-3-58/a2.png', '环境舒适,交通方便', 'apartment', 'Comfortable environment and convenient transportation');
INSERT INTO `crew_service` VALUES ('2', '2019-03-29 22:23:40', '0', '删除测试', '2', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/22-18-30/QQ图片20160321135942.png', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/22-18-38/mth.jpg,http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/22-18-46/a997c8.jpg', '中文描述', 'test', 'description');

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
  `url_path` varchar(100) DEFAULT NULL COMMENT '菜单访问路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', 'P001', null, '1', null, '2019-02-28 16:25:19', '1');
INSERT INTO `menu` VALUES ('2', '场景管理', 'P002', null, '1', null, '2019-02-28 16:26:13', '1');
INSERT INTO `menu` VALUES ('3', '需求管理', 'P003', null, '1', null, '2019-02-28 16:26:27', '1');
INSERT INTO `menu` VALUES ('4', '内容管理', 'P004', null, '1', null, '2019-02-28 16:26:40', '1');
INSERT INTO `menu` VALUES ('5', '分类管理', 'P005', null, '1', null, '2019-02-28 16:26:51', '1');
INSERT INTO `menu` VALUES ('6', '权限管理', 'P006', null, '1', null, '2019-02-28 16:27:03', '1');
INSERT INTO `menu` VALUES ('7', '用户列表', 'K001', 'P001', '2', '/user/getUserList', '2019-02-28 16:27:44', '1');
INSERT INTO `menu` VALUES ('8', '场景列表', 'K002', 'P002', '2', '/stage/getStageList', '2019-02-28 16:28:11', '1');
INSERT INTO `menu` VALUES ('9', '场景审核列表', 'K003', 'P002', '2', '/stage/getStageAuditList', '2019-02-28 16:28:25', '1');
INSERT INTO `menu` VALUES ('10', '需求列表', 'K004', 'P003', '2', '/req/getRequestList', '2019-02-28 16:28:43', '1');
INSERT INTO `menu` VALUES ('11', '轮播图列表', 'K005', 'P004', '2', '/content/getRotationPicList', '2019-02-28 16:29:07', '1');
INSERT INTO `menu` VALUES ('12', '剧组服务列表', 'K006', 'P004', '2', '/content/getCrewServiceList', '2019-02-28 16:29:07', '1');
INSERT INTO `menu` VALUES ('13', '反馈信息', 'K007', 'P004', '2', '/content/getResponseInfoList', '2019-02-28 16:29:20', '1');
INSERT INTO `menu` VALUES ('14', '场景地区列表', 'K008', 'P005', '2', '/category/getStageAreaList', '2019-02-28 16:29:43', '1');
INSERT INTO `menu` VALUES ('15', '影视剧类型列表', 'K009', 'P005', '2', '/category/getFilmTypeList', '2019-02-28 16:29:43', '1');
INSERT INTO `menu` VALUES ('16', '场景风格列表', 'K010', 'P005', '2', '/category/getStageStyleList', '2019-02-28 16:30:07', '1');
INSERT INTO `menu` VALUES ('17', '场景类型列表', 'K011', 'P005', '2', '/category/getStageTypeList', '2019-02-28 16:30:21', '1');
INSERT INTO `menu` VALUES ('18', '管理员列表', 'K012', 'P006', '2', '/zhuYi/getAdminList', '2019-02-28 16:30:41', '1');
INSERT INTO `menu` VALUES ('19', '身份列表', 'K013', 'P006', '2', '/admin/getRoleList', '2019-02-28 16:30:55', '1');
INSERT INTO `menu` VALUES ('20', '操作日志', 'K014', 'P006', '2', '/admin/getOperateLogList', '2019-02-28 16:31:07', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COMMENT='菜单身份关系表';

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('1', '1', '2', '2019-03-21 15:35:33', '0');
INSERT INTO `menu_role` VALUES ('2', '1', '3', '2019-03-21 15:35:42', '0');
INSERT INTO `menu_role` VALUES ('3', '1', '8', '2019-03-21 15:36:00', '0');
INSERT INTO `menu_role` VALUES ('4', '1', '9', '2019-03-21 15:36:10', '0');
INSERT INTO `menu_role` VALUES ('5', '1', '10', '2019-03-21 15:36:25', '0');
INSERT INTO `menu_role` VALUES ('6', '2', '4', '2019-03-21 15:37:03', '0');
INSERT INTO `menu_role` VALUES ('7', '2', '11', '2019-03-21 15:37:16', '0');
INSERT INTO `menu_role` VALUES ('8', '2', '12', '2019-03-21 15:37:23', '0');
INSERT INTO `menu_role` VALUES ('9', '3', '1', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('10', '3', '2', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('11', '3', '3', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('12', '3', '4', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('13', '3', '5', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('14', '3', '6', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('15', '3', '7', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('16', '3', '8', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('17', '3', '9', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('18', '3', '10', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('19', '3', '11', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('20', '3', '12', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('21', '3', '13', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('22', '3', '14', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('23', '3', '15', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('24', '3', '16', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('25', '3', '17', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('26', '3', '18', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('27', '3', '19', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('28', '3', '20', '2019-03-21 18:55:54', '0');
INSERT INTO `menu_role` VALUES ('29', '4', '2', '2019-03-22 11:44:34', '0');
INSERT INTO `menu_role` VALUES ('30', '4', '8', '2019-03-22 11:44:34', '0');
INSERT INTO `menu_role` VALUES ('31', '4', '9', '2019-03-22 11:44:34', '0');
INSERT INTO `menu_role` VALUES ('32', '4', '1', '2019-03-22 14:00:22', '0');
INSERT INTO `menu_role` VALUES ('33', '4', '2', '2019-03-22 14:00:22', '0');
INSERT INTO `menu_role` VALUES ('34', '4', '7', '2019-03-22 14:00:22', '0');
INSERT INTO `menu_role` VALUES ('35', '4', '8', '2019-03-22 14:00:22', '0');
INSERT INTO `menu_role` VALUES ('36', '4', '9', '2019-03-22 14:00:22', '0');
INSERT INTO `menu_role` VALUES ('37', '2', '4', '2019-03-22 14:00:43', '0');
INSERT INTO `menu_role` VALUES ('38', '2', '12', '2019-03-22 14:00:43', '0');
INSERT INTO `menu_role` VALUES ('39', '5', '1', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('40', '5', '2', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('41', '5', '3', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('42', '5', '4', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('43', '5', '5', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('44', '5', '6', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('45', '5', '7', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('46', '5', '8', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('47', '5', '9', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('48', '5', '10', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('49', '5', '11', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('50', '5', '12', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('51', '5', '13', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('52', '5', '14', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('53', '5', '15', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('54', '5', '16', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('55', '5', '17', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('56', '5', '18', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('57', '5', '19', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('58', '5', '20', '2019-03-22 14:25:42', '1');
INSERT INTO `menu_role` VALUES ('59', '3', '1', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('60', '3', '2', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('61', '3', '3', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('62', '3', '4', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('63', '3', '5', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('64', '3', '6', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('65', '3', '7', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('66', '3', '8', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('67', '3', '9', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('68', '3', '10', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('69', '3', '11', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('70', '3', '12', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('71', '3', '13', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('72', '3', '14', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('73', '3', '15', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('74', '3', '16', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('75', '3', '17', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('76', '3', '19', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('77', '3', '20', '2019-03-22 14:25:59', '1');
INSERT INTO `menu_role` VALUES ('78', '4', '1', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('79', '4', '2', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('80', '4', '6', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('81', '4', '7', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('82', '4', '8', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('83', '4', '9', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('84', '4', '18', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('85', '4', '19', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('86', '4', '20', '2019-03-26 16:50:11', '1');
INSERT INTO `menu_role` VALUES ('87', '1', '2', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('88', '1', '3', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('89', '1', '6', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('90', '1', '8', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('91', '1', '9', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('92', '1', '10', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('93', '1', '18', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('94', '1', '19', '2019-03-26 16:50:37', '1');
INSERT INTO `menu_role` VALUES ('95', '1', '20', '2019-03-26 16:50:37', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='管理后台操作日志表';

-- ----------------------------
-- Records of operate_log
-- ----------------------------
INSERT INTO `operate_log` VALUES ('1', '1', '超级管理员', 'K012', '权限管理->管理员列表', '2019-03-23 14:45:02', '1');
INSERT INTO `operate_log` VALUES ('2', '1', '超级管理员', 'K013', '权限管理->身份列表', '2019-03-23 14:45:32', '1');

-- ----------------------------
-- Table structure for `recommendation`
-- ----------------------------
DROP TABLE IF EXISTS `recommendation`;
CREATE TABLE `recommendation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `req_id` int(11) DEFAULT NULL COMMENT '需求Id',
  `recommend_status` int(4) DEFAULT NULL COMMENT '推荐状态(0未推荐,,,,1已推荐,,,,2临时保存)',
  `stage_title_ch` varchar(100) DEFAULT NULL COMMENT '场景分类中文名称',
  `stage_title_en` varchar(100) DEFAULT NULL COMMENT '场景分类英文名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operate_admin_id` int(11) DEFAULT NULL COMMENT '操作管理员Id',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='推荐表';

-- ----------------------------
-- Records of recommendation
-- ----------------------------
INSERT INTO `recommendation` VALUES ('1', '1', '1', '0', '男主的家', 'house12', '2019-04-02 02:23:40', '2019-04-03 14:04:14', '1', '1');
INSERT INTO `recommendation` VALUES ('2', '1', '1', '0', '女主的家', 'house1', '2019-04-02 02:25:55', '2019-04-03 11:58:48', '1', '1');
INSERT INTO `recommendation` VALUES ('7', '1', '1', '2', 'sdfsdf', 'sdff', '2019-04-02 22:14:15', '2019-04-03 14:26:50', '1', '0');
INSERT INTO `recommendation` VALUES ('8', '1', '1', '2', '男2的家', 'GuangDongSheng', '2019-04-03 11:59:00', '2019-04-03 14:26:46', '1', '0');
INSERT INTO `recommendation` VALUES ('9', '1', '1', '2', null, null, '2019-04-03 13:12:36', '2019-04-03 14:26:38', '1', '0');
INSERT INTO `recommendation` VALUES ('10', '1', '1', '2', '女2的家', 'hahah', '2019-04-03 14:26:53', '2019-04-03 14:37:55', '1', '0');

-- ----------------------------
-- Table structure for `recommendation_stage`
-- ----------------------------
DROP TABLE IF EXISTS `recommendation_stage`;
CREATE TABLE `recommendation_stage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `rec_stage_type_id` int(11) DEFAULT NULL COMMENT '推荐场景分类Id',
  `stage_id` int(11) DEFAULT NULL COMMENT '场景Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operate_admin_id` int(11) DEFAULT NULL COMMENT '操作管理员Id',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='推荐场景表';

-- ----------------------------
-- Records of recommendation_stage
-- ----------------------------
INSERT INTO `recommendation_stage` VALUES ('1', '1', '1', '2019-04-02 02:23:40', '1', '1');
INSERT INTO `recommendation_stage` VALUES ('2', '2', '2', '2019-04-02 02:23:40', '1', '1');
INSERT INTO `recommendation_stage` VALUES ('3', '1', '4', '2019-04-03 14:04:03', '1', '0');
INSERT INTO `recommendation_stage` VALUES ('4', '1', '3', '2019-04-03 14:20:38', '1', '1');
INSERT INTO `recommendation_stage` VALUES ('5', '10', '4', '2019-04-03 14:27:24', '1', '0');

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
  `process_status` int(4) DEFAULT NULL COMMENT '流程状态',
  `language` int(4) DEFAULT NULL COMMENT '语种（1：中文；2：英文）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='反馈信息表';

-- ----------------------------
-- Records of response_info
-- ----------------------------
INSERT INTO `response_info` VALUES ('1', '2019-03-30 13:25:26', '1', '场景已驳回', '1', null, null, '0', '1');
INSERT INTO `response_info` VALUES ('2', '2019-03-30 13:25:26', '1', '场景待审核', '1', '0', '0', '1', '1');
INSERT INTO `response_info` VALUES ('3', '2019-03-30 13:25:26', '1', '场景已通过', '1', null, null, '2', '1');
INSERT INTO `response_info` VALUES ('4', '2019-03-30 13:25:26', '1', '场景已驳回英文', '1', null, null, '0', '2');
INSERT INTO `response_info` VALUES ('5', '2019-03-30 13:25:26', '1', '场景待审核英文', '1', null, null, '1', '2');
INSERT INTO `response_info` VALUES ('6', '2019-03-30 13:25:26', '1', '场景已通过英文', '1', '0', '0', '2', '2');
INSERT INTO `response_info` VALUES ('7', '2019-03-30 13:25:26', '1', '需求已终止', '2', null, null, '0', '1');
INSERT INTO `response_info` VALUES ('8', '2019-03-30 13:25:26', '1', '需求待推荐', '2', null, null, '1', '1');
INSERT INTO `response_info` VALUES ('9', '2019-03-30 13:25:26', '1', '需求推荐中', '2', null, null, '2', '1');
INSERT INTO `response_info` VALUES ('10', '2019-03-30 13:25:26', '1', '需求已终止英文', '2', null, null, '0', '2');
INSERT INTO `response_info` VALUES ('11', '2019-03-30 13:25:26', '1', '需求待推荐英文', '2', null, null, '1', '2');
INSERT INTO `response_info` VALUES ('12', '2019-03-30 13:25:26', '1', '需求推荐中英文', '2', null, null, '2', '2');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `title` varchar(50) DEFAULT NULL COMMENT '身份名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='身份表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '市场部管理员', '2019-03-18 17:24:23', '2019-03-26 16:50:37', '1');
INSERT INTO `role` VALUES ('2', '内容管理员', '2019-03-22 14:00:43', '2019-03-18 17:24:31', '0');
INSERT INTO `role` VALUES ('3', '超级管理员', '2019-03-21 18:55:54', '2019-03-22 14:25:59', '1');
INSERT INTO `role` VALUES ('4', '场景管理员', '2019-03-22 14:00:22', '2019-03-26 16:50:11', '1');
INSERT INTO `role` VALUES ('5', '开发者权限', '2019-03-22 14:25:42', '2019-03-22 14:25:42', '1');

-- ----------------------------
-- Table structure for `rotation_pic`
-- ----------------------------
DROP TABLE IF EXISTS `rotation_pic`;
CREATE TABLE `rotation_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `order` int(4) DEFAULT NULL COMMENT '权重',
  `pic_name` varchar(100) DEFAULT NULL COMMENT '轮播图名称',
  `request_url` varchar(1000) DEFAULT NULL COMMENT '跳转链接',
  `pic_url` varchar(1000) DEFAULT NULL COMMENT '轮播图图片访问路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `operater_id` int(11) DEFAULT NULL COMMENT '操作者Id',
  `status` int(4) DEFAULT NULL COMMENT '状态(1有效,,,0无效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='轮播图表';

-- ----------------------------
-- Records of rotation_pic
-- ----------------------------
INSERT INTO `rotation_pic` VALUES ('1', '1', '轮播图1', 'https://www.baidu.com', 'http://sowcar.com/t6/691/1553768872x2059272752.jpg', '2019-03-28 18:29:09', '2019-03-28 18:29:09', '1', '1');
INSERT INTO `rotation_pic` VALUES ('2', '1', '轮播图2', 'testst', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/11-39-15/a4.png', '2019-03-28 19:03:38', '2019-03-29 11:43:49', '1', '1');
INSERT INTO `rotation_pic` VALUES ('3', '1', '轮播图3', 'https://www.baidu.com', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/12-4-59/mth.jpg', '2019-03-29 11:37:42', '2019-03-29 12:24:48', '1', '0');
INSERT INTO `rotation_pic` VALUES ('4', '1', '轮播图4', 'https://www.baidu.com', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/11-38-56/a2.png', '2019-03-29 11:41:19', '2019-03-29 11:43:32', '1', '1');
INSERT INTO `rotation_pic` VALUES ('5', '1', '轮播图5', 'https://www.baidu.com', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/29/11-40-10/7761891.jpg', '2019-03-29 11:44:45', '2019-03-29 11:44:45', '1', '1');

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
  `voltage` int(4) DEFAULT NULL COMMENT '电压(1:220V,,,,2:380V)',
  `other_note` varchar(500) DEFAULT NULL COMMENT '其他配套',
  `stage_desc` varchar(500) DEFAULT NULL COMMENT '场景描述',
  `pic_url` text COMMENT '图片链接',
  `video_url` varchar(2000) DEFAULT NULL COMMENT '视频链接',
  `file_url` varchar(2000) DEFAULT NULL COMMENT '文件链接',
  `response_info_id` int(11) DEFAULT NULL COMMENT '反馈信息id',
  `process_status` int(4) DEFAULT NULL COMMENT '流程状态(0已驳回,,,1待审核,,,2已通过)',
  `user_id` int(11) DEFAULT NULL COMMENT '发布者ID',
  `nameEn` varchar(200) DEFAULT NULL COMMENT '场景名称（英文）',
  `addressEn` varchar(200) DEFAULT NULL COMMENT '详细地址（英文）',
  `otherNoteEn` varchar(500) DEFAULT NULL COMMENT '其他配套（英文）',
  `stageDescEn` varchar(500) DEFAULT NULL COMMENT '场景描述（英文）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='场景表';

-- ----------------------------
-- Records of stage
-- ----------------------------
INSERT INTO `stage` VALUES ('1', '2019-03-30 19:10:47', '1', '法租界葱绿花园', '1', '9', '11', '龙岗区和平路298号', '13', '22', '26', '20', '1', '1', '1', '1', '开放式;复古风', '这个房间非常好看', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/30/19-0-27/a997c8.jpg', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/30/19-0-33/mth.jpg', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/3/30/19-0-38/QQ图片20160321135942.png', '0', '2', '0', 'Lush gardens in the French concession', '298 heping road', 'Style restoring ancient ways', 'This room is very nice');
INSERT INTO `stage` VALUES ('2', '2019-04-01 16:13:57', '1', '西关大屋', '1', '9', '12', '白云区西关路', '13', '22', '26', '19', '2', '3', '2', '1', '居家生活用品', '居家生活场所', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/15-58-38/a5.png', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/15-58-53/timg(2).jpg', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/15-59-3/a1.png', '0', '2', '1', 'west big house', 'BaiYun XiGuanLu', 'articles for daily use', 'domestic life house');
INSERT INTO `stage` VALUES ('3', '2019-04-01 16:31:46', '1', '光明顶', '1', '9', '11', '明教光明顶', '13', '22', '26', '19', '2', '1', '5', '2', '古装道具', '围攻光明顶戏份', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/16-13-34/a2.png', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/16-13-41/a3.png', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/16-13-48/a4.png', '0', '2', '2', 'GuangMingDing', 'mjgmd', 'gzdj', 'aaaaaa');
INSERT INTO `stage` VALUES ('4', '2019-04-01 16:45:56', '1', '测试场景名称', '2', '0', '0', '测试地址', '13', '23', '0', '19', '2', '2', '2', '2', '测试配套', '测试场景描述', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/16-41-1/timg(6).jpg', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/16-41-9/timg(3).jpg', 'http://breaa-pic-1255419371.pictj.myqcloud.com/association/2019/4/1/16-41-16/timg(1).jpg', '0', '2', '1', 'testStageName', 'testAddress', 'testpeitao', 'testStageDescription');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2', 'user1', '123456', '超级用户', '13412345234', 'haha@aa.com', '2019-03-27 11:52:37', '2019-03-27 11:52:37', '1');
INSERT INTO `user` VALUES ('2', '0', 'user0', '123456', '无效用户', '15212343254', 'wuxiao@aa.com', '2019-03-27 11:53:38', '2019-03-27 11:53:38', '1');
INSERT INTO `user` VALUES ('3', '1', 'userDaijihuo', '123456', '待激活用户', '13512341232', 'daijihuo@163.com', '2019-03-27 11:54:30', '2019-03-27 11:54:30', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='需求表';

-- ----------------------------
-- Records of user_request
-- ----------------------------
INSERT INTO `user_request` VALUES ('1', '1', '1', '路人甲', '联系人', '12345678901', '12345678@163.com', '我爱我家', '1', '2', '1', '2', '3', '1', '1', '1', '2019-03-27 16:48:33', '2019-04-01 16:48:42', '10', '20', '100', '12', '1230000', '有花园', '张艺谋', '章子怡；刘德华', '漫威影业', '皮克斯', '有花园', '2019-03-27 16:50:56', '2019-03-27 16:50:56', '1', '1');
