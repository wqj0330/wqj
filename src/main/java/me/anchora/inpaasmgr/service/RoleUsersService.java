/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.inpaasmgr.RoleUsers;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleUsersCriteria;

import org.apache.ibatis.session.RowBounds;

public interface RoleUsersService {
    public void insert(RoleUsers roleUsers);

    public void update(RoleUsers roleUsers);

    public List<RoleUsers> queryAll();

    public void delete(Long id);

    public List<RoleUsers> queryAllRoleUsers(RoleUsersCriteria criteria);

    public List<RoleUsers> queryRoleUsersByPage(RoleUsersCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(RoleUsers roleUsers);

	public List<RoleUsers> queryRoleUsersWithOtherByPage(RoleUsers roleUsers, RowBounds rowBounds);
}
