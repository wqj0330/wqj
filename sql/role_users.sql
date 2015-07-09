CREATE TABLE `role_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `role_id` bigint(20) NOT NULL COMMENT 'role id',
  `user_id` bigint(20) NOT NULL COMMENT 'user id',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role_users` VALUES 
('1', '1', '1', null, sysdate(), null, null, null);