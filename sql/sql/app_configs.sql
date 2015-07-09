CREATE TABLE `app_configs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `app_guid` varchar(255) NOT NULL COMMENT 'app guid',
  `app_name` varchar(255) NOT NULL COMMENT 'app 名称',
  `is_autoscaling` varchar(255) NOT NULL COMMENT '是否伸缩,0:否,1:是',
  `autoscaling_type` varchar(255) NOT NULL COMMENT '自动伸缩类型,0:垂直,1:水平',
  `autoscaling_value` float(20,2) NOT NULL COMMENT '自动伸缩数量,256（256M）/1',
  `autoscaling_trigger_type` varchar(255) NOT NULL COMMENT '自动伸缩触发类型,0:cpu,1:mem,2:auto',
  `autoscaling_trigger_up_value` float(20,2) NOT NULL COMMENT '自动伸缩触发值,90',
  `autoscaling_trigger_down_value` float(20,2) NOT NULL COMMENT '自动伸缩触发值 ,30',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
 PRIMARY KEY (`id`),
 UNIQUE KEY `i_app_configs_1` (`app_guid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;