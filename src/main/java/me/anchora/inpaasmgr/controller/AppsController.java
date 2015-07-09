package me.anchora.inpaasmgr.controller;

import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.ccdb.Apps;
import me.anchora.inpaasmgr.service.AppsService;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppsController extends BaseController {
    @Autowired
    private AppsService appsService;

    @RequestMapping(value = "/admin/getAllAppsByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllAppsByPage(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        try {
            Apps apps = (Apps) EntryUtil.getObject(request, Apps.class);
            PageTool.pageSetting(apps, cacheService);

            List<Apps> resultList = appsService.queryAppsByPage(apps, apps.getRowBounds());
            Integer count = appsService.queryCount(apps);

            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, apps.getPageSize()));
            result.addCurrentPage(apps.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/startApp.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void startApp(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);

            appsService.startApps(commonVo.getAppName());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/stopApp.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void stopApp(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);

            appsService.stopApps(commonVo.getAppName());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/restartApp.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void restartApp(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);

            appsService.restartApps(commonVo.getAppName());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteApp.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteApp(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);

            appsService.deleteApps(commonVo.getAppName());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}
