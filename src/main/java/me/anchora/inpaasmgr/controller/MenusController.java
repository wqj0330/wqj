/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.Menus;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenusCriteria;
import me.anchora.inpaasmgr.service.MenusService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;
import me.anchora.inpaasmgr.utils.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenusController extends BaseController {

    @Autowired
    private MenusService menusService;

    @RequestMapping(value = "/admin/getMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getMenus(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            Login user = new Login();
            //参考RequestUtil.java,user.setLocale(RequestUtil.getLocale(request).toString())获取cookie中“lang”的Value值。
            user.setLocale(RequestUtil.getLocale(request).toString());
            //是取session里面的“username“给这个username赋值，login.js中$.cookie("username")&&$.cookie("password")
            user.setUserName((String)request.getSession().getAttribute("userName"));
            //菜单类型Map<String, Object>，MenusServiceImpl.java
            List<Map<String, Object>> resultList = menusService.queryMenus(user);

            result.addList(resultList);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/getAllMenusByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllMenusByPage(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Menus menus = (Menus) EntryUtil.getObject(request, Menus.class);
            PageTool.pageSetting(menus, cacheService);
    
            List<Menus> resultList = menusService.queryMenusWithParentByPage(menus, menus.getRowBounds());
            Integer count = menusService.queryCount(menus);
    
            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, menus.getPageSize()));
            result.addCurrentPage(menus.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/getAllMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllMenus(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            Menus menus = (Menus) EntryUtil.getObject(request, Menus.class);
            MenusCriteria criteria = (MenusCriteria)CriteriaUtil.createCriteria(menus, MenusCriteria.class);

            List<Menus> resultList = menusService.queryAllMenus(criteria);

            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/createMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createMenus(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Menus menus = (Menus) EntryUtil.getObject(request, Menus.class);
    
            menusService.insert(menus);
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void updateMenus(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Menus menus = (Menus) EntryUtil.getObject(request, Menus.class);
    
            menusService.update(menus);
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteMenus(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Menus menus = (Menus) EntryUtil.getObject(request, Menus.class);
    
            menusService.delete(menus.getId());
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }
}
