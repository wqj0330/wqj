/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalanceDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalanceDetailsCriteria;

import org.apache.ibatis.session.RowBounds;

public interface UserBalanceDetailsService {
    public void insert(UserBalanceDetails userbalanceDetails);

    public void update(UserBalanceDetails userbalanceDetails);

    public void delete(Long id);

    public List<UserBalanceDetails> queryAllUserBalanceDetails(UserBalanceDetailsCriteria criteria);

    public List<UserBalanceDetails> queryUserBalanceDetailsByPage(UserBalanceDetailsCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(UserBalanceDetailsCriteria criteria);
    //系统赠送
    public void dofree();
    
    //发送邮件
    public void sendEmail();
}
