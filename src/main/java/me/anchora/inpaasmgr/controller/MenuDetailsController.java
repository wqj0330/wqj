/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetailsCriteria;
import me.anchora.inpaasmgr.service.MenuDetailsService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuDetailsController extends BaseController {

    @Autowired
    private MenuDetailsService menuDetailsService;

    @RequestMapping(value = "/admin/getAllMenuDetailsByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllMenuDetailsByPage(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuDetails menuDetails = (MenuDetails) EntryUtil.getObject(request, MenuDetails.class);
            PageTool.pageSetting(menuDetails, cacheService);
    
            List<MenuDetails> resultList = menuDetailsService.queryMenuDetailsWithOtherByPage(menuDetails, menuDetails.getRowBounds());
            Integer count = menuDetailsService.queryCount(menuDetails);
    
            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, menuDetails.getPageSize()));
            result.addCurrentPage(menuDetails.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/getAllMenuDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllMenuDetails(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            MenuDetails menuDetails = (MenuDetails) EntryUtil.getObject(request, MenuDetails.class);
            MenuDetailsCriteria criteria = (MenuDetailsCriteria)CriteriaUtil.createCriteria(menuDetails, MenuDetailsCriteria.class);

            List<MenuDetails> resultList = menuDetailsService.queryAllMenuDetails(criteria);

            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/createMenuDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createMenuDetails(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuDetails menuDetails = (MenuDetails) EntryUtil.getObject(request, MenuDetails.class);
    
            menuDetailsService.insert(menuDetails);
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateMenuDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void updateMenuDetails(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuDetails menuDetails = (MenuDetails) EntryUtil.getObject(request, MenuDetails.class);
    
            menuDetailsService.update(menuDetails);
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteMenuDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteMenuDetails(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuDetails menuDetails = (MenuDetails) EntryUtil.getObject(request, MenuDetails.class);
    
            menuDetailsService.delete(menuDetails.getId());
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }
}
