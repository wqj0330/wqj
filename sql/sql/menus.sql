CREATE TABLE `menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `menu_name` varchar(255) NOT NULL COMMENT 'menu name',
  `parent_menu_id` bigint(20) COMMENT '父菜单编号ID',
  `menu_order` bigint(20) COMMENT '顺序号',
  `style_class` varchar(255) COMMENT '菜单样式',
  `menu_item_id` varchar(255) COMMENT '菜单id，用于js触发',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_menus_1` (`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `menus` VALUES ('1', 'overview', null, '1', 'icon-roof', 'tab_overview', null, sysdate(), null, null, null)
, ('2', 'management', null, '2', 'icon-manage', null, null, sysdate(), null, null, null)
, ('3', 'settings', null, '3', 'icon-system', null, null, sysdate(), null, null, null)
, ('4', 'monitoring', '2', '1', null, 'tab_host_monit', null, sysdate(), null, null, null)
, ('5', 'vms', '2', '2', null, 'tab_job_monit', null, sysdate(), null, null, null)
, ('6', 'quotas', '2', '3', null, 'tab_quota', null, sysdate(), null, null, null)
, ('7', 'orgs', '2', '4', null, 'tab_org', null, sysdate(), null, null, null)
, ('8', 'spaces', '2', '5', null, 'tab_space', null, sysdate(), null, null, null)
, ('9', 'users', '2', '6', null, 'tab_uaausers', null, sysdate(), null, null, null)
, ('10', 'apps', '2', '7', null, 'tab_app', null, sysdate(), null, null, null)
, ('11', 'domains', '2', '8', null, 'tab_domain', null, sysdate(), null, null, null)
, ('12', 'services', '2', '9', null, 'tab_service', null, sysdate(), null, null, null)
, ('13', 'servicebound', '2', '10', null, 'tab_service_binding', null, sysdate(), null, null, null)
, ('14', 'config', '3', '1', null, 'tab_config', null, sysdate(), null, null, null)
, ('15', 'administrators', '3', '2', null, 'tab_login', null, sysdate(), null, null, null)
, ('16', 'invitecodes', '3', '3', null, 'tab_invitecode', null, sysdate(), null, null, null)

, ('17', 'menus', '3', '4', null, 'tab_menus', null, sysdate(), null, null, null)
, ('18', 'menudetails', '3', '5', null, 'tab_menudetails', null, sysdate(), null, null, null)
, ('19', 'roles', '3', '6', null, 'tab_roles', null, sysdate(), null, null, null)
, ('20', 'roleusers', '3', '7', null, 'tab_roleusers', null, sysdate(), null, null, null)
, ('21', 'rolemenus', '3', '8', null, 'tab_rolemenus', null, sysdate(), null, null, null)
, ('22', 'usermenus', '3', '9', null, 'tab_usermenus', null, sysdate(), null, null, null)
;