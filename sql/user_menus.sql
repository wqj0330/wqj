CREATE TABLE `user_menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `user_id` bigint(20) NOT NULL COMMENT 'user id',
  `menu_id` bigint(20) NOT NULL COMMENT 'menu id',
  `has_right` varchar(2) COMMENT '是否有权限,Y/N',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
