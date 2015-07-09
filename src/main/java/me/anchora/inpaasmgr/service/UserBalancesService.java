/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalances;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalancesCriteria;

import org.apache.ibatis.session.RowBounds;

public interface UserBalancesService {

    public List<UserBalances> queryAllUserBalances(UserBalancesCriteria criteria);

    public Integer queryCount(UserBalancesCriteria criteria);

	public List<UserBalances> queryUserBalancesByPage(UserBalancesCriteria criteria, RowBounds rowBounds);
	//图表
	public List<Map<String, Object>> userBalancesCountChart(CommonVo commonVo, Locale locale);
    
}
