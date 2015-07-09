/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;
import java.util.Date;
import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.RoleMenusMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleMenus;
import me.anchora.inpaasmgr.service.RoleMenusService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "roleMenusService")
public class RoleMenusServiceImpl implements RoleMenusService {

    @Autowired
    private RoleMenusMapper roleMenusMapper;
 
    public List<RoleMenus> queryRoleMenusWithOtherByPage(RoleMenus roleMenus, RowBounds rowBounds) {
        List<RoleMenus> result = roleMenusMapper.selectByExampleWithOtherWithRowbounds(roleMenus, rowBounds);
        return result;
    }

    public Integer queryCount(RoleMenus roleMenus) {
        Integer result = roleMenusMapper.countByExampleWithOther(roleMenus);
        return result;
    }

    public Integer insert(RoleMenus roleMenus) {
    	roleMenus.setCreatedAt(new Date());
        Integer result = roleMenusMapper.insert(roleMenus);
        return result;
    }

    public void update(RoleMenus roleMenus) {
    	RoleMenus roleMenusTmp = roleMenusMapper.selectByPrimaryKey(roleMenus.getId());
    	roleMenus.setCreatedAt(roleMenusTmp.getCreatedAt());
    	roleMenus.setUpdatedAt(new Date());
    	roleMenusMapper.updateByPrimaryKey(roleMenus);
    }

    public void delete(Long id) {
    	roleMenusMapper.deleteByPrimaryKey(id);
    }
}
