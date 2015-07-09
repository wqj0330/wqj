package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsers;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsersCriteria;
import me.anchora.inpaasmgr.service.UaaUsersService;
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
public class UaaUsersController extends BaseController {
	
    @Autowired
    private UaaUsersService uaaUsersService;

    @RequestMapping(value = "/admin/getAllUaaUsersByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllUsersByPage(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            UaaUsers uaaUsers = (UaaUsers) EntryUtil.getObject(request, UaaUsers.class);
            
            UaaUsersCriteria criteria = (UaaUsersCriteria) CriteriaUtil.createCriteria(uaaUsers, UaaUsersCriteria.class);

            PageTool.pageSetting(uaaUsers, cacheService);
            List<UaaUsers> resultList = uaaUsersService.queryUaaUsersByPage(criteria, uaaUsers.getRowBounds());
            Integer count = uaaUsersService.queryCount(criteria);

            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, uaaUsers.getPageSize()));
            result.addCurrentPage(uaaUsers.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/createUaaUsers.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void createUaaUsers(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);

            uaaUsersService.doRegister(commonVo.getEmail(), commonVo.getPassword(), commonVo.getQuotaId());

        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteUser.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            UaaUsers uaaUsers = (UaaUsers) EntryUtil.getObject(request, UaaUsers.class);

            uaaUsersService.delete(uaaUsers);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}
