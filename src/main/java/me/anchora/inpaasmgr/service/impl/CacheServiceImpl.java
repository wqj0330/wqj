/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anchora.inpaasmgr.cache.EhcacheManager;
import me.anchora.inpaasmgr.dao.inpaasmgr.SystemConfigMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfig;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfigCriteria;
import me.anchora.inpaasmgr.service.CacheService;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;
    
    @Override
    public void doReloadConfigCache() {
        Map<String, SystemConfig> systemConfigMap = new HashMap<String, SystemConfig>();
        SystemConfigCriteria criteria = new SystemConfigCriteria();
        List<SystemConfig> systemConfigList = systemConfigMapper.selectByExample(criteria);
        for (SystemConfig systemConfig : systemConfigList) {
            systemConfigMap.put(systemConfig.getConfigName(), systemConfig);
        }
        EhcacheManager.getInstance().put(new Element(SystemConfig.class.getName(), systemConfigMap));
    }

    @Override
    public void doReloadAllCache() {
        doReloadConfigCache();
    }
}
