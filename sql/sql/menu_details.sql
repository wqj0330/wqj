CREATE TABLE `menu_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `menu_id` bigint(20) NOT NULL COMMENT 'menu id',
  `lang_id` bigint(20) NOT NULL COMMENT 'language ID',
  `menu_value` varchar(1000) COMMENT '菜单显示名',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_menu_details_1` (`menu_id`, `lang_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `menu_details` VALUES ('1', '1', '1', 'Overview', null, sysdate(), null, null, null)
, ('2', '1', '2', '平台总览', null, sysdate(), null, null, null)
, ('3', '2', '1', 'Management', null, sysdate(), null, null, null)
, ('4', '2', '2', '平台管理', null, sysdate(), null, null, null)
, ('5', '3', '1', 'Settings', null, sysdate(), null, null, null)
, ('6', '3', '2', '设置', null, sysdate(), null, null, null)
, ('7', '4', '1', 'Hosts', null, sysdate(), null, null, null)
, ('8', '4', '2', '主机管理', null, sysdate(), null, null, null)
, ('9', '5', '1', 'Jobs', null, sysdate(), null, null, null)
, ('10', '5', '2', '任务管理', null, sysdate(), null, null, null)
, ('11', '6', '1', 'Quotas', null, sysdate(), null, null, null)
, ('12', '6', '2', '配额管理', null, sysdate(), null, null, null)
, ('13', '7', '1', 'Organizations', null, sysdate(), null, null, null)
, ('14', '7', '2', '组织管理', null, sysdate(), null, null, null)
, ('15', '8', '1', 'Spaces', null, sysdate(), null, null, null)
, ('16', '8', '2', '空间管理', null, sysdate(), null, null, null)
, ('17', '9', '1', 'Users', null, sysdate(), null, null, null)
, ('18', '9', '2', '用户管理', null, sysdate(), null, null, null)
, ('19', '10', '1', 'Apps', null, sysdate(), null, null, null)
, ('20', '10', '2', '应用管理', null, sysdate(), null, null, null)
, ('21', '11', '1', 'Domains', null, sysdate(), null, null, null)
, ('22', '11', '2', '域名管理', null, sysdate(), null, null, null)
, ('23', '12', '1', 'Services', null, sysdate(), null, null, null)
, ('24', '12', '2', '服务管理', null, sysdate(), null, null, null)
, ('25', '13', '1', 'Service Bound', null, sysdate(), null, null, null)
, ('26', '13', '2', '服务绑定管理', null, sysdate(), null, null, null)
, ('27', '14', '1', 'Configurations', null, sysdate(), null, null, null)
, ('28', '14', '2', '系统配置', null, sysdate(), null, null, null)
, ('29', '15', '1', 'Administrators', null, sysdate(), null, null, null)
, ('30', '15', '2', '操作员管理', null, sysdate(), null, null, null)
, ('31', '16', '1', 'Invite codes', null, sysdate(), null, null, null)
, ('32', '16', '2', '邀请码管理', null, sysdate(), null, null, null)

, ('33', '17', '1', 'Menus', null, sysdate(), null, null, null)
, ('34', '17', '2', '菜单管理', null, sysdate(), null, null, null)
, ('35', '18', '1', 'Menu details', null, sysdate(), null, null, null)
, ('36', '18', '2', '菜单详情管理', null, sysdate(), null, null, null)
, ('37', '19', '1', 'Roles', null, sysdate(), null, null, null)
, ('38', '19', '2', '角色管理', null, sysdate(), null, null, null)
, ('39', '20', '1', 'Role users', null, sysdate(), null, null, null)
, ('40', '20', '2', '角色用户管理', null, sysdate(), null, null, null)
, ('41', '21', '1', 'Role menus', null, sysdate(), null, null, null)
, ('42', '21', '2', '角色菜单管理', null, sysdate(), null, null, null)
, ('43', '22', '1', 'User menus', null, sysdate(), null, null, null)
, ('44', '22', '2', '用户菜单管理', null, sysdate(), null, null, null)
;