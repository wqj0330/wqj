/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleUsers;
import me.anchora.inpaasmgr.service.RoleUsersService;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleUsersController extends BaseController {
	private static Logger logger = Logger.getLogger(RoleUsersController.class);   
    @Autowired
    private RoleUsersService roleUsersService;

    @RequestMapping(value = "/admin/getAllRoleUsersByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllRoleUsersByPage(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            RoleUsers roleUsers = (RoleUsers) EntryUtil.getObject(request, RoleUsers.class);
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info(roleUsers);
            PageTool.pageSetting(roleUsers, cacheService);
    
            List<RoleUsers> resultList = roleUsersService.queryRoleUsersWithOtherByPage(roleUsers, roleUsers.getRowBounds());
            Integer count = roleUsersService.queryCount(roleUsers);
    
            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, roleUsers.getPageSize()));
            result.addCurrentPage(roleUsers.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/createRoleUsers.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createRoleUsers(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            RoleUsers roleUsers = (RoleUsers) EntryUtil.getObject(request, RoleUsers.class);
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info(roleUsers);
    
            roleUsersService.insert(roleUsers);
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateRoleUsers.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void updateRoleUsers(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            RoleUsers roleUsers = (RoleUsers) EntryUtil.getObject(request, RoleUsers.class);
    
            roleUsersService.update(roleUsers);
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteRoleUsers.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteRoleUsers(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            RoleUsers roleUsers = (RoleUsers) EntryUtil.getObject(request, RoleUsers.class);
    
            roleUsersService.delete(roleUsers.getId());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }
}
