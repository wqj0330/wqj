/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.LoginCriteria;

public interface LoginService {
    public List<Login> queryLoginByPage(LoginCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(LoginCriteria criteria);

    public List<Login> login(LoginCriteria criteria);

    public Integer insert(Login login);

    public void update(Login login);

    public void delete(Login login);

    public void changePwd(Login login);

    public void updateLastLogin(Login loginReturn);

	public List<Login> queryAllUsers(LoginCriteria criteria);
}
