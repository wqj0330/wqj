/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.Date;
import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.LoginMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.LoginCriteria;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.LoginService;
import me.anchora.inpaasmgr.utils.Md5;
import me.anchora.inpaasmgr.utils.SystemUtil;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<Login> queryLoginByPage(LoginCriteria criteria, RowBounds rowBounds) {
        List<Login> result = loginMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
    }

    @Override
    public Integer queryCount(LoginCriteria criteria) {
        Integer result = loginMapper.countByExample(criteria);
        return result;
    }

    @Override
    public List<Login> login(LoginCriteria criteria) {
        List<Login> result = loginMapper.selectByExample(criteria);
        return result;
    }

    @Override
    public Integer insert(Login login) {
        login.setCreateAt(new Date());
        login.setPassword(Md5.MD5(login.getPassword()));
        Integer result = loginMapper.insert(login);
        return result;
    }

    @Override
    public void update(Login login) {
        Login loginTmp = loginMapper.selectByPrimaryKey(login.getId());
        loginTmp.setUserName(login.getUserName());
        loginTmp.setUpdateAt(new Date());
        loginTmp.setPassword(Md5.MD5(login.getPassword()));
        loginTmp.setTimezone(login.getTimezone());
        loginMapper.updateByPrimaryKey(loginTmp);
    }

    @Override
    public void updateLastLogin(Login login) {
        loginMapper.updateByPrimaryKey(login);
    }

    @Override
    public void delete(Login login) {
        Login loginTmp = loginMapper.selectByPrimaryKey(login.getId());
        if (loginTmp.getUserName().equals(login.getUserName())) {
            SystemUtil.throwException(MsgEnum.LOGIN_10006.getCode());//LOGIN_10006：不能删除你自己
        }
        loginMapper.deleteByPrimaryKey(login.getId());
    }

    @Override
    public void changePwd(Login login) {
        login.setOldPassword(Md5.MD5(login.getOldPassword()));
        LoginCriteria criteria = new LoginCriteria();
        criteria.createCriteria().andUserNameEqualTo(login.getUserName()).andPasswordEqualTo(login.getOldPassword());
        List<Login> loginList = loginMapper.selectByExample(criteria);
        if (loginList == null || loginList.size() == 0) {
            SystemUtil.throwException(MsgEnum.LOGIN_10007.getCode());
        }

        Login loginTmp = loginList.get(0);
        loginTmp.setPassword(Md5.MD5(login.getNewPassword()));
        loginTmp.setUpdateAt(new Date());
        loginMapper.updateByPrimaryKey(loginTmp);
    }
    
    public List<Login> queryAllUsers(LoginCriteria criteria) {
        List<Login> result = loginMapper.selectByExample(criteria);
        return result;
    }
}
