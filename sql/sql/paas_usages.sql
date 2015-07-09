CREATE TABLE `paas_usages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `user_email` varchar(255) NOT NULL COMMENT 'user email',
  `service_name` varchar(255) COMMENT 'service 名称',
  `service_plan` varchar(255) COMMENT 'service plan 名称',
  `quantity` float(20,2) COMMENT 'quantity',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
 PRIMARY KEY (`id`),
 INDEX `i_paas_usages_1` (`user_email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;