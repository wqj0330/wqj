CREATE TABLE `vms_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `job_name` varchar(255) NOT NULL COMMENT 'VM任务名',
  `job_index` bigint(20) NOT NULL COMMENT 'VM任务序号',
  `state` varchar(255) NOT NULL COMMENT '状态',
  `resource_pool` varchar(255) NOT NULL COMMENT '资源池',
  `ips` varchar(255) NOT NULL COMMENT 'ip地址',
  `load_avg01` float(20,2) NOT NULL COMMENT '1分钟平均负载',
  `load_avg05` float(20,2) NOT NULL COMMENT '4分钟平均负载',
  `load_avg15` float(20,2) NOT NULL COMMENT '15分钟平均负载',
  `cpu_user` float(20,1) NOT NULL COMMENT '用户CPU',
  `cpu_sys` float(20,1) NOT NULL COMMENT '系统CPU',
  `cpu_wait` float(20,1) NOT NULL COMMENT '等待CPU',
  `memory_usage_per` float(20,1) NOT NULL COMMENT '内存使用百分比',
  `memory_usage_num` varchar(255) NOT NULL COMMENT '内存使用量',
  `swap_usage_per` float(20,1) NOT NULL COMMENT '交换区使用百分比',
  `swap_usage_num` varchar(255) NOT NULL COMMENT '交换区使用量',
  `system_disk_usage` float(20,1) NOT NULL COMMENT '系统盘用量',
  `ephemeral_disk_usage` float(20,1) NOT NULL COMMENT '挂载盘1用量',
  `persistent_disk_usage` float(20,1) DEFAULT NULL COMMENT '挂载盘2用量',
  `data_time` datetime COMMENT '采集时间',
  `is_latest` varchar(2) NOT NULL COMMENT '是否最新数据,0:是,1:否',
  PRIMARY KEY (`id`),
  KEY `i_vms_data_1` (`is_latest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
