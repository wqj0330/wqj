/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.ccdb.Organizations;

import org.apache.ibatis.session.RowBounds;

public interface OrganizationsService {

    public List<Organizations> queryAll();

    public List<Organizations> queryOrganizationsByPage(Organizations organizations, RowBounds rowBounds);

    public Integer queryCount(Organizations organizations);
    
    public void deleteOrganization(Organizations organizations);
}
