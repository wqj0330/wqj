CREATE TABLE `login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `user_name` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(1000) NOT NULL COMMENT '密码（md5）',
  `level` varchar(2) DEFAULT NULL COMMENT '级别',
  `locale`  varchar(200) DEFAULT NULL COMMENT '语言',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间',
  `current_login` datetime DEFAULT NULL COMMENT '当前登录时间',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `timezone` varchar(30) DEFAULT NULL COMMENT '时区',
  `current_login_ip` varchar(20) DEFAULT NULL COMMENT '当前IP',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_login_1` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `login` (`id`, `user_name`, `password`, `level`, `update_at`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', sysdate());
