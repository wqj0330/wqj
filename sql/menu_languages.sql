CREATE TABLE `menu_languages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `lang_name` varchar(255) NOT NULL COMMENT 'language name',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_menu_languages_1` (`lang_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `menu_languages` VALUES ('1', 'en_US', null, sysdate(), null, null, null);
INSERT INTO `menu_languages` VALUES ('2', 'zh_CN', null, sysdate(), null, null, null);
