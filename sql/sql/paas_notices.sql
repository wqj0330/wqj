CREATE TABLE `paas_notices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `content_cn` varchar(10000)  COMMENT '中文公告',
  `content_en` varchar(10000) COMMENT '英文公告',
  `language` varchar(20) COMMENT '语言',
  `remark` varchar(1000) COMMENT '备注',
  `notice_show` varchar(2) COMMENT '公告展示',
  `created_at` datetime COMMENT '创建日期',
  `updated_at` datetime COMMENT '更新日期',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;