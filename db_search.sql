/*
Navicat MySQL Data Transfer

Source Server         : 个人Linux数据库
Source Server Version : 50633
Source Host           : 101.200.46.131:3306
Source Database       : db_search

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2018-08-20 18:43:08
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
  `create_time` date NOT NULL COMMENT '创建时间',
  `update_time` date NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '这是一条标题', '调皮的内容~', '2018-08-17', '2018-08-17');
INSERT INTO `tb_article` VALUES ('2', '我比上一条标题长。', '上一条内容比我短。', '2018-08-17', '2018-08-17');
INSERT INTO `tb_article` VALUES ('3', '我是老三', '老三的内容12345678', '2018-08-20', '2018-08-20');
