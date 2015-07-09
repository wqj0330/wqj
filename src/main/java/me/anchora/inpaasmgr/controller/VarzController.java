package me.anchora.inpaasmgr.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.service.VarzService;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VarzController extends BaseController {

    @Autowired
    private VarzService varzService;

    @RequestMapping(value = "/admin/getAllComponent.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllComponent(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            List<Map<String, Object>> resultList = varzService.getComponentList();
            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }
        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/getVarzDetail.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getVarzDetail(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            Map<String, Map<String, Object>> map = varzService.getVarzList("DEA");
            result.addResult(map);
        } catch (Exception e) {
            super.exception(request, e);
        }
        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/getAppsDetail.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAppsDetail(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);
            //commonVo.getGuid()=?  参考paas.mgr.apps.js（data:JSON.stringify({guid:appguid})）中可知guid即appguid，即Commonvo中guid的值为（等于）Apps中guid的值
            List<Map<String, Object>> resultList = varzService.getAppDetail(commonVo.getGuid());
            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }
        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/insertAppMonitDatas.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void insertAppMonitDatas(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            varzService.insertAppMonitDatas();
        } catch (Exception e) {
            super.exception(request, e);
        }
        result.asynchronousPrintResult(response, result.returnResult());
    }

}
