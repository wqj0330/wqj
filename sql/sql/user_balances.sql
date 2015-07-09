CREATE TABLE `user_balances` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `email` varchar(200) NOT NULL COMMENT '用户Email',
  `balance` float(20,2) NOT NULL COMMENT '余额',
  `balance_type` varchar(20) NOT NULL COMMENT '类型 0:系统赠送,1:用户充值,2:商店所得',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建日期',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `updated_at` datetime DEFAULT NULL COMMENT '更新日期',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_user_balances_1` (`email`,`balance_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;