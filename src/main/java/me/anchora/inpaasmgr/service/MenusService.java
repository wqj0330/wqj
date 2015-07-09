/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;
import java.util.Map;

import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.Menus;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenusCriteria;

import org.apache.ibatis.session.RowBounds;

public interface MenusService {
    public List<Map<String, Object>> queryMenus(Login user);
    public void insert(Menus menus);

    public void update(Menus menus);

    public List<Menus> queryAll();

    public void delete(Long id);

    public List<Menus> queryMenusWithParentByPage(Menus menus, RowBounds rowBounds);

    public Integer queryCount(Menus menus);
    
    public List<Menus> queryAllMenus(MenusCriteria criteria);
   
}
