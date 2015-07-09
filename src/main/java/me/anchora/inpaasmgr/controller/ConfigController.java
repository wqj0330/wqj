/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfig;
import me.anchora.inpaasmgr.entry.inpaasmgr.SystemConfigCriteria;
import me.anchora.inpaasmgr.service.ConfigService;
import me.anchora.inpaasmgr.service.InitDbService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService configService;
    
    @Autowired
    private InitDbService initDbService;

    @RequestMapping(value = "/admin/getAllConfigByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllConfigByPage(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            SystemConfig systemConfig = (SystemConfig) EntryUtil.getObject(request, SystemConfig.class);
            SystemConfigCriteria criteria = (SystemConfigCriteria)CriteriaUtil.createCriteria(systemConfig, SystemConfigCriteria.class);
            PageTool.pageSetting(systemConfig, cacheService);

//            initDbService.initDb();
//            initDbService.insert();
            
            List<SystemConfig> resultList = configService.querySystemConfigByPage(criteria, systemConfig.getRowBounds());
            Integer count = configService.queryCount(criteria);

            result.addList(resultList);
            result.addTotalPage(PageTool.pageCount(count, systemConfig.getPageSize()));
            result.addCurrentPage(systemConfig.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/createConfig.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createConfig(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            SystemConfig systemConfig = (SystemConfig) EntryUtil.getObject(request, SystemConfig.class);

            configService.insert(systemConfig);

            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateConfig.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void updateConfig(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            SystemConfig systemConfig = (SystemConfig) EntryUtil.getObject(request, SystemConfig.class);

            configService.update(systemConfig);

            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteConfig.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteConfig(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            SystemConfig systemConfig = (SystemConfig) EntryUtil.getObject(request, SystemConfig.class);

            configService.delete(systemConfig.getId());

            cacheService.doReloadAllCache();
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}
