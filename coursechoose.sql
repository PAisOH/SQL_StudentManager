/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL_chat
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3307
 Source Schema         : coursechoose

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 05/06/2022 19:20:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_ccourse
-- ----------------------------
DROP TABLE IF EXISTS `c_ccourse`;
CREATE TABLE `c_ccourse`  (
  `stu_id` int NOT NULL,
  `course_id` int NOT NULL,
  `ccourse_mark` int NULL DEFAULT 0,
  `ccourse_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`stu_id`, `course_id`) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  CONSTRAINT `c_ccourse_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `c_course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_ccourse_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `c_student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_ccourse
-- ----------------------------
INSERT INTO `c_ccourse` VALUES (96001, 1, 0, '2022-06-05 17:47:11');
INSERT INTO `c_ccourse` VALUES (96001, 2, 0, '2022-06-05 19:06:42');
INSERT INTO `c_ccourse` VALUES (96001, 3, 81, '2018-03-07 10:06:27');
INSERT INTO `c_ccourse` VALUES (96001, 5, 0, '2022-06-05 17:25:35');
INSERT INTO `c_ccourse` VALUES (96002, 1, 98, '2018-03-06 16:17:44');
INSERT INTO `c_ccourse` VALUES (96002, 2, 61, '2018-03-05 11:04:57');
INSERT INTO `c_ccourse` VALUES (96004, 5, 68, '2018-03-05 11:05:05');
INSERT INTO `c_ccourse` VALUES (96005, 1, 88, '2018-03-06 17:10:13');
INSERT INTO `c_ccourse` VALUES (96005, 2, 17, '2018-03-07 10:09:42');
INSERT INTO `c_ccourse` VALUES (97002, 2, 88, '2018-03-06 17:10:27');
INSERT INTO `c_ccourse` VALUES (97003, 2, 98, '2018-03-06 16:18:06');
INSERT INTO `c_ccourse` VALUES (97003, 3, 61, '2018-03-06 17:10:24');

-- ----------------------------
-- Table structure for c_class
-- ----------------------------
DROP TABLE IF EXISTS `c_class`;
CREATE TABLE `c_class`  (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_maxnum` int NOT NULL,
  `grade_id` int NOT NULL,
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `grade_id`(`grade_id` ASC) USING BTREE,
  CONSTRAINT `grade_id` FOREIGN KEY (`grade_id`) REFERENCES `c_grade` (`grade_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_class
-- ----------------------------
INSERT INTO `c_class` VALUES (1, ' 数字媒体21-1', 26, 1);
INSERT INTO `c_class` VALUES (2, ' 数字媒体21-2', 28, 1);
INSERT INTO `c_class` VALUES (3, '智能交通20-1', 32, 2);
INSERT INTO `c_class` VALUES (4, '智能交通20-2', 30, 2);
INSERT INTO `c_class` VALUES (5, '人工智能19-1', 33, 3);

-- ----------------------------
-- Table structure for c_course
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course`  (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_credit` float NOT NULL,
  `course_begin_time` datetime NOT NULL,
  `teach_id` int NOT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `teach_id`(`teach_id` ASC) USING BTREE,
  CONSTRAINT `c_course_ibfk_1` FOREIGN KEY (`teach_id`) REFERENCES `c_teacher` (`teach_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES (1, '计算机技术', 4, '2022-06-01 12:38:58', 1);
INSERT INTO `c_course` VALUES (2, '数据结构与算法', 5, '2022-06-02 00:00:00', 2);
INSERT INTO `c_course` VALUES (3, '软件工程导论', 3, '2022-06-03 00:00:00', 3);
INSERT INTO `c_course` VALUES (5, '离散数学', 4, '2022-06-07 00:00:00', 3);
INSERT INTO `c_course` VALUES (16, '计算机网络', 4, '2022-06-05 00:00:00', 6);
INSERT INTO `c_course` VALUES (17, '图形学', 6, '2022-06-05 00:00:00', 6);

-- ----------------------------
-- Table structure for c_grade
-- ----------------------------
DROP TABLE IF EXISTS `c_grade`;
CREATE TABLE `c_grade`  (
  `grade_id` int NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_grade
-- ----------------------------
INSERT INTO `c_grade` VALUES (1, '一年级');
INSERT INTO `c_grade` VALUES (2, '二年级');
INSERT INTO `c_grade` VALUES (3, '三年级');
INSERT INTO `c_grade` VALUES (4, '四年级');
INSERT INTO `c_grade` VALUES (5, '五年级');

-- ----------------------------
-- Table structure for c_log
-- ----------------------------
DROP TABLE IF EXISTS `c_log`;
CREATE TABLE `c_log`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `log_operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_time` datetime NULL DEFAULT NULL,
  `login_user` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_log
-- ----------------------------
INSERT INTO `c_log` VALUES (1, '[admin]管理员登陆系统', '2018-03-05 10:48:01', 'admin');
INSERT INTO `c_log` VALUES (2, '[admin]管理员登陆系统', '2018-03-05 10:50:22', 'admin');
INSERT INTO `c_log` VALUES (3, '[admin]管理员登陆系统', '2018-03-05 10:53:41', 'admin');
INSERT INTO `c_log` VALUES (4, '[admin]管理员登陆系统', '2018-03-05 10:56:28', 'admin');
INSERT INTO `c_log` VALUES (5, '[admin]管理员登陆系统', '2018-03-05 11:10:31', 'admin');
INSERT INTO `c_log` VALUES (6, '[10015]号学生登陆系统', '2018-03-05 11:11:56', '10015');
INSERT INTO `c_log` VALUES (7, '[1]号教师用户登陆系统', '2018-03-05 11:12:52', '1');
INSERT INTO `c_log` VALUES (8, '[admin]管理员登陆系统', '2018-03-05 11:30:43', 'admin');
INSERT INTO `c_log` VALUES (9, '[admin]管理员登陆系统', '2018-03-05 11:34:20', 'admin');
INSERT INTO `c_log` VALUES (10, '[admin]管理员登陆系统', '2018-03-05 15:56:50', 'admin');
INSERT INTO `c_log` VALUES (11, '[1]号教师用户登陆系统', '2018-03-05 16:04:24', '1');
INSERT INTO `c_log` VALUES (12, '[1]号教师用户登陆系统', '2018-03-05 16:04:52', '1');
INSERT INTO `c_log` VALUES (13, '[1]号教师用户登陆系统', '2018-03-05 16:08:38', '1');
INSERT INTO `c_log` VALUES (14, '[10015]号学生登陆系统', '2018-03-05 16:10:18', '10015');
INSERT INTO `c_log` VALUES (15, '[admin]管理员登陆系统', '2018-03-05 16:13:47', 'admin');
INSERT INTO `c_log` VALUES (16, '[admin]管理员登陆系统', '2018-03-06 10:05:34', 'admin');
INSERT INTO `c_log` VALUES (17, '[admin]管理员登陆系统', '2018-03-06 10:08:01', 'admin');
INSERT INTO `c_log` VALUES (18, '[admin]管理员登陆系统', '2018-03-06 14:11:53', 'admin');
INSERT INTO `c_log` VALUES (19, '[10016]号学生登陆系统', '2018-03-06 15:07:10', '10016');
INSERT INTO `c_log` VALUES (20, '[10021]号学生登陆系统', '2018-03-06 15:19:25', '10021');
INSERT INTO `c_log` VALUES (21, '[10021]号学生登陆系统', '2018-03-06 16:00:17', '10021');
INSERT INTO `c_log` VALUES (22, '[10021]号学生登陆系统', '2018-03-06 16:01:35', '10021');
INSERT INTO `c_log` VALUES (23, '[10021]号学生登陆系统', '2018-03-06 16:12:52', '10021');
INSERT INTO `c_log` VALUES (24, '[10021]号学生登陆系统', '2018-03-06 16:15:27', '10021');
INSERT INTO `c_log` VALUES (25, '[10021]号学生登陆系统', '2018-03-06 16:17:27', '10021');
INSERT INTO `c_log` VALUES (26, '[10021]号学生登陆系统', '2018-03-06 16:19:51', '10021');
INSERT INTO `c_log` VALUES (27, '[10021]号学生登陆系统', '2018-03-06 16:40:26', '10021');
INSERT INTO `c_log` VALUES (28, '[10021]号学生登陆系统', '2018-03-06 16:42:44', '10021');
INSERT INTO `c_log` VALUES (29, '[10021]号学生登陆系统', '2018-03-06 16:45:49', '10021');
INSERT INTO `c_log` VALUES (30, '[admin]管理员登陆系统', '2018-03-06 17:00:52', 'admin');
INSERT INTO `c_log` VALUES (31, '[admin]管理员登陆系统', '2018-03-06 17:01:05', 'admin');
INSERT INTO `c_log` VALUES (32, '[admin]管理员登陆系统', '2018-03-07 10:01:59', 'admin');
INSERT INTO `c_log` VALUES (33, '[10021]号学生登陆系统', '2018-03-07 10:09:21', '10021');
INSERT INTO `c_log` VALUES (34, '[10015]号学生登陆系统', '2022-06-05 15:35:48', '10015');
INSERT INTO `c_log` VALUES (35, '[Admin]管理员登陆系统', '2022-06-05 17:06:01', 'Admin');
INSERT INTO `c_log` VALUES (36, '[admin]管理员登陆系统', '2022-06-05 17:12:02', 'admin');
INSERT INTO `c_log` VALUES (37, '[Admin]管理员登陆系统', '2022-06-05 17:25:17', 'Admin');
INSERT INTO `c_log` VALUES (38, '[Admin]管理员登陆系统', '2022-06-05 17:36:19', 'Admin');
INSERT INTO `c_log` VALUES (39, '[Admin]管理员登陆系统', '2022-06-05 17:39:09', 'Admin');
INSERT INTO `c_log` VALUES (40, '[Admin]管理员登陆系统', '2022-06-05 19:03:01', 'Admin');

-- ----------------------------
-- Table structure for c_student
-- ----------------------------
DROP TABLE IF EXISTS `c_student`;
CREATE TABLE `c_student`  (
  `stu_id` int NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `stu_sex` char(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `class_id` int NOT NULL,
  `stu_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '1',
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10023 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_student
-- ----------------------------
INSERT INTO `c_student` VALUES (96001, '陈罗志', '男', 1, '123456');
INSERT INTO `c_student` VALUES (96002, '马小燕', '女', 1, '123456');
INSERT INTO `c_student` VALUES (96003, '刘东明', '女', 2, '123456');
INSERT INTO `c_student` VALUES (96004, '赵志勇', '女', 2, '123456');
INSERT INTO `c_student` VALUES (96005, '司马志明', '男', 5, '123456');
INSERT INTO `c_student` VALUES (97002, '李成功', '男', 4, '123456');
INSERT INTO `c_student` VALUES (97003, '黎明', '男', 3, '123456');

-- ----------------------------
-- Table structure for c_teacher
-- ----------------------------
DROP TABLE IF EXISTS `c_teacher`;
CREATE TABLE `c_teacher`  (
  `teach_id` int NOT NULL AUTO_INCREMENT,
  `teach_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_education` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teach_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`teach_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_teacher
-- ----------------------------
INSERT INTO `c_teacher` VALUES (1, '李老师', '女', 'CS', '1');
INSERT INTO `c_teacher` VALUES (2, '陈老师', '男', 'IS', '1');
INSERT INTO `c_teacher` VALUES (3, '吴老师', '男', 'MA', '1');
INSERT INTO `c_teacher` VALUES (5, '钟老师', '男', 'MA', '1');
INSERT INTO `c_teacher` VALUES (6, '肖老师', '女', 'CS', '1');
INSERT INTO `c_teacher` VALUES (7, '蔡老师', '男', 'CS', '1');
INSERT INTO `c_teacher` VALUES (10, '张老师', '女', 'IS', '1');

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_login_time` datetime NULL DEFAULT NULL,
  `user_privilege` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_name`(`user_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES (2, 'admin', '123456', NULL, NULL, '管理员');

SET FOREIGN_KEY_CHECKS = 1;
