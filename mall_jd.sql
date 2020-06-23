/*
 Navicat Premium Data Transfer

 Source Server         : Mysql_local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mall_jd_test

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 23/06/2020 11:11:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jd_category
-- ----------------------------
DROP TABLE IF EXISTS `jd_category`;
CREATE TABLE `jd_category` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3694 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jd_item
-- ----------------------------
DROP TABLE IF EXISTS `jd_item`;
CREATE TABLE `jd_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) DEFAULT NULL COMMENT '商品标题',
  `url` varchar(255) DEFAULT NULL COMMENT '商品详情地址',
  PRIMARY KEY (`id`),
  KEY `title` (`title`) COMMENT 'title字段-普通索引'
) ENGINE=InnoDB AUTO_INCREMENT=39660750 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
