package me.anchora.inpaasmgr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.ccdb.Domains;
import me.anchora.inpaasmgr.entry.ccdb.DomainsCriteria;
import me.anchora.inpaasmgr.service.DomainsService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DomainsController extends BaseController{
	
	@Autowired
	private DomainsService domainsService;
	
	@RequestMapping(value = "/admin/getAllDomainsByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
	 public void getAllDomainsByPage(HttpServletRequest request, HttpServletResponse response) {
		result =new PaasResult();
		try {
            Domains domains = (Domains) EntryUtil.getObject(request, Domains.class);
            DomainsCriteria criteria = (DomainsCriteria)CriteriaUtil.createCriteria(domains, DomainsCriteria.class);
            PageTool.pageSetting(domains, cacheService);
            List<Domains> resultList = domainsService.queryDomainsByPage(criteria, domains.getRowBounds());
            Integer count = domainsService.queryCount(criteria);

            result.addList(resultList, getTimezone(request));
            result.addTotalPage(PageTool.pageCount(count, domains.getPageSize()));
            result.addCurrentPage(domains.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
	}
	
	
	

}
