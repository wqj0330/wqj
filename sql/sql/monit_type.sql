CREATE TABLE `monit_type` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
`type_name` varchar(255) NOT NULL COMMENT '类别名称',
`remark` varchar(1000) NULL COMMENT '备注',
`created_at` timestamp NULL DEFAULT NULL COMMENT '创建日期',
`updated_at` timestamp NULL DEFAULT NULL COMMENT '更新日期',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;