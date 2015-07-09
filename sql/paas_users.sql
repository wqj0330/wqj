/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.12
Source Server Version : 50524
Source Host           : 192.168.1.12:3306
Source Database       : mopaasv2

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2014-11-20 09:37:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `paas_users`
-- ----------------------------
DROP TABLE IF EXISTS `paas_users`;
CREATE TABLE `paas_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(255) NOT NULL COMMENT '密码',
  `user_cf_pwd` varchar(255) NOT NULL COMMENT '平台密码',
  `user_platform` varchar(255) DEFAULT NULL COMMENT '平台地址',
  `quota_id` bigint(20) DEFAULT NULL COMMENT '套餐ID',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '上次登录IP',
  `current_login_ip` varchar(100) DEFAULT NULL COMMENT '当前登录IP',
  `user_phone` varchar(100) DEFAULT NULL,
  `phone_identified` varchar(10) DEFAULT '0' COMMENT '0,f表示未手机认证,1表示审核中,2表示成功',
  `user_identified` varchar(10) DEFAULT '0' COMMENT '0,f表示未实名认证,1表示审核中,2表示成功',
  `user_active` varchar(10) DEFAULT 'f' COMMENT '用户是否激活',
  `user_from` varchar(100) DEFAULT NULL COMMENT '用户来源',
  `user_relevant` varchar(100) DEFAULT NULL COMMENT '推荐人',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `accesskey` varchar(255) DEFAULT NULL,
  `secretkey` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email` (`user_email`),
  UNIQUE KEY `user` (`user_email`,`accesskey`,`secretkey`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paas_users
-- ----------------------------
INSERT INTO `paas_users` VALUES ('11', '3081657571@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '/S0SFl0a2o0=', null, null, null, null, null, null, 't', null, null, null, null, '2014-11-18 15:52:00', '2014-11-18 15:52:00');
INSERT INTO `paas_users` VALUES ('12', 'yfshen', 'e10adc3949ba59abbe56e057f20f883e', '', null, null, null, null, null, 'f', 't', null, null, null, null, null, null);
INSERT INTO `paas_users` VALUES ('14', 'bb', 'e10adc3949ba59abbe56e057f20f883e', '', null, null, null, null, null, 'f', 't', null, null, null, null, null, null);

-- MoPaaS v2 Enterprise added
ALTER TABLE `paas_users`
ADD COLUMN `user_type`  varchar(10) NULL COMMENT '用户类型0:个人,1:企业' AFTER `user_relevant`,
ADD COLUMN `parent_id`  bigint(20) NULL COMMENT '父用户ID' AFTER `user_type`,
ADD COLUMN `contacts_name`  varchar(255) NULL COMMENT '联系人姓名' AFTER `parent_id`,
ADD COLUMN `contacts_id_no`  varchar(255) NULL COMMENT '联系人身份证号码' AFTER `contacts_name`,
ADD COLUMN `contacts_id_img1`  varchar(1000) NULL COMMENT '联系人身份证持证照正面' AFTER `contacts_id_no`,
ADD COLUMN `contacts_id_img2`  varchar(1000) NULL COMMENT '联系人身份证持证照反面' AFTER `contacts_id_img1`,
ADD COLUMN `contacts_addr`  varchar(2000) NULL COMMENT '联系人地址' AFTER `contacts_id_img2`,
ADD COLUMN `company_name`  varchar(255) NULL COMMENT '企业名称' AFTER `contacts_addr`,
ADD COLUMN `company_bussiness_license`  varchar(255) NULL COMMENT '营业执照注册号(15位)' AFTER `company_name`,
ADD COLUMN `company_bussiness_license_img`  varchar(1000) NULL COMMENT '营业执照副本扫描件' AFTER `company_bussiness_license`,
ADD COLUMN `company_org_code`  varchar(255) NULL COMMENT '组织机构代码(15位)' AFTER `company_bussiness_license_img`;

