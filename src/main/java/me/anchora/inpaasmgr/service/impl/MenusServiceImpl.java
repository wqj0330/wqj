/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anchora.inpaasmgr.dao.inpaasmgr.MenusMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.Menus;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenusCriteria;
import me.anchora.inpaasmgr.service.MenusService;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "menusService")
public class MenusServiceImpl implements MenusService {

    private static Logger logger = Logger.getLogger(MenusServiceImpl.class);
    
    @Autowired
    private MenusMapper menusMapper;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryMenus(Login user) {
    	//首先确定有权限访问的菜单信息（menusList），然后生成菜单
        List<Menus> menusList = menusMapper.selectWithMany(user);
        
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        Map<Long, Map<String, Object>> tmpmap = new HashMap<Long, Map<String,Object>>();
        Map<String, Object> firstLevelMenu;
        List<Map<String, Object>> subMenus;
        Map<String, Object> secondryLevelMenu;
        for(Menus menu : menusList) {
            if(menu.getParentMenuId() == null) {
            	//生成一级菜单（参考menu-generate.js）
            	firstLevelMenu = new HashMap<String, Object>();
                
                subMenus = new ArrayList<Map<String,Object>>();
                //"title"，menu.getMenuDetails().getMenuValue()菜单名
                firstLevelMenu.put("title", menu.getMenuDetails().getMenuValue());
              //"styleClass"，menu.getStyleClass()可用图标(icon-roof,icon-manage,icon-system)
                firstLevelMenu.put("styleClass", menu.getStyleClass());
              //"menuItemId", menu.getMenuItemId()菜单ID（tab_）
                firstLevelMenu.put("menuItemId", menu.getMenuItemId());
                firstLevelMenu.put("subMenus", subMenus);
                tmpmap.put(menu.getId(), firstLevelMenu);
                result.add(firstLevelMenu);
            } else {
            	//生成二级菜单（参考menu-generate.js）
                if(tmpmap.containsKey(menu.getParentMenuId())) {
                    firstLevelMenu = tmpmap.get(menu.getParentMenuId());
                    subMenus = (List<Map<String, Object>>)firstLevelMenu.get("subMenus");
                    secondryLevelMenu = new HashMap<String, Object>();
                    secondryLevelMenu.put("menuItemId", menu.getMenuItemId());
                    secondryLevelMenu.put("title", menu.getMenuDetails().getMenuValue());
                    subMenus.add(secondryLevelMenu);
                } else {
                    logger.warn("Menu menusuration error. Menu id:" + menu.getId());
                }
            }
        }
        
        return result;
    }

    public void insert(Menus menus) {
        menusMapper.insert(menus);
    }

    public void update(Menus menus) {
        menusMapper.updateByPrimaryKey(menus);
    }

    public List<Menus> queryAll() {
        return menusMapper.selectByExample(new MenusCriteria());
    }

    public void delete(Long id) {
        menusMapper.deleteByPrimaryKey(id);
    }

    public List<Menus> queryMenusWithParentByPage(Menus menus, RowBounds rowBounds) {
        List<Menus> result = menusMapper.selectByExampleWithParentWithRowbounds(menus, rowBounds);
        return result;
    }

    public Integer queryCount(Menus menus) {
        Integer result = menusMapper.countByExampleWithParent(menus);
        return result;
    }

    public List<Menus> queryAllMenus(MenusCriteria criteria) {
        List<Menus> result = menusMapper.selectByExample(criteria);
    	
        return result;
    }
}
