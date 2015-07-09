/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.Roles;
import me.anchora.inpaasmgr.entry.inpaasmgr.RolesCriteria;
import me.anchora.inpaasmgr.service.RolesService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RolesController extends BaseController {
	private static Logger logger = Logger.getLogger(RolesController.class);
    @Autowired
    private RolesService rolesService;

    @RequestMapping(value = "/admin/getAllRolesByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllRolesByPage(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Roles roles = (Roles) EntryUtil.getObject(request, Roles.class);
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info(roles);
            RolesCriteria criteria = (RolesCriteria)CriteriaUtil.createCriteria(roles, RolesCriteria.class);
            PageTool.pageSetting(roles, cacheService);
    
            List<Roles> resultList = rolesService.queryRolesByPage(criteria, roles.getRowBounds());
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info(resultList.get(0));
            Integer count = rolesService.queryCount(criteria);
    
            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, roles.getPageSize()));
            result.addCurrentPage(roles.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }


    @RequestMapping(value = "/admin/getAllRoles.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllRoles(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            Roles roles = (Roles) EntryUtil.getObject(request, Roles.class);
            RolesCriteria criteria = (RolesCriteria)CriteriaUtil.createCriteria(roles, RolesCriteria.class);

            List<Roles> resultList = rolesService.queryAllRoles(criteria);

            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    
    @RequestMapping(value = "/admin/createRoles.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createRoles(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Roles roles = (Roles) EntryUtil.getObject(request, Roles.class);
    
            rolesService.insert(roles);
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateRoles.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void updateRoles(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Roles roles = (Roles) EntryUtil.getObject(request, Roles.class);
    
            rolesService.update(roles);
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteRoles.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteRoles(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            Roles roles = (Roles) EntryUtil.getObject(request, Roles.class);
    
            rolesService.delete(roles.getId());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }
}
