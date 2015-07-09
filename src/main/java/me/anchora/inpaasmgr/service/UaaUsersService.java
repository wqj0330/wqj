/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsers;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsersCriteria;

import org.apache.ibatis.session.RowBounds;

public interface UaaUsersService {
    public List<UaaUsers> queryUaaUsersByPage(UaaUsersCriteria criteria, RowBounds rowBounds);

    public List<UaaUsers> queryUaaUsersByPage(UaaUsersCriteria criteria);

    public Integer queryCount(UaaUsersCriteria criteria);

    public List<Map<String, Object>> usersChart(CommonVo commonVo, Locale locale);

    public void delete(UaaUsers uaaUsers);

    public void doRegister(String email, String password, Integer quotaId);

}
