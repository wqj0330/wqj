/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;
import java.util.Map;

public interface VarzService {
    public void vcapComponentDiscover();
    
    public List<Map<String, Object>> getComponentList();
    
    public Map<String, Map<String, Object>> getVarzList(String node);

    public List<Map<String, Object>> getAppDetail(String guid);
    
    public void insertAppMonitDatas();
}
