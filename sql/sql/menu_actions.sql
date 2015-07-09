CREATE TABLE `menu_actions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `menu_id` bigint(20) NOT NULL COMMENT 'menu id',
  `menu_action` varchar(255) COMMENT 'menu action',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_menu_actions_1` (`menu_id`,`menu_action`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
