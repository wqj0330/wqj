package me.anchora.inpaasmgr.service.impl;

import java.util.List;

import me.anchora.inpaasmgr.dao.ccdb.DomainsMapper;
import me.anchora.inpaasmgr.entry.ccdb.Domains;
import me.anchora.inpaasmgr.entry.ccdb.DomainsCriteria;
import me.anchora.inpaasmgr.service.DomainsService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service(value="domainsService")
public class DomainsServiceImpl implements DomainsService {

	@Autowired
    private DomainsMapper domainsMapper;

	public List<Domains> queryDomainsByPage(DomainsCriteria criteria,RowBounds rowBounds) {
		List<Domains> result=domainsMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
        
       
	}

	public Integer queryCount(DomainsCriteria criteria) {
		 return domainsMapper.countByExample(criteria);
	}

}
