/*
 Navicat Premium Data Transfer

 Source Server         : fxyp-21.5
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.21.5:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 02/03/2019 13:25:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                           `dept_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
                           `pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'parent-id',
                           `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
                           `flag` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '1-有效；0-无效;',
                           `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                           `modified` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
                           PRIMARY KEY (`dept_id`) USING BTREE,
                           INDEX `idx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_rel`;
CREATE TABLE `sys_dept_rel`  (
                               `dept_id` int(10) UNSIGNED NOT NULL COMMENT 'sys_dept.id',
                               `saas_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'saas.user.id; wk_t_user.ku_id; 一个机构对应一个saas用户',
                               `sso_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应单点登录系统相关Id',
                               `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                               `modified` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
                               PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门关联关系表；存放与saas用户对应关系；与单点登录系统对应关系；' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module`  (
                             `module_id` int(11) NOT NULL AUTO_INCREMENT,
                             `pid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'parent-id',
                             `module_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能点名称',
                             `module_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
                             `flag` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '1-有效；',
                             `order_no` smallint(5) UNSIGNED NULL DEFAULT NULL COMMENT '(同级)顺序号',
                             PRIMARY KEY (`module_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '(约定的)功能模块(预留)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                           `role_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
                           `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                           `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
                           `flag` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '1-有效；',
                           `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                           PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module`  (
                                  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
                                  `role_id` int(10) UNSIGNED NOT NULL COMMENT 'sys_role.role_id',
                                  `module_id` int(10) UNSIGNED NOT NULL COMMENT '模块id，未来应该为sys_module.module_id',
                                  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                           `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
                           `login_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名, ',
                           `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
                           `dept_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT ' sys_dept.dept_id',
                           `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称, sys_dept.dept_name',
                           `flag` tinyint(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '1-有效；0-无效;',
                           `sso_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应单点登录系统相关Id',
                           `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                           `modified` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
                           PRIMARY KEY (`user_id`) USING BTREE,
                           INDEX `uk_login_id`(`login_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
                                `user_id` int(10) UNSIGNED NOT NULL COMMENT 'sys_user.user_id',
                                `role_id` int(10) UNSIGNED NOT NULL COMMENT 'sys_role.role_id',
                                `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- module init
insert into sys_module(pid, module_name, module_url, flag, order_no)
values (0, '舆情监测', null, 1, 1), (0, '风险预警', null, 1, 1), (0, '舆情追踪', null, 1, 1), (0, '态势感知', null, 1, 1), (0, '风险统计分析', null, 1, 1), (0, '舆情专报', null, 1, 1), (0, '系统设置', null, 1, 1);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (1, '信息列表', null, 1, 1), (1, '统计分析', null, 1, 2);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (2, '信息列表', null, 1, 1);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (10, '支持预警分发功能', null, 1, 1);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (3, '事件追踪', null, 1, 1), (1, '往期事件', null, 1, 2);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (4, '态势分析', null, 1, 1);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (5, '风险统计分析', null, 1, 1);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (6, '简报列表', null, 1, 1), (6, '数据列表', null, 1, 2), (6, '内容分类', null, 1, 3), (1, '简报模板', null, 1, 4);

insert into sys_module(pid, module_name, module_url, flag, order_no)
values (7, '用户管理', null, 1, 1), (7, '角色管理', null, 1, 2), (7, '专题设置', null, 1, 3), (7, '预警规则设置', null, 1, 4), (7, '信息过滤管理', null, 1, 5);

commit ;


select * from sys_module;
