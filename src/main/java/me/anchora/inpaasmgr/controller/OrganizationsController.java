/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.ccdb.Organizations;
import me.anchora.inpaasmgr.service.OrganizationsService;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrganizationsController extends BaseController {

    @Autowired
    private OrganizationsService organizationsService;

    @RequestMapping(value = "/admin/getAllOrgsByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllOrgsByPage(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            Organizations organizations = (Organizations) EntryUtil.getObject(request, Organizations.class);
//            OrganizationsCriteria criteria = (OrganizationsCriteria)CriteriaUtil.createCriteria(organizations, OrganizationsCriteria.class);
            PageTool.pageSetting(organizations, cacheService);

            List<Organizations> resultList = organizationsService.queryOrganizationsByPage(organizations, organizations.getRowBounds());
            Integer count = organizationsService.queryCount(organizations);

            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, organizations.getPageSize()));
            result.addCurrentPage(organizations.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteOrg.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteOrg(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            Organizations organizations = (Organizations) EntryUtil.getObject(request, Organizations.class);

            organizationsService.deleteOrganization(organizations);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}
