CREATE TABLE `monit_category` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
`type_id` bigint(20) NOT NULL COMMENT '类别ID',
`category_name` varchar(255) NOT NULL COMMENT 'category名称',
`category_warning_value` varchar(255) NULL COMMENT '警告阀值',
`remark` varchar(1000) NULL COMMENT '备注',
`created_at` timestamp NULL DEFAULT NULL COMMENT '创建日期',
`updated_at` timestamp NULL DEFAULT NULL COMMENT '更新日期',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;