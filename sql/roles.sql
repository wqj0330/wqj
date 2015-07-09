CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `role_name` varchar(255) NOT NULL COMMENT 'role name',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_roles_1` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `roles` VALUES ('1', 'admin', null, sysdate(), null, null, null);