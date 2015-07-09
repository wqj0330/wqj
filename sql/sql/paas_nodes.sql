CREATE TABLE `paas_nodes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `node_name` varchar(255) NOT NULL COMMENT 'node 名称',
  `node_vm_id` varchar(255) COMMENT 'node vm id',
  `node_vm_job_id` varchar(255) COMMENT 'node vm job id',
  `node_vm_ip` varchar(255) COMMENT 'node vm ip',
  `node_vm_template_id` varchar(255) COMMENT 'node vm template id',
  `node_vm_user` varchar(255) COMMENT 'node vm user',
  `node_vm_pwd` varchar(255) COMMENT 'node vm pwd',
  `node_vm_status` varchar(2) COMMENT 'node vm status, 0:creating vm, 1:create vm error, 2:created vm, installing cloud foundry, 3: install cloud foundry error, 4: installed cloud foundry, 5: deleting node, 6: delete node error, 7: deleted node',
  `node_msg` varchar(1000) COMMENT 'message',
  `remark` varchar(1000) COMMENT '备注',
  `created_at` datetime COMMENT '创建日期',
  `created_by` varchar(100) COMMENT '创建者',
  `updated_at` datetime COMMENT '更新日期',
  `updated_by` varchar(100) COMMENT '更新者',
 PRIMARY KEY (`id`),
 UNIQUE KEY `i_paas_nodes_1` (`node_vm_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;