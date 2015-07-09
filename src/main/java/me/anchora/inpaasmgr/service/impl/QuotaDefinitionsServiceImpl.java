/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.List;

import me.anchora.inpaasmgr.dao.ccdb.QuotaDefinitionsMapper;
import me.anchora.inpaasmgr.entry.ccdb.QuotaDefinitions;
import me.anchora.inpaasmgr.entry.ccdb.QuotaDefinitionsCriteria;
import me.anchora.inpaasmgr.service.QuotaDefinitionsService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "quotaDefinitionsService")
public class QuotaDefinitionsServiceImpl implements QuotaDefinitionsService {

    @Autowired
    private QuotaDefinitionsMapper quotaDefinitionsMapper;

    public List<QuotaDefinitions> queryAll() {
        return quotaDefinitionsMapper.selectByExample(new QuotaDefinitionsCriteria());
    }

    public void delete(Integer id) {
        quotaDefinitionsMapper.deleteByPrimaryKey(id);
    }

    public List<QuotaDefinitions> queryQuotaDefinitionsByPage(QuotaDefinitionsCriteria criteria, RowBounds rowBounds) {
        List<QuotaDefinitions> result = quotaDefinitionsMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
    }

    public Integer queryCount(QuotaDefinitionsCriteria criteria) {
        Integer result = quotaDefinitionsMapper.countByExample(criteria);
        return result;
    }
}
