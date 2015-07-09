/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import static me.anchora.inpaasmgr.Constants.POINT_NUM;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.service.AppsService;
import me.anchora.inpaasmgr.service.UaaUsersService;
import me.anchora.inpaasmgr.service.UserBalancesService;
import me.anchora.inpaasmgr.utils.ConfigUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.RequestUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "overviewController")
public class OverviewController extends BaseController {
    private static Logger logger = Logger.getLogger(OverviewController.class);

    @Autowired
    private UaaUsersService usersService;

    @Autowired
    private AppsService appsService;
    
    @Autowired
    private UserBalancesService userBalancesService;

    @RequestMapping(value = "/admin/usersCountChart.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void usersCountChart(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);
            if (commonVo.getPointNum() == null || "".equals(commonVo.getPointNum())) {
                String pointNum = ConfigUtil.getConfig(cacheService, "chart_point_num");
                if (pointNum == null || "".equals(pointNum)) {
                    logger.info("chart_point_num has not bean configured! Default value " + POINT_NUM + " has been setted.");
                    pointNum = POINT_NUM;
                }
                commonVo.setPointNum(pointNum);
            }

            List<Map<String, Object>> resultList = usersService.usersChart(commonVo, RequestUtil.getLocale(request));
            result.addList(resultList);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/appsCountChart.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void appsCountChart(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);
            if (commonVo.getPointNum() == null || "".equals(commonVo.getPointNum())) {
                String pointNum = ConfigUtil.getConfig(cacheService, "chart_point_num");
                if (pointNum == null || "".equals(pointNum)) {
                    logger.info("chart_point_num has not bean configured! Default value " + POINT_NUM + " has been setted.");
                    pointNum = POINT_NUM;
                }
                commonVo.setPointNum(pointNum);
            }

            List<Map<String, Object>> resultList = appsService.appsCountChart(commonVo, RequestUtil.getLocale(request));
            result.addList(resultList);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    //添加的userBalances的chart
    @RequestMapping(value = "/admin/userBalancesCountChart.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void userBalancesCountChart(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);
            if (commonVo.getPointNum() == null || "".equals(commonVo.getPointNum())) {
                String pointNum = ConfigUtil.getConfig(cacheService, "chart_point_num");
                if (pointNum == null || "".equals(pointNum)) {
                    logger.info("chart_point_num has not bean configured! Default value " + POINT_NUM + " has been setted.");
                    pointNum = POINT_NUM;
                }
                commonVo.setPointNum(pointNum);
            }

            List<Map<String, Object>> resultList = userBalancesService.userBalancesCountChart(commonVo, RequestUtil.getLocale(request));
            result.addList(resultList);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}