CREATE TABLE `system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `config_name` varchar(100) NOT NULL COMMENT '配置名称',
  `config_value` varchar(10000) NOT NULL COMMENT '配置值',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL '更新日期',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_system_config_1` (`config_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	INSERT INTO `system_config` (`create_time`, `config_name`, `config_value`, `remark`) VALUES 
	 (sysdate(), 'collectFrequency', '5', 'minute'),
	 (sysdate(), 'page_size', '20', NULL),
	 (sysdate(), 'inpaas_mail_sender_stmp_server_is_ssl', '0', '0:yes,1:no'),
	 (sysdate(), 'inpaas_mail_sender_stmp_server_ssl_port', '465', 'ssl port'),
	 (sysdate(), 'inpaas_mail_sender_stmp_server', 'smtp.exmail.qq.com', NULL),
	 (sysdate(), 'inpaas_mail_sender_username', 'no-reply@mopaas.com', NULL),
	 (sysdate(), 'inpaas_mail_sender_password', 'IFe6zOIbqNfLAA1/B1DUgA==', NULL),
	 (sysdate(), 'mem_all', '48', 'G,Allocatable mem'),
	 (sysdate(), 'paas_admin', 'admin', 'admin user'),
	 (sysdate(), 'paas_admin_pwd', 'glOSZgi38HFzPaXsZuxGFQ==', 'admin encryted password'),
	 (sysdate(), 'bosh_cli_ip', '10.0.0.241', 'bosh cli ip'),
	 (sysdate(), 'bosh_cli_user', 'root', 'bosh cli user'),
	 (sysdate(), 'bosh_cli_pwd', 'QrCU+hCUdHI=', 'encryted pwd'),
	 (sysdate(), 'bosh_cli_ruby_ver', 'ruby-1.9.3-p484', 'ruby version'),
	 (sysdate(), 'vms_data_days', '30', 'Datas days in vms_data'),
	 (sysdate(), 'timezone', 'Asia/Shanghai', 'user timezone:Asia/Shanghai'),
	 (sysdate(), 'log_lines', '200', 'Log default lines'),
	 (sysdate(), 'host_user', 'root', 'Host user for log'),
	 (sysdate(), 'host_pwd', '4ZQV4Y/YQuC/IYq0z2FwSg==', 'Host password for log'),
	 (sysdate(), 'host_ssh_port', '22', 'Host port for log'),
	 (sysdate(), 'host_log_path', '/var/vcap/sys/log', 'Host log path'),
	 (sysdate(), 'host_log_pair', 'HA:haproxy/startup_stdout.log;NATS:nats/nats.log;dea:dea_next/dea_next.log;dea:dea_next/dea_next.log;cc:cloud_controller_ng/cloud_controller_ng.stderr.log,cloud_controller_ng/cloud_controller_ng.log;uaa:uaa/uaa.log;hm:hm9000/hm9000_sender.stdout.log,hm9000/hm9000_analyzer.stdout.log;loggregator:loggregator/loggregator.stdout.log;gorouter:gorouter/gorouter.log', 'Host log pair'),
	 (sysdate(), 'app_autoscaling_monit_time', '30', 'app autoscaling monit time'),
	 (sysdate(), 'dea_autoscaling_monit_time', '30', 'dea autoscaling monit time'),
	 (sysdate(), 'is_dea_autoscaling', '0', '0:No,1:Yes'),
	 (sysdate(), 'dea_autoscaling_trigger_type', '1', '0:cpu,1:mem,2:auto'),
	 (sysdate(), 'dea_autoscaling_trigger_up_value', '90', '90%'),
	 (sysdate(), 'dea_autoscaling_trigger_down_value', '30', '30%'),
	 (sysdate(), 'dea_autoscaling_template', 'xxxxxx', 'dea autoscaling template'),
	 (sysdate(), 'iaas_type', '0', '0:vsphere,1:openstack,2:aws,3:cloudstack'),
	 (sysdate(), 'iaas_connect_parameters', 'xxxx', 'iaas connect parameters'),
	 (sysdate(), 'is_sendcloud', 'Y', 'Y:sendcloud,N:mail_send'),
	 (sysdate(), 'send_cloud_url', 'https://sendcloud.sohu.com/webapi/mail.send.xml', 'mail sender url'),
	 (sysdate(), 'send_cloud_api_user', 'postmaster@mopaas.sendcloud.org', 'mail sender user'),
	 (sysdate(), 'send_cloud_api_key', 'teRhW+RGsXmv6Lmx1Fn3FXM9pexm7EYV', 'mail sender key')
	 
;

INSERT INTO `system_config`(`config_name`, `config_value`, `remark`, `create_time`) VALUES 
('ACCESS_KEY', 'OEUAaFUR1nMOOowgg61o6AJXbjppTBPRul9IF6+Pam5xOwexA8/o73M9pexm7EYV', 'qiniu ak', sysdate()),
('SECRET_KEY', 'WsS59CElT81taTf6XZH1sgrjf+0wdtrUrTwyMdUk0DxflsGO1xgrUHM9pexm7EYV', 'qiniu pk', sysdate()),
('IS_NFS', 'N', 'n:qiniu', sysdate()),
('bucket_user_infos', 'user-infos', 'user info bucket', sysdate()),
('bucket_user_infos_url', '7xiul8.com1.z0.glb.clouddn.com', 'user info url', sysdate()),
('bucket_user_icps', 'user-icps', 'user icps bucket', sysdate()),
('bucket_user_icps_url', '7xixrb.com1.z0.glb.clouddn.com', 'user icps url', sysdate());

INSERT INTO `system_config`(`config_name`, `config_value`, `remark`, `create_time`) VALUES 
('send_msg', '1', 'is send msg, 0:no, 1:yes', sysdate()),
('send_msg_url', 'http://api.sms.cn/mt/', 'api url', sysdate()),
('send_msg_user', 'mopaas', 'api user', sysdate()),
('send_msg_pwd', 'QrCU+hCUdHI=', 'api pwd', sysdate());

INSERT INTO `system_config`(`config_name`, `config_value`, `remark`, `create_time`) VALUES 
('msg_confirm_code', '欢迎使用魔泊云。您的验证码：##code##，如非本人操作，请忽略本短信。【MoPaaS】', '验证码短信', sysdate());

INSERT INTO `system_config`(`config_name`, `config_value`, `remark`, `create_time`) VALUES 
('send_msg_max', '5', 'max times to send msg in a day', sysdate());

INSERT INTO `system_config`(`config_name`, `config_value`, `remark`, `create_time`) VALUES 
('msg_app_error', '您的应用##app##被识别到存在一些问题，为了避免对您的业务产生的影响，请立即登录MoPaaS查看。【MoPaaS】', 'app error msg', sysdate()),
('msg_app_overload', '您的应用##app##可用内存内存已不足##app_mem_leave##%，请及时登陆MoPaaS进行扩容。【MoPaaS】', 'app error msg', sysdate()),
('msg_app_overload_leave', '5', '5%', sysdate());

INSERT INTO `system_config` (`create_time`, `config_name`, `config_value`, `remark`) VALUES 
(sysdate(), 'cs-zone-id', 'c56ea6d8-4d4d-42f3-9ea4-a931852349a3', 'zone id'),
(sysdate(), 'cs-user-id', 'uid', 'user id'),
(sysdate(), 'cs-project-id', 'pid', 'project id'),
(sysdate(), 'cs-template-id', '9940ee77-7d80-4fdd-8902-d9ff2dee96df', 'template id'),
(sysdate(), 'cs-template-user', 'root', 'template user'),
(sysdate(), 'cs-template-pwd', 'IuL+Kd53FMg=', 'template pwd'),
(sysdate(), 'cs-vm-network-id', '1ee5e1b8-6a91-4953-9334-d937ba1b7aed', 'network id'),
(sysdate(), 'cs-vm-hypervisor', 'VMware', 'Hypervisor'),
(sysdate(), 'cs-vm-level', 'vm_level_a', 'vm level'),
(sysdate(), 'cs-vm-cpu', '2', 'vm cpu num'),
(sysdate(), 'cs-vm-memory', '2048', 'vm memory num'),
(sysdate(), 'cs-vm-ha', 'false', 'vm ha enable'),
(sysdate(), 'cs-vm-purpose', 'purpose_application', 'vm purpose'),
(sysdate(), 'cs-create-vm-uri', 'resource/vm', 'cs create vm uri'),
(sysdate(), 'cs-job-detail-uri', 'resource/job/', 'cs job detail'),
(sysdate(), 'cs-destory-vm-uri', 'resource/vm/destroyVirtualMachine', 'cs destory vm uri');