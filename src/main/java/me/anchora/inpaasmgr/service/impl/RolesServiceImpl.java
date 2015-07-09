/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.Date;
import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.RolesMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.Roles;
import me.anchora.inpaasmgr.entry.inpaasmgr.RolesCriteria;
import me.anchora.inpaasmgr.service.RolesService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "rolesService")
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;
   
    public void insert(Roles roles) {
    	roles.setCreatedAt(new Date());
        rolesMapper.insert(roles);
    }

    public void update(Roles roles) {
    	Roles rolesTmp=rolesMapper.selectByPrimaryKey(roles.getId());
    	roles.setCreatedAt(rolesTmp.getCreatedAt());
    	roles.setUpdatedAt(new Date());
        rolesMapper.updateByPrimaryKey(roles);
    }

    public List<Roles> queryAll() {
        return rolesMapper.selectByExample(new RolesCriteria());
    }

    public void delete(Long id) {
        rolesMapper.deleteByPrimaryKey(id);
    }

    public List<Roles> queryRolesByPage(RolesCriteria criteria, RowBounds rowBounds) {
        List<Roles> result = rolesMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
    }

    public Integer queryCount(RolesCriteria criteria) {
        Integer result = rolesMapper.countByExample(criteria);
        return result;
    }

    public List<Roles> queryAllRoles(RolesCriteria criteria) {
        List<Roles> result = rolesMapper.selectByExample(criteria);
        return result;
    }
}
