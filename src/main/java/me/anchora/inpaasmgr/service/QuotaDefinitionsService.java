/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.ccdb.QuotaDefinitions;
import me.anchora.inpaasmgr.entry.ccdb.QuotaDefinitionsCriteria;

import org.apache.ibatis.session.RowBounds;

public interface QuotaDefinitionsService {

    public List<QuotaDefinitions> queryAll();

    public void delete(Integer id);

    public List<QuotaDefinitions> queryQuotaDefinitionsByPage(QuotaDefinitionsCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(QuotaDefinitionsCriteria criteria);
}
