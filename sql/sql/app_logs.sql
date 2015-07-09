CREATE TABLE `app_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `app_url` varchar(255) NOT NULL COMMENT '访问地址',
  `access_time` datetime NOT NULL COMMENT '采集时间',
  `method` varchar(255) NOT NULL COMMENT '访问方式',
  `resource` varchar(4096) DEFAULT NULL COMMENT '访问资源',
  `return_code` varchar(255) DEFAULT NULL COMMENT '返回代码',
  `body_bytes_sent` bigint(20) DEFAULT NULL COMMENT '返回字节',
  `source_page` varchar(4096) DEFAULT NULL COMMENT '访问源',
  `client_info` varchar(4096) DEFAULT NULL COMMENT '客户端信息',
  `client_ip` varchar(255) DEFAULT NULL COMMENT '访问IP',
  `client_port` varchar(255) DEFAULT NULL COMMENT '访问端口',
  `vcap_request_id` varchar(255) DEFAULT NULL COMMENT 'Vcap请求ID',
  `response_time` float(20,6) DEFAULT NULL COMMENT 'response time',
  `app_guid` varchar(255) DEFAULT NULL COMMENT 'app guid',
  `protocol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
