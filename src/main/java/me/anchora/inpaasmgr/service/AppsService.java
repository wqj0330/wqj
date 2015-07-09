/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.ccdb.Apps;
import me.anchora.inpaasmgr.entry.ccdb.AppsCriteria;

import org.apache.ibatis.session.RowBounds;

public interface AppsService {

    public List<Apps> queryAppsByPage(AppsCriteria criteria);

    public Integer queryMem();

    public Integer queryCount(Apps apps);

    public List<Map<String, Object>> appsCountChart(CommonVo commonVo, Locale locale);

	public List<Apps> queryAppsByPage(Apps apps, RowBounds rowBounds);
    
    public void startApps(String appName);
    
    public void restartApps(String appName);
    
    public void stopApps(String appName);
    
    public void deleteApps(String appName);
    
    public void sendEmail();
}
