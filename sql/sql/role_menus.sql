CREATE TABLE `role_menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `role_id` bigint(20) NOT NULL COMMENT 'role id',
  `menu_id` bigint(20) NOT NULL COMMENT 'menu id',
  `has_right` varchar(2) COMMENT '是否有权限,Y/N',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role_menus` VALUES
  ('1', '1', '1', 'Y', null, sysdate(), null, null, null)
, ('2', '1', '2', 'Y', null, sysdate(), null, null, null)
, ('3', '1', '3', 'Y', null, sysdate(), null, null, null)
, ('4', '1', '4', 'Y', null, sysdate(), null, null, null)
, ('5', '1', '5', 'Y', null, sysdate(), null, null, null)
, ('6', '1', '6', 'Y', null, sysdate(), null, null, null)
, ('7', '1', '7', 'Y', null, sysdate(), null, null, null)
, ('8', '1', '8', 'Y', null, sysdate(), null, null, null)
, ('9', '1', '9', 'Y', null, sysdate(), null, null, null)
, ('10', '1', '10', 'Y', null, sysdate(), null, null, null)
, ('11', '1', '11', 'Y', null, sysdate(), null, null, null)
, ('12', '1', '12', 'Y', null, sysdate(), null, null, null)
, ('13', '1', '13', 'Y', null, sysdate(), null, null, null)
, ('14', '1', '14', 'Y', null, sysdate(), null, null, null)
, ('15', '1', '15', 'Y', null, sysdate(), null, null, null)
, ('16', '1', '16', 'Y', null, sysdate(), null, null, null)
, ('17', '1', '17', 'Y', null, sysdate(), null, null, null)
, ('18', '1', '18', 'Y', null, sysdate(), null, null, null)
, ('19', '1', '19', 'Y', null, sysdate(), null, null, null)
, ('20', '1', '20', 'Y', null, sysdate(), null, null, null)
, ('21', '1', '21', 'Y', null, sysdate(), null, null, null)
, ('22', '1', '22', 'Y', null, sysdate(), null, null, null)
;
