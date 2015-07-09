/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.List;

import me.anchora.inpaasmgr.base.BaseService;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsMapper;
import me.anchora.inpaasmgr.dao.ccdb.QuotaDefinitionsMapper;
import me.anchora.inpaasmgr.dao.ccdb.SpacesMapper;
import me.anchora.inpaasmgr.entry.ccdb.Organizations;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsCriteria;
import me.anchora.inpaasmgr.entry.ccdb.Spaces;
import me.anchora.inpaasmgr.entry.ccdb.SpacesCriteria;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.OrganizationsService;
import me.anchora.inpaasmgr.utils.CloudFoundryClientUtil;
import me.anchora.inpaasmgr.utils.SystemUtil;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "organizationsService")
public class OrganizationsServiceImpl extends BaseService implements OrganizationsService {
    
    private static Logger logger = Logger.getLogger(OrganizationsServiceImpl.class);
    
    @Autowired
    private SpacesMapper spacesMapper;

    @Autowired
    private OrganizationsMapper organizationsMapper;

    @Autowired
    private QuotaDefinitionsMapper quotaDefinitionsMapper;

    public List<Organizations> queryAll() {
        return organizationsMapper.selectByExample(new OrganizationsCriteria());
    }

    public List<Organizations> queryOrganizationsByPage(Organizations organizations, RowBounds rowBounds) {
        return organizationsMapper.selectWithQuotaWithRowbounds(organizations, rowBounds);
    }

    public Integer queryCount(Organizations organizations) {
        return organizationsMapper.countWithQuota(organizations);
    }

    public void deleteOrganization(Organizations organizations) {
        if (organizations == null || organizations.getGuid() == null || organizations.getGuid().length() == 0) {
            SystemUtil.throwException(MsgEnum.ORGANIZATIONS_10002.getCode());
        }
        
        if (organizations == null || organizations.getId() == null) {
            SystemUtil.throwException(MsgEnum.ORGANIZATIONS_10003.getCode());
        }
        
        CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens());

        SpacesCriteria spacesCriteria = new SpacesCriteria();
        spacesCriteria.createCriteria().andOrganizationIdEqualTo(organizations.getId());
        List<Spaces> spacesList = spacesMapper.selectByExample(spacesCriteria);
        for(Spaces space : spacesList) {
            try {
                cloudFoundryClient.deleteSpace(space.getGuid());
            } catch (Exception e) {
                logger.info(e);
                CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
                cloudFoundryClient.deleteSpace(space.getGuid());
            }
        }
        
        try {
            cloudFoundryClient.deleteOrganization(organizations.getGuid());
        } catch (Exception e) {
            logger.info(e);
            CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
            cloudFoundryClient.deleteOrganization(organizations.getGuid());
        }
    }
}
