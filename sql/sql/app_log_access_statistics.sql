CREATE TABLE `app_log_access_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `app_guid` varchar(255) DEFAULT NULL COMMENT '应用ID',
  `flag` smallint(6) DEFAULT NULL COMMENT '区分:1,分钟;2,小时;3,天;21,流量/分钟;22，流量/小时;23,流量/天',
  `log_date` datetime NOT NULL COMMENT '日志时间',
  `log_count` bigint(20) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_app_log_access_statistics_1` (`app_guid`,`flag`,`log_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;