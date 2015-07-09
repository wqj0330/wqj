CREATE TABLE `app_warning_smses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `app_name` varchar(255) NOT NULL COMMENT '应用名称',
  `user_email` varchar(100) NOT NULL COMMENT '用户邮箱',
  `user_phone` varchar(100) NOT NULL COMMENT '用户手机',
  `sms_content` varchar(1000) NOT NULL COMMENT '短信内容',
  `sms_state` varchar(20) NOT NULL COMMENT '短信状态',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_app_warning_smses_1` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
