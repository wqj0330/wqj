package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.service.CcUsersService;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class CcUsersController extends BaseController {

	
	@Autowired
	private CcUsersService ccUsersService;
	
	@RequestMapping(value = "admin/getAllCcUsersByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
	 public void getAllCcUsersByPage(HttpServletRequest request, HttpServletResponse response) {
		result =new PaasResult();
		try {
            CommonVo commonVo = (CommonVo) EntryUtil.getObject(request, CommonVo.class);
            
            PageTool.pageSetting(commonVo, cacheService);
            List<CommonVo> resultList = ccUsersService.selectWithOrgSpace(commonVo, commonVo.getRowBounds());
            Integer count = ccUsersService.countWithOrgSpace(commonVo);

            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, commonVo.getPageSize()));
            result.addCurrentPage(commonVo.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
	}
} 
