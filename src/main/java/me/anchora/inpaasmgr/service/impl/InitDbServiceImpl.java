/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import me.anchora.inpaasmgr.dao.inpaasmgr.UserBalanceDetailsMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalanceDetailsCriteria;
import me.anchora.inpaasmgr.service.InitDbService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "initDbService")
public class InitDbServiceImpl implements InitDbService {
    
    private static Logger logger = Logger.getLogger(InitDbServiceImpl.class);
    
    @Autowired
    private UserBalanceDetailsMapper userBalanceDetailsMapper;
    //初始化
    public void initDb() {
    	
    	  UserBalanceDetailsCriteria userBalanceDetailsCriteria = new UserBalanceDetailsCriteria();
    	  userBalanceDetailsCriteria.createCriteria();
          try {
        	  //SQL: select count(*) from user_balance_details
        	  // Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'inpaasmgr.user_balance_details' doesn't exist
              Integer userBalanceDetailsCount = userBalanceDetailsMapper.countByExample(userBalanceDetailsCriteria);
            //1.如果user_balance_details表不存在，则在执行以下步骤时代码错误，跳到catch执行,并在logger(e)报出错误： Table 'inpaasmgr.user_balance_details' doesn't exist，并且执行catch中的initTables()，创建user_balance_details表。
            //2.如果user_balance_details表存在且数据为空则执行if语句中内容，执行到if语句中initTables()时发生代码错误，因为user_balance_details表已经存在;跳转到catch执行，并报出错误logger(e)：Table 'user_balance_details' already exists
              if(userBalanceDetailsCount == null || userBalanceDetailsCount == 0) {
          //（userBalanceDetailsCount == null || userBalanceDetailsCount == 0）的意思是user_balance_details表存在，但数据为空/0。
            	  logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                  initTables();
              }
          } catch (Exception e) {
        	  logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              logger.info(e);
              initTables();
          }

    }
    
    private void initTables() {
    	
    	userBalanceDetailsMapper.createUserBalanceDetails();
    	logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	logger.info("自动创建表格成功");
    	userBalanceDetailsMapper.insertUserBalanceDetails();
    	logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	logger.info("自动插入表格数据成功");
    	
    }
//    
//    public void insert() {
//    	
//    	userBalanceDetailsMapper.createUserBalanceDetails();
//    	logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    	logger.info("自动创建表格成功");
//    	userBalanceDetailsMapper.insertUserBalanceDetails();
//    	logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    	logger.info("自动插入表格数据成功");
//    	
//    }
}
