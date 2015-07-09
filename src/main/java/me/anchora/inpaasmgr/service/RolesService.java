/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.inpaasmgr.Roles;
import me.anchora.inpaasmgr.entry.inpaasmgr.RolesCriteria;

import org.apache.ibatis.session.RowBounds;

public interface RolesService {
    public void insert(Roles roles);

    public void update(Roles roles);

    public List<Roles> queryAll();

    public void delete(Long id);

    public List<Roles> queryAllRoles(RolesCriteria criteria);

    public List<Roles> queryRolesByPage(RolesCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(RolesCriteria criteria);
}
