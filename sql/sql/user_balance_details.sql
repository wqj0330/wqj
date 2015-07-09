CREATE TABLE user_balance_details (

`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',

`user_id` bigint(20) COMMENT '用户ID',

`email` varchar(200) NOT NULL COMMENT '用户Email',

`trade_no` varchar(200) NOT NULL COMMENT '交易号',

`detail_balance` float(20,2) NOT NULL COMMENT '充值金额',

`balance_type` varchar(20) NOT NULL COMMENT '充值类型 0:系统赠送,1:支付宝,2:paypal,3:商店所得,其他:网银',

`balance_date` datetime NOT NULL COMMENT '充值日期',

`balance_status` varchar(20) NOT NULL COMMENT '充值状态 0:充值中,1:充值成功',

`remark` varchar(1000) COMMENT '备注',

`created_at` datetime COMMENT '创建日期',

`created_by` varchar(100) COMMENT '创建者',

`updated_at` datetime COMMENT '更新日期',

`updated_by` varchar(100) COMMENT '更新者',

PRIMARY KEY (`id`),

UNIQUE KEY ix_user_balance_details_1 (`trade_no`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;