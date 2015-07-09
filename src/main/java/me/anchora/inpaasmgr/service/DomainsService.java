package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.ccdb.Domains;
import me.anchora.inpaasmgr.entry.ccdb.DomainsCriteria;

import org.apache.ibatis.session.RowBounds;

public interface DomainsService {
	public List<Domains> queryDomainsByPage(DomainsCriteria criteria,
			RowBounds rowBounds);
	public Integer queryCount(DomainsCriteria criteria);
}
