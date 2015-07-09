/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleMenus;
import me.anchora.inpaasmgr.service.RoleMenusService;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleMenusController extends BaseController {

    @Autowired
    private RoleMenusService roleMenusService;
    @RequestMapping(value = "/admin/getAllRoleMenusByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllRoleMenusByPage(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
        	 RoleMenus roleMenus = (RoleMenus) EntryUtil.getObject(request, RoleMenus.class);
             PageTool.pageSetting(roleMenus, cacheService);
     
             List<RoleMenus> resultList = roleMenusService.queryRoleMenusWithOtherByPage(roleMenus, roleMenus.getRowBounds());
             Integer count = roleMenusService.queryCount(roleMenus);
             
            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, roleMenus.getPageSize()));
            result.addCurrentPage(roleMenus.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/createRoleMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void insert(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
        	RoleMenus roleMenus = (RoleMenus) EntryUtil.getObject(request, RoleMenus.class);
        	roleMenusService.insert(roleMenus);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateRoleMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void update(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
        	RoleMenus roleMenus = (RoleMenus) EntryUtil.getObject(request, RoleMenus.class);
        	roleMenusService.update(roleMenus);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteRoleMenus.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
        	RoleMenus roleMenus = (RoleMenus) EntryUtil.getObject(request, RoleMenus.class);
        	roleMenusService.delete(roleMenus.getId());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}
