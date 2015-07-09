/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.Date;
import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.RoleUsersMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleUsers;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleUsersCriteria;
import me.anchora.inpaasmgr.service.RoleUsersService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "roleUsersService")
public class RoleUsersServiceImpl implements RoleUsersService {

    @Autowired
    private RoleUsersMapper roleUsersMapper;

    public void insert(RoleUsers roleUsers) {
    	roleUsers.setCreatedAt(new Date());
        roleUsersMapper.insert(roleUsers);
    }

    public void update(RoleUsers roleUsers) {
    	RoleUsers rolesTmp=roleUsersMapper.selectByPrimaryKey(roleUsers.getId());
    	roleUsers.setCreatedAt(rolesTmp.getCreatedAt());
    	roleUsers.setUpdatedAt(new Date());
        roleUsersMapper.updateByPrimaryKey(roleUsers);
    }

    public List<RoleUsers> queryAll() {
        return roleUsersMapper.selectByExample(new RoleUsersCriteria());
    }

    public void delete(Long id) {
        roleUsersMapper.deleteByPrimaryKey(id);
    }

    public List<RoleUsers> queryRoleUsersByPage(RoleUsersCriteria criteria, RowBounds rowBounds) {
        List<RoleUsers> result = roleUsersMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
    }

    public List<RoleUsers> queryRoleUsersWithOtherByPage(RoleUsers roleUsers, RowBounds rowBounds) {
        List<RoleUsers> result = roleUsersMapper.selectByExampleWithOtherWithRowbounds(roleUsers, rowBounds);
        return result;
    }

    public Integer queryCount(RoleUsers roleUsers) {
        Integer result = roleUsersMapper.countByExampleWithOther(roleUsers);
        return result;
    }

    public List<RoleUsers> queryAllRoleUsers(RoleUsersCriteria criteria) {
        List<RoleUsers> result = roleUsersMapper.selectByExample(criteria);
        return result;
    }
}
