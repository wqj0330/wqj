/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfig;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfigCriteria;
import me.anchora.inpaasmgr.exception.AppException;

public interface ConfigService {

    public SystemConfig insert(SystemConfig systemConfig);

    public void update(SystemConfig systemConfig);

    public SystemConfig queryByName(String configName) throws AppException;

    public List<SystemConfig> queryAll();

    public void delete(Long id);

    public List<SystemConfig> querySystemConfigByPage(SystemConfigCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(SystemConfigCriteria criteria);
}
