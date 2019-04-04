/*
SQLyog Ultimate v8.32 
MySQL - 5.5.36 : Database - zhuyi
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zhuyi` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zhuyi`;

/*Table structure for table `category` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

/*Table structure for table `crew_service` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `crew_service` */

/*Table structure for table `response_info` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `response_info` */

/*Table structure for table `stage` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stage` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
