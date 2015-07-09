CREATE TABLE `monit_host` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
`host_ip` varchar(100) NOT NULL COMMENT '机器IP',
`host_name` varchar(100) NULL COMMENT '机器名',
`host_desc` varchar(100) NULL COMMENT '主机描述',
`remark` varchar(1000) NULL COMMENT '备注',
`created_at` timestamp NULL DEFAULT NULL COMMENT '创建日期',
`updated_at` timestamp NULL DEFAULT NULL COMMENT '更新日期',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;