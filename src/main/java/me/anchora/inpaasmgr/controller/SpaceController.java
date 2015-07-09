package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.ccdb.Spaces;
import me.anchora.inpaasmgr.service.SpacesService;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpaceController extends BaseController{
	
	@Autowired
	private SpacesService spacesService;
	
	@RequestMapping(value = "/admin/getAllSpacesByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
	 public void getAllSpacesByPage(HttpServletRequest request, HttpServletResponse response) {
		result =new PaasResult();
		try {
            Spaces spaces = (Spaces) EntryUtil.getObject(request, Spaces.class);
           // SpacesCriteria criteria = (SpacesCriteria)CriteriaUtil.createCriteria(spaces, SpacesCriteria.class);
            
            PageTool.pageSetting(spaces, cacheService);
            List<Spaces> resultList = spacesService.querySpacesByPage(spaces, spaces.getRowBounds());
            Integer count = spacesService.queryCount(spaces);

            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, spaces.getPageSize()));
            result.addCurrentPage(spaces.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
	}
    
    @RequestMapping(value = "/admin/deleteSpace.html", method = { RequestMethod.POST, RequestMethod.GET })
     public void deleteSpace(HttpServletRequest request, HttpServletResponse response) {
        result =new PaasResult();
        try {
            Spaces spaces = (Spaces) EntryUtil.getObject(request, Spaces.class);
            
            spacesService.deleteSpace(spaces);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}
