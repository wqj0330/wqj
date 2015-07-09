/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.anchora.inpaasmgr.base.BaseService;
import me.anchora.inpaasmgr.dao.inpaasmgr.AppConfigsMapper;
import me.anchora.inpaasmgr.dao.inpaasmgr.AppMonitDatasMapper;
import me.anchora.inpaasmgr.dao.inpaasmgr.CfMonitDatasMapper;
import me.anchora.inpaasmgr.dao.inpaasmgr.SystemConfigMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.AppConfigs;
import me.anchora.inpaasmgr.entry.inpaasmgr.AppConfigsCriteria;
import me.anchora.inpaasmgr.entry.inpaasmgr.AppMonitDatas;
import me.anchora.inpaasmgr.entry.inpaasmgr.CfMonitDatas;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfig;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfigCriteria;
import me.anchora.inpaasmgr.service.VarzService;
import me.anchora.inpaasmgr.utils.ConfigUtil;
import me.anchora.inpaasmgr.utils.HttpClientUtils;
import me.anchora.inpaasmgr.utils.JsonUtil;
import me.anchora.inpaasmgr.utils.LogUtil;
import nats.client.Message;
import nats.client.MessageHandler;
import nats.client.Nats;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "varzService")
public class VarzServiceImpl extends BaseService implements VarzService {
    private static Logger logger = Logger.getLogger(VarzServiceImpl.class);

    @Autowired
    private Nats nats;

    @Autowired
    private AppConfigsMapper appConfigsMapper;

    private List<Map<String, Object>> componentList;

    @Autowired
    private AppMonitDatasMapper appMonitDatasMapper;

    @Autowired
    private CfMonitDatasMapper cfMonitDatasMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public void vcapComponentDiscover() {
        componentList = new ArrayList<Map<String, Object>>();
        //组件在启动时都会向component注册自己。那么这个注册就会启动一个http server。启动的代码在vcap common的component.rb中.
        nats.request("vcap.component.discover", "", 50L, TimeUnit.SECONDS, new MessageHandler() {
            @SuppressWarnings("unchecked")
            //nats.client.Message  从nats获取的消息？
            public void onMessage(Message message) {
            	//Received from NATS(vcap.component.discover),即从NATS接收消息
                logger.info("Received from NATS(vcap.component.discover): " + message);
                //getBody()检索此 Message 实例的正文
                String body = message.getBody();
                Map<String, Object> map = (Map<String, Object>) JsonUtil.getMapFromJson(body);
                componentList.add(map);
            }
        });
    }

    public List<Map<String, Object>> getComponentList() {
        if (componentList == null || componentList.size() == 0) {
            vcapComponentDiscover();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    LogUtil.exception(logger, e);
                }
                if (componentList != null && componentList.size() > 0) {
                    break;
                }
            }
        }

        return componentList;
    }

    @SuppressWarnings("unchecked")
    //varz状态监控
    public Map<String, Map<String, Object>> getVarzList(String node) {
        if (componentList == null || componentList.size() == 0) {
            vcapComponentDiscover();
            for (int i = 0; i < 5; i++) {
                try {
                	//这段代码的作用是使当前进程沉睡2S，展现给用户的结果就是画面维持两秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    LogUtil.exception(logger, e);
                }
                if (componentList != null && componentList.size() > 0) {
                    break;
                }
            }
        }

        String type = null;
        String uuid = null;
        String host = null;
        String user = null;
        String password = null;
        String varzDetail = null;

        Map<String, Map<String, Object>> varzMap = new HashMap<String, Map<String, Object>>();
        Map<String, Object> varzDetailMap;
        
        for (Map<String, Object> componentMap : componentList) {
            if (componentMap.containsKey("type")) {
                type = componentMap.get("type").toString();
            }
            if (componentMap.containsKey("uuid")) {
            	uuid = componentMap.get("uuid").toString();
            }
            //java.lang.String.contains() 方法返回true ，此方法返回true，如果此字符串包含，否则返回false。（即验证字符串“type”中是否包含字符串node）
            if (type != null && type.length() > 0 && uuid != null && uuid.length() > 0 && type.contains(node)) {
                if (componentMap.containsKey("host")) {
                    host = componentMap.get("host").toString();
                }
                if (componentMap.containsKey("credentials")) {
                    if (((List<String>) componentMap.get("credentials")).size() == 2) {
                        user = ((List<String>) componentMap.get("credentials")).get(0);
                        password = ((List<String>) componentMap.get("credentials")).get(1);
                    }
                }
                if (host != null && host.length() > 0 && user != null && user.length() > 0 && password != null && password.length() > 0) {
                    logger.info("Getting varz from:" + "http://" + user + ":" + password + "@" + host + "/varz");
                    try {
                    	//http访问且通过用户user和密码password验证，即可获取dea的许多信息，包括配置，framework、instances、以及VM（虚拟机）状态等。
						varzDetail = HttpClientUtils.getByHttp("http://" + user + ":" + password + "@" + host + "/varz");
					} catch (Exception e) {
						LogUtil.exception(logger, e);
					}
                    logger.info("varzDetail的值：！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
                    logger.info("Result from varz:" + varzDetail);
                    //把上述获取的varzDetail从Json形式转换为Map形式：getMapFromJson(varzDetail)
                    varzDetailMap = (Map<String, Object>) JsonUtil.getMapFromJson(varzDetail);
                    varzMap.put(type + "/" + uuid, varzDetailMap);
                }
            }
        }

        return varzMap;
    }

    @SuppressWarnings("unchecked")
    //CommonVo中guid
    public List<Map<String, Object>> getAppDetail(String guid) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        //getVarzList(String node)  node节点？
        //从Varz获取“DEA”的相关信息
        Map<String, Map<String, Object>> map = getVarzList("DEA");
        Map<String, Object> instanceMap;
        Map<String, Object> instancesMap;
        Map<String, Object> instanceDetailMap;
        Map<String, Object> resultMap;
        //map.keyset()获得Map中的key集合。
        //首先，set是一个集合，keyset（）返回的就是一个set集合比如map里面的键值对是这样的<1,one>,<2,two><3,three><4,four><5,five><6,six>那么keyset（）函数就是把1,2,3,4,5,6放到一个set集合里面，然后返回给调用处。
        for (String type : map.keySet()) {
            for (String str : map.get(type).keySet()) {
                if ("instance_registry".equals(str)) {
                    instanceMap = (Map<String, Object>)map.get(type).get(str);
                    for (String instanceGuid : instanceMap.keySet()) {
                        if (instanceGuid.equals(guid)) {
                            instancesMap = (Map<String, Object>) instanceMap.get(instanceGuid);
                            for (String instances : instancesMap.keySet()) {
                                resultMap = new HashMap<String, Object>();
                                instanceDetailMap = (Map<String, Object>) instancesMap.get(instances);
                                for (String instanceDetail : instanceDetailMap.keySet()) {
                                	//获取实例的详情 instanceDetail=instanceDetailMap.keySet()，包括"instance_index"，"state","computed_pcpu","used_memory","used_disk"等等。
                                	//这些信息都是http访问获取的DEA的信息
                                    resultMap.put(instanceDetail, instanceDetailMap.get(instanceDetail));
                                }
                                resultMap.put("host", map.get(type).get("host"));
                                resultMap.put("now", new Date().getTime());
                                resultList.add(resultMap);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return resultList;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void insertAppMonitDatas() {
		AppConfigsCriteria appConfigsCriteria = new AppConfigsCriteria();
		appConfigsCriteria.createCriteria().andIsAutoscalingEqualTo("1");
		List<AppConfigs> appConfigsList = appConfigsMapper.selectByExample(appConfigsCriteria);
		List<String> appGuids = new ArrayList<String>();
		Map<String, AppConfigs> appConfigsMap = new HashMap<String, AppConfigs>();
		for(AppConfigs appConfigs : appConfigsList) {
			appGuids.add(appConfigs.getAppGuid());
			appConfigsMap.put(appConfigs.getAppGuid(), appConfigs);
		}
		
    	List<AppMonitDatas> dataList = new ArrayList<AppMonitDatas>();
    	List<CfMonitDatas> cfDataList = new ArrayList<CfMonitDatas>();
    	AppMonitDatas appMonitDatas;
    	CfMonitDatas cfMonitDatas;
        Map<String, Map<String, Object>> map = getVarzList("DEA");
        Map<String, Object> instanceMap;
        Map<String, Object> instancesMap;
        Map<String, Object> instanceDetailMap;
        Map<String, Object> limitMap;
        Float stateRunningTimestamp;
        Float memUsedBytesAll = 0F;
        Float memFreeBytesAll = 0F;
        for (String type : map.keySet()) {
        	cfMonitDatas = new  CfMonitDatas();
        	cfDataList.add(cfMonitDatas);
            for (String str : map.get(type).keySet()) {
            	if("type".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setCfNodeName(map.get(type).get(str).toString());
            	} else if("uuid".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setCfNodeUuid(map.get(type).get(str).toString());
            	} else if("host".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setCfNodeIp(map.get(type).get(str).toString());
            	} else if("uptime".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setCfNodeUptime(map.get(type).get(str).toString());
            	} else if("num_cores".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setNumCores(Float.valueOf(map.get(type).get(str).toString()));
            	} else if("cpu".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setCpu(Float.valueOf(map.get(type).get(str).toString()));
            	} else if("cpu_load_avg".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setCpuLoadAvg(Float.valueOf(map.get(type).get(str).toString()));
            	} else if("mem_bytes".equals(str) && map.get(type).get(str) != null) {
        			cfMonitDatas.setMemBytes(Float.valueOf(map.get(type).get(str).toString()));
            	} else if("mem_used_bytes".equals(str) && map.get(type).get(str) != null) {
        			memUsedBytesAll += Float.valueOf(map.get(type).get(str).toString());
        			cfMonitDatas.setMemUsedBytes(Float.valueOf(map.get(type).get(str).toString()));
            	} else if("mem_free_bytes".equals(str) && map.get(type).get(str) != null) {
            		memFreeBytesAll += Float.valueOf(map.get(type).get(str).toString());
            		cfMonitDatas.setMemFreeBytes(Float.valueOf(map.get(type).get(str).toString()));
            	} else if("available_memory_ratio".equals(str) && map.get(type).get(str) != null) {
            		cfMonitDatas.setAvailableMemoryRatio(Float.valueOf(map.get(type).get(str).toString()));
            	} else if("available_disk_ratio".equals(str) && map.get(type).get(str) != null) {
            		cfMonitDatas.setAvailableDiskRatio(Float.valueOf(map.get(type).get(str).toString()));
            	}
            	
            	cfMonitDatas.setCreatedAt(new Date());
                if ("instance_registry".equals(str)) {
                    instanceMap = (Map<String, Object>)map.get(type).get(str);
                    for (String instanceGuid : instanceMap.keySet()) {
                    	if(appConfigsMap.containsKey(instanceGuid)) {
                    		appMonitDatas = new AppMonitDatas();
                    		appMonitDatas.setAppGuid(instanceGuid);
	                        instancesMap = (Map<String, Object>) instanceMap.get(instanceGuid);
	                        for (String instances : instancesMap.keySet()) {
	                            instanceDetailMap = (Map<String, Object>) instancesMap.get(instances);
	                            for (String instanceDetail : instanceDetailMap.keySet()) {
	                            	if("application_name".equals(instanceDetail)) {
	                            		appMonitDatas.setAppName(instanceDetailMap.get(instanceDetail).toString());
	                            	} else if("instance_index".equals(instanceDetail)) {
	                            		appMonitDatas.setInstanceId(Long.valueOf(instanceDetailMap.get(instanceDetail).toString()));
	                            	} else if("state".equals(instanceDetail)) {
	                            		appMonitDatas.setAppState(instanceDetailMap.get(instanceDetail).toString());
	                            	} else if("limits".equals(instanceDetail)) {
	                            		limitMap = (Map<String, Object>)instanceDetailMap.get(instanceDetail);
	                            		for(String limit : limitMap.keySet()) {
	                            			if("mem".equals(limit)) {
	                            				appMonitDatas.setAppMemLimit(Long.valueOf(limitMap.get(limit).toString()) * 1024 * 1024);
	                            			} else if("disk".equals(limit)) {
	                            				appMonitDatas.setAppDiskLimit(Long.valueOf(limitMap.get(limit).toString()) * 1024 * 1024);
	                            			}
	                            		}
	                            	} else if("used_memory_in_bytes".equals(instanceDetail)) {
	                            		appMonitDatas.setAppMemUsed(Long.valueOf(instanceDetailMap.get(instanceDetail).toString()));
	                            	} else if("used_disk_in_bytes".equals(instanceDetail)) {
	                            		appMonitDatas.setAppDiskUsed(Long.valueOf(instanceDetailMap.get(instanceDetail).toString()));
	                            	} else if("computed_pcpu".equals(instanceDetail)) {
	                            		appMonitDatas.setAppCpuUsed(Float.valueOf(instanceDetailMap.get(instanceDetail).toString()) * 100F);
	                            	} else if("state_running_timestamp".equals(instanceDetail)) {
	                            		stateRunningTimestamp = Float.valueOf(instanceDetailMap.get(instanceDetail).toString());
	                            		stateRunningTimestamp = new Date().getTime() - stateRunningTimestamp * 1000F;
	                            		appMonitDatas.setAppUptime(getUptime(stateRunningTimestamp));
	                            	}
	                            }
	                            appMonitDatas.setAppHost(map.get(type).get("host").toString());
	                            appMonitDatas.setCreatedAt(new Date());
	                            dataList.add(appMonitDatas);
	                        }
                    	}
                    }
                }
            }
        }
        
        Float allMemory = ((memUsedBytesAll + memFreeBytesAll)/1024/1024/1000F);
        Long allMemoryConfig = Long.valueOf(ConfigUtil.getConfig(cacheService, "mem_all"));
        if(allMemory.longValue() != allMemoryConfig) {
        	SystemConfig systemConfig = new SystemConfig();
        	systemConfig.setConfigValue(String.valueOf(allMemory.longValue()));
        	SystemConfigCriteria systemConfigCriteria = new SystemConfigCriteria();
        	systemConfigCriteria.createCriteria().andConfigNameEqualTo("mem_all");
        	systemConfigMapper.updateByExampleSelective(systemConfig, systemConfigCriteria);
        	cacheService.doReloadConfigCache();
        }
        
        if(dataList != null && dataList.size() > 0) {
        	appMonitDatasMapper.insertList(dataList);
        }
        
		String isDeaAutoscaling = ConfigUtil.getConfig(cacheService, "is_dea_autoscaling");

        if(isDeaAutoscaling != null && "1".equals(isDeaAutoscaling) && cfDataList != null && cfDataList.size() > 0) {
        	cfMonitDatasMapper.insertList(cfDataList);
        }
    }
    
    private String getUptime(Float uptime) {
    	Long days = Float.valueOf(uptime/1000/60/60/24).longValue();
    	Long hours = Float.valueOf((uptime-days*1000*60*60*24)/1000/60/60).longValue();
    	Long minutes = Float.valueOf((uptime-days*1000*60*60*24 - hours*1000*60*60)/1000/60).longValue();
    	
    	return days + "d:" + hours + "h:" + minutes + "m";
    }
}
