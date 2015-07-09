/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.inpaasmgr.RoleMenus;
import org.apache.ibatis.session.RowBounds;

public interface RoleMenusService {
    public Integer insert(RoleMenus roleMenus);

    public void update(RoleMenus roleMenus);

    public void delete(Long id);

	public Integer queryCount(RoleMenus roleMenus);

	public List<RoleMenus> queryRoleMenusWithOtherByPage(RoleMenus roleMenus, RowBounds rowBounds);
}
