CREATE TABLE `monit_data_detail` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
`os_id` bigint(20) NOT NULL COMMENT 'os ID',
`type_id` bigint(20) NOT NULL COMMENT '类别ID',
`job_id` bigint(20) NOT NULL COMMENT 'job ID',
`category_id` bigint(20) NOT NULL COMMENT 'category ID',
`host_id` bigint(20) NOT NULL COMMENT '机器Id',
`data_value` varchar(255) NOT NULL COMMENT 'value',
`is_latest` varchar(2) NOT NULL COMMENT '是否最新数据,0:是,1:否',
`remark` varchar(1000) NULL COMMENT '备注',
`created_at` timestamp NULL DEFAULT NULL COMMENT '创建日期',
`updated_at` timestamp NULL DEFAULT NULL COMMENT '更新日期',
 PRIMARY KEY (`id`),
 INDEX `i_monit_data_detail_1` (`is_latest`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;