/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalanceDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalanceDetailsCriteria;
import me.anchora.inpaasmgr.service.UserBalanceDetailsService;
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
public class UserBalanceDetailsController extends BaseController {
	private static Logger logger = Logger.getLogger(UserBalanceDetailsController.class);
    @Autowired
    private UserBalanceDetailsService userBalanceDetailsService;

    @RequestMapping(value = "/admin/getAllUserBalanceDetailsByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllUserBalanceDetailsByPage(HttpServletRequest request, HttpServletResponse response) {
    	
        result = new PaasResult();
        try {
        	UserBalanceDetails userBalanceDetails = (UserBalanceDetails) EntryUtil.getObject(request, UserBalanceDetails.class);
        	UserBalanceDetailsCriteria criteria = (UserBalanceDetailsCriteria)CriteriaUtil.createCriteria(userBalanceDetails, UserBalanceDetailsCriteria.class);
            PageTool.pageSetting(userBalanceDetails, cacheService);
    
            List<UserBalanceDetails> resultList = userBalanceDetailsService.queryUserBalanceDetailsByPage(criteria, userBalanceDetails.getRowBounds());
            Integer count = userBalanceDetailsService.queryCount(criteria);
    
//            userBalanceDetailsService.dofree();
//            logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            logger.info("赠送成功");
//            //发送邮件
//            userBalanceDetailsService.sendEmail();
            
            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, userBalanceDetails.getPageSize()));
            result.addCurrentPage(userBalanceDetails.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }


    @RequestMapping(value = "/admin/getAllUserBalanceDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllUserBalanceDetails(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
        	UserBalanceDetails userBalanceDetails = (UserBalanceDetails) EntryUtil.getObject(request, UserBalanceDetails.class);
        	UserBalanceDetailsCriteria criteria = (UserBalanceDetailsCriteria)CriteriaUtil.createCriteria(userBalanceDetails, UserBalanceDetailsCriteria.class);

            List<UserBalanceDetails> resultList = userBalanceDetailsService.queryAllUserBalanceDetails(criteria);

            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    
    @RequestMapping(value = "/admin/createUserBalanceDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createUserBalanceDetails(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
        	UserBalanceDetails userBalanceDetails = (UserBalanceDetails) EntryUtil.getObject(request, UserBalanceDetails.class);
    
        	userBalanceDetailsService.insert(userBalanceDetails);
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateUserBalanceDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void updateUserBalanceDetails(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
        	UserBalanceDetails userBalanceDetails = (UserBalanceDetails) EntryUtil.getObject(request, UserBalanceDetails.class);
        	if(null==userBalanceDetails){
        		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>\nuserBalanceDetails is null");
        	}
        	if(null==userBalanceDetails.getDetailBalance()){
        		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>\nuserBalanceDetails.getDetailBalance is null");
        	}
        	userBalanceDetailsService.update(userBalanceDetails);
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteUserBalanceDetails.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteUserBalanceDetails(HttpServletRequest request, HttpServletResponse response) {
    
        result = new PaasResult();
        try {
        	UserBalanceDetails userBalanceDetails = (UserBalanceDetails) EntryUtil.getObject(request, UserBalanceDetails.class);
    
        	userBalanceDetailsService.delete(userBalanceDetails.getId());
        } catch (Exception e) {
            super.exception(request, e);
        }
    
        result.asynchronousPrintResult(response, result.returnResult());
    }
}
