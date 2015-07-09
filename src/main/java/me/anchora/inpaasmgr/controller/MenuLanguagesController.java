/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuLanguages;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuLanguagesCriteria;
import me.anchora.inpaasmgr.service.MenuLanguagesService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuLanguagesController extends BaseController {

    @Autowired
    private MenuLanguagesService menuLanguagesService;

    @RequestMapping(value = "/admin/getAllMenuLanguagesByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllMenuLanguagesByPage(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuLanguages menuLanguages = (MenuLanguages) EntryUtil.getObject(request, MenuLanguages.class);
            MenuLanguagesCriteria criteria = (MenuLanguagesCriteria)CriteriaUtil.createCriteria(menuLanguages, MenuLanguagesCriteria.class);
            PageTool.pageSetting(menuLanguages, cacheService);
    
            List<MenuLanguages> resultList = menuLanguagesService.queryMenuLanguagesByPage(criteria, menuLanguages.getRowBounds());
            Integer count = menuLanguagesService.queryCount(criteria);
    
            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, menuLanguages.getPageSize()));
            result.addCurrentPage(menuLanguages.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/getAllMenuLanguages.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllMenuLanguages(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            MenuLanguages menuLanguages = (MenuLanguages) EntryUtil.getObject(request, MenuLanguages.class);
            MenuLanguagesCriteria criteria = (MenuLanguagesCriteria)CriteriaUtil.createCriteria(menuLanguages, MenuLanguagesCriteria.class);

            List<MenuLanguages> resultList = menuLanguagesService.queryAllMenuLanguages(criteria);

            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/createMenuLanguages.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createMenuLanguages(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuLanguages menuLanguages = (MenuLanguages) EntryUtil.getObject(request, MenuLanguages.class);
    
            menuLanguagesService.insert(menuLanguages);
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateMenuLanguages.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void updateMenuLanguages(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuLanguages menuLanguages = (MenuLanguages) EntryUtil.getObject(request, MenuLanguages.class);
    
            menuLanguagesService.update(menuLanguages);
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteMenuLanguages.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteMenuLanguages(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
            MenuLanguages menuLanguages = (MenuLanguages) EntryUtil.getObject(request, MenuLanguages.class);
    
            menuLanguagesService.delete(menuLanguages.getId());
    
            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }
}
