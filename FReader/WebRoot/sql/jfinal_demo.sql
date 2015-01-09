/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : jfinal_demo

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-01-09 17:31:00
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
  `picture` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `descri` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `download` int(10) unsigned zerofill DEFAULT NULL,
  `praise` int(10) unsigned zerofill DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
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
INSERT INTO `type` VALUES ('1', '涓汉涔︾睄');
INSERT INTO `type` VALUES ('2', '姝︿緺灏忚');
INSERT INTO `type` VALUES ('3', '鐜勫够灏忚');
INSERT INTO `type` VALUES ('4', '瑷�儏灏忚');
INSERT INTO `type` VALUES ('5', '閮藉競灏忚');
INSERT INTO `type` VALUES ('6', '鎴樹簤灏忚');
INSERT INTO `type` VALUES ('7', '鎮枒灏忚');
INSERT INTO `type` VALUES ('8', '鎺ㄧ悊灏忚');
INSERT INTO `type` VALUES ('9', '绌胯秺灏忚');
INSERT INTO `type` VALUES ('10', '鎺㈤櫓灏忚');
INSERT INTO `type` VALUES ('11', '鍘嗗彶灏忚');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(64) COLLATE utf8_bin NOT NULL,
  `picture` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'Alix', 'aabbcc112233', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('7', 'min', 'hell0', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('8', 'ming', 'ming', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('9', 'hello', 'hello', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('10', 'oli', 'olix', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('11', 'toot', 'toot', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('12', 'root', 'root', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('13', 'admin', 'admin', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('14', 'manyu', 'mamy', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('15', 'mayuu', 'mayuu', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('16', 'minture', 'minture', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('17', 'derek', 'derek', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('18', 'to', 'toe', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('19', 'cccc', 'we', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('20', 'aaaa', 'aaaa', 'upload\\43.jpg');
INSERT INTO `user` VALUES ('21', 'ee', 'aa', 'upload\\44.jpg');
INSERT INTO `user` VALUES ('22', '11', '22', 'upload\\44.jpg');
