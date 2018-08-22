/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : db_search

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-08-22 13:21:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '文章标题',
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '文章内容',
  `state` int(1) unsigned NOT NULL COMMENT '文章状态（0：待审核；1：审核通过；2：审核不通过；）',
  `latitude` double NOT NULL COMMENT '发布文章所在位置精度',
  `longitude` double NOT NULL COMMENT '发布文章所在位置纬度',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '这是一条标题', '调皮的内容~', '0', '31.234219', '121.490601', '2018-08-21 03:01:00', '2018-08-17 00:00:00');
INSERT INTO `tb_article` VALUES ('2', '我比上一条标题长。', '上一条内容比我短。', '1', '39.915446', '116.403849', '2018-08-17 10:02:00', '2018-08-17 00:00:00');
INSERT INTO `tb_article` VALUES ('3', '我是老三', '老三的内容12345678', '2', '22.539051', '113.952254', '2018-08-20 11:03:00', '2018-08-20 00:00:00');
