/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.SystemConfigMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfig;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfigCriteria;
import me.anchora.inpaasmgr.service.ConfigService;
import me.anchora.inpaasmgr.utils.SecurityUtil;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "configService")
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public SystemConfig insert(SystemConfig systemConfig) {
        if(systemConfig.getIsEncrypt() != null && systemConfig.getIsEncrypt()) {
            systemConfig.setConfigValue(SecurityUtil.encrypt(systemConfig.getConfigValue()));
        }
        Integer id = systemConfigMapper.insert(systemConfig);
        systemConfig.setId(Long.valueOf(id));
        return systemConfig;
    }

    @Override
    public void update(SystemConfig systemConfig) {
        systemConfigMapper.updateByPrimaryKey(systemConfig);
    }

    @Override
    public SystemConfig queryByName(String configName) {
        SystemConfigCriteria criteria = new SystemConfigCriteria();
        criteria.createCriteria().andConfigNameEqualTo(configName);
        List<SystemConfig> systemConfigList;
        SystemConfig result = null;
        systemConfigList = systemConfigMapper.selectByExample(criteria);
        if (systemConfigList != null && systemConfigList.size() != 0) {
            result = systemConfigList.get(0);
        }
        return result;
    }

    @Override
    public List<SystemConfig> queryAll() {
        return systemConfigMapper.selectByExample(new SystemConfigCriteria());
    }

    @Override
    public void delete(Long id) {
        systemConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SystemConfig> querySystemConfigByPage(SystemConfigCriteria criteria, RowBounds rowBounds) {
        List<SystemConfig> result = systemConfigMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
    }

    @Override
    public Integer queryCount(SystemConfigCriteria criteria) {
        Integer result = systemConfigMapper.countByExample(criteria);
        return result;
    }
}
