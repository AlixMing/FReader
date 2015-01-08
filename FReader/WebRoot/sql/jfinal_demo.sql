/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : jfinal_demo

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-01-07 18:01:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8_bin NOT NULL,
  `content` varchar(256) COLLATE utf8_bin NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_user` (`userId`),
  CONSTRAINT `blog_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `author` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `download` int(10) unsigned zerofill DEFAULT NULL,
  `praise` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_type` (`typeId`),
  CONSTRAINT `book_type` FOREIGN KEY (`typeId`) REFERENCES `type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of book
-- ----------------------------

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '个人书籍');
INSERT INTO `type` VALUES ('2', '武侠小说');
INSERT INTO `type` VALUES ('3', '玄幻小说');
INSERT INTO `type` VALUES ('4', '言情小说');
INSERT INTO `type` VALUES ('5', '都市小说');
INSERT INTO `type` VALUES ('6', '战争小说');
INSERT INTO `type` VALUES ('7', '悬疑小说');
INSERT INTO `type` VALUES ('8', '推理小说');
INSERT INTO `type` VALUES ('9', '穿越小说');
INSERT INTO `type` VALUES ('10', '探险小说');
INSERT INTO `type` VALUES ('11', '历史小说');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'Alix', 'aabbcc112233');
INSERT INTO `user` VALUES ('7', 'min', 'hell0');
INSERT INTO `user` VALUES ('8', 'a', 'a');
INSERT INTO `user` VALUES ('9', 'b', 'b');
