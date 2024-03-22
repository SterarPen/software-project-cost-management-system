/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : project_manage_system

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 22/03/2024 18:56:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '管理员id，也是管理员账号',
  `admin_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员名称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员登陆密码',
  `update_time` datetime NOT NULL COMMENT '管理员上次密码更新时间（隔一段时间要求更换一次密码）',
  `old_password` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '旧密码（更换密码需保证新密码与以前用过的旧密不同）',
  `expire` smallint(1) UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目经理账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application`  (
  `application_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '申请表中id',
  `application_type` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请类型',
  `application_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 申请内容',
  `status` int(11) NOT NULL COMMENT '申请状态，0-正在审批，1-审批通过，2-审批未通过，3-销毁或失效',
  `create_time` datetime NOT NULL COMMENT ' 申请创建时间',
  `destory_time` datetime NOT NULL COMMENT '申请完成或销毁时间',
  `cost` decimal(10, 2) NOT NULL COMMENT '支出金额',
  `note` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `project_id` int(11) UNSIGNED NOT NULL COMMENT '项目id，申请属于哪个项目',
  `developer_id` int(11) UNSIGNED NOT NULL COMMENT '开发人员id，申请提出是哪个开发人员',
  PRIMARY KEY (`application_id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE,
  INDEX `application_ibfk_2`(`developer_id`) USING BTREE,
  CONSTRAINT `application_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project1` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `application_ibfk_2` FOREIGN KEY (`developer_id`) REFERENCES `manager` (`manager_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for authentiication
-- ----------------------------
DROP TABLE IF EXISTS `authentiication`;
CREATE TABLE `authentiication`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `delete_time` datetime NOT NULL,
  `expire` int(1) UNSIGNED NOT NULL DEFAULT 1,
  `role` int(1) NOT NULL COMMENT '0-管理员，1-甲方，2-项目经理',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 287 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for buyer
-- ----------------------------
DROP TABLE IF EXISTS `buyer`;
CREATE TABLE `buyer`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购买方账号',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '购买方名称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '购买方登录密码',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '购买方手机号',
  `email` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '购买方邮箱',
  `create_by` int(11) NOT NULL COMMENT '账号创建者，对应系统管理员中一个id',
  `create_time` datetime NOT NULL COMMENT '账号创建时间',
  `destroy_time` datetime NULL DEFAULT NULL COMMENT '账号销毁时间',
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `handling_project` int(11) NULL DEFAULT 0 COMMENT '购买方账号登录后显示的项目ID',
  `expire` char(1) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11139 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '甲方账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cost
-- ----------------------------
DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost`  (
  `cost_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `cost_type` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cost_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `money` decimal(10, 2) NOT NULL,
  `stage_id` int(11) UNSIGNED NOT NULL,
  `developer_id` int(255) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`cost_id`) USING BTREE,
  INDEX `stage_id`(`stage_id`) USING BTREE,
  INDEX `user_id`(`developer_id`) USING BTREE,
  CONSTRAINT `cost_ibfk_1` FOREIGN KEY (`stage_id`) REFERENCES `stage` (`stage_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cost_ibfk_2` FOREIGN KEY (`developer_id`) REFERENCES `manager` (`manager_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand`  (
  `demand_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '需求id',
  `project_id` int(11) UNSIGNED NOT NULL COMMENT '项目id，需求属于哪个项目',
  `demand_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '需求内容',
  `create_time` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0-正处理，1-否定，2-肯定，3-待删除，-1-删除',
  `demand_content_file` longblob NULL,
  PRIMARY KEY (`demand_id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for interface_document
-- ----------------------------
DROP TABLE IF EXISTS `interface_document`;
CREATE TABLE `interface_document`  (
  `interface_id` int(11) NOT NULL COMMENT '接口文档id',
  `interface_document` blob NOT NULL COMMENT '接口文档',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口查看url',
  `update_time` datetime NOT NULL,
  `project_id` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`interface_id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE,
  CONSTRAINT `interface_document_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project1` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `manager_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id，也是用户账号',
  `manager_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登陆密码',
  `email` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工手机号，开发人员手机号',
  `create_by` int(11) UNSIGNED NOT NULL COMMENT '账号创建者，对应系统管理员中的一个id',
  `create_time` datetime NOT NULL COMMENT '账号创建时间',
  `destory_time` datetime NULL DEFAULT NULL COMMENT '账号销毁时间',
  `expire` smallint(1) NOT NULL DEFAULT 1,
  `handling_project` int(10) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`manager_id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发人员账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息的ID',
  `message_type` int(4) UNSIGNED NOT NULL COMMENT '消息类型（1-验证码）',
  `message_platform` int(2) UNSIGNED NOT NULL COMMENT '消息发送平台（1-手机号，2-邮箱）',
  `message_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `send_time` datetime NOT NULL COMMENT '消息发送时间',
  `finish_time` datetime NOT NULL COMMENT '消息无效时间',
  `valid_time_interval` int(10) UNSIGNED NOT NULL COMMENT '消息有效的时间间隔，以秒为单位',
  `sender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息发送者',
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息接收者',
  `time_unit` int(11) NULL DEFAULT NULL COMMENT '消息有效时间的时间间隔单位',
  `expire` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '消息是否有效，1-有效，0-无效',
  `role` int(11) NOT NULL,
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `project_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
  `buyer_id` int(11) UNSIGNED NOT NULL COMMENT '购买方id',
  `create_time` date NOT NULL COMMENT '项目开始时间（一般就是项目创建时间）',
  `finish_time` date NULL DEFAULT NULL COMMENT '项目完成时间',
  `developer_manager_id` int(11) UNSIGNED NOT NULL COMMENT '项目对应的项目经理id',
  `see_status` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '项目是否最近被查看',
  PRIMARY KEY (`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project1
-- ----------------------------
DROP TABLE IF EXISTS `project1`;
CREATE TABLE `project1`  (
  `project_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
  `buyer_id` int(11) UNSIGNED NOT NULL COMMENT '购买方id',
  `create_time` date NOT NULL COMMENT '项目开始时间（一般就是项目创建时间）',
  `finsh_time` date NULL DEFAULT NULL COMMENT '项目完成时间',
  `developer_manager_id` int(11) UNSIGNED NOT NULL COMMENT '项目对应的项目经理id',
  PRIMARY KEY (`project_id`) USING BTREE,
  INDEX `buyer_id`(`buyer_id`) USING BTREE,
  INDEX `user_id`(`developer_manager_id`) USING BTREE,
  CONSTRAINT `project1_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `buyer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project1_ibfk_2` FOREIGN KEY (`developer_manager_id`) REFERENCES `manager` (`manager_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_developer_relation
-- ----------------------------
DROP TABLE IF EXISTS `project_developer_relation`;
CREATE TABLE `project_developer_relation`  (
  `project_id` int(11) UNSIGNED NOT NULL COMMENT '项目id',
  `developer_id` int(11) UNSIGNED NOT NULL COMMENT '开发人员id',
  `application_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请表id',
  PRIMARY KEY (`project_id`, `developer_id`) USING BTREE,
  INDEX `developer_id`(`developer_id`) USING BTREE,
  CONSTRAINT `project_developer_relation_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project1` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_developer_relation_ibfk_2` FOREIGN KEY (`developer_id`) REFERENCES `manager` (`manager_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目与开发人员关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stage
-- ----------------------------
DROP TABLE IF EXISTS `stage`;
CREATE TABLE `stage`  (
  `stage_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `stage_name` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_id` int(11) UNSIGNED NOT NULL COMMENT '划分阶段的项目id',
  `start_time` date NOT NULL COMMENT '开始时间',
  `cost_time` date NOT NULL COMMENT '花费时间',
  `predict_cost` decimal(12, 2) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`stage_id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `stage_id` int(48) UNSIGNED NOT NULL COMMENT '任务所属的开发阶段id',
  `task_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务内容',
  `project_id` int(11) UNSIGNED NOT NULL COMMENT '任务所属项目的id',
  `status` int(11) NOT NULL COMMENT '任务情况，0-未分配，1-未完成，2-完成',
  `cost` decimal(10, 2) NOT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL,
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `stage_id`(`stage_id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_task_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_task_relation`;
CREATE TABLE `user_task_relation`  (
  `task_id` int(11) UNSIGNED NOT NULL,
  `developer_id` int(11) UNSIGNED NOT NULL,
  `status` int(11) NOT NULL COMMENT '开发人员的任务完成情况，0-未完成，1-完成',
  PRIMARY KEY (`task_id`, `developer_id`) USING BTREE,
  INDEX `user_id`(`developer_id`) USING BTREE,
  CONSTRAINT `user_task_relation_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_task_relation_ibfk_2` FOREIGN KEY (`developer_id`) REFERENCES `manager` (`manager_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
