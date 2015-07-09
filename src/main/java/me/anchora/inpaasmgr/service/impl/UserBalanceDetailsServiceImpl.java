/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.Date;
import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.UserBalanceDetailsMapper;
import me.anchora.inpaasmgr.dao.inpaasmgr.UserBalancesMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalanceDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalanceDetailsCriteria;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalances;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalancesCriteria;
import me.anchora.inpaasmgr.service.UserBalanceDetailsService;
import me.anchora.inpaasmgr.utils.DateUtil;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "userBalanceDetailsService")
public class UserBalanceDetailsServiceImpl implements UserBalanceDetailsService {
	private static Logger logger = Logger.getLogger(UserBalanceDetailsServiceImpl.class);
	@Autowired
	private UserBalanceDetailsMapper userBalanceDetailsMapper;
	
    @Autowired
    private UserBalancesMapper userBalancesMapper;
    
    public List<UserBalanceDetails> queryUserBalanceDetailsByPage(UserBalanceDetailsCriteria criteria, RowBounds rowBounds) {
        List<UserBalanceDetails> result = userBalanceDetailsMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
    }

    public List<UserBalanceDetails> queryAllUserBalanceDetails(UserBalanceDetailsCriteria criteria) {
        List<UserBalanceDetails> result = userBalanceDetailsMapper.selectByExample(criteria);
        return result;
    }
  
    public Integer queryCount(UserBalanceDetailsCriteria criteria) {
        Integer result = userBalanceDetailsMapper.countByExample(criteria);
        return result;
    }

	public void insert(UserBalanceDetails userBalanceDetails)  {
		if(userBalanceDetails.getEmail()!=null&&!"".equals(userBalanceDetails.getEmail())){
			UserBalances userBalances=new UserBalances();
			userBalances.setEmail(userBalanceDetails.getEmail());
			userBalances.setBalanceType("0");
			UserBalances userBalancesTmp;
				 userBalancesTmp = userBalancesMapper.queryByEmail(userBalances);
				if(userBalancesTmp!=null&&userBalancesTmp.getEmail()!=null&&!"".equals(userBalancesTmp.getEmail())){
					Float oldBalance=userBalancesTmp.getBalance();
					Float detailBalance=userBalanceDetails.getDetailBalance();
					Float newBalance=oldBalance+detailBalance;
					userBalancesTmp.setBalance(newBalance);
					userBalancesTmp.setUpdatedAt(new Date());
					userBalancesMapper.updateByPrimaryKey(userBalancesTmp);
						
				}
				else{
					userBalances.setBalance(userBalanceDetails.getDetailBalance());
					userBalances.setCreatedAt(new Date());
					userBalances.setBalanceType("0");
					userBalancesMapper.insert(userBalances);
				   }
				userBalanceDetails.setTradeNo(DateUtil.formatDateHMSbase(new Date()));
				userBalanceDetails.setBalanceType("0");
				userBalanceDetails.setBalanceDate(new Date());
				userBalanceDetails.setBalanceStatus("1");
				 userBalanceDetailsMapper.insert(userBalanceDetails);
        }
    }
    
	public void update(UserBalanceDetails userBalanceDetails) {
    	UserBalanceDetails userBalanceDetailsTmp = userBalanceDetailsMapper.selectByPrimaryKey(userBalanceDetails.getId());
		if(userBalanceDetails.getEmail()!=null&&!"".equals(userBalanceDetails.getEmail())){				
			UserBalances userBalances=new UserBalances();
			userBalances.setEmail(userBalanceDetails.getEmail());
			userBalances.setBalanceType("0");
			UserBalances userBalancesTmp2;
			userBalancesTmp2=userBalancesMapper.queryByEmail(userBalances);
				if(userBalancesTmp2.getEmail()!=null&&!"".equals(userBalancesTmp2.getEmail())){
					Float oldBalance=userBalancesTmp2.getBalance();
					Float olddetailBalance=userBalanceDetailsTmp.getDetailBalance();
					Float newdetailBalance=userBalanceDetails.getDetailBalance();
					Float newBalance=oldBalance-olddetailBalance+newdetailBalance;
					userBalancesTmp2.setBalance(newBalance);
					userBalancesTmp2.setUpdatedAt(new Date());
					userBalancesMapper.updateByPrimaryKey(userBalancesTmp2);
					userBalanceDetailsTmp.setDetailBalance(newdetailBalance);
					userBalanceDetailsMapper.updateByPrimaryKey(userBalanceDetailsTmp);
							
            }
        } 
    }	

	public void delete(Long id)  {
		UserBalanceDetails userBalanceDetails = userBalanceDetailsMapper.selectByPrimaryKey(id);
		if(userBalanceDetails.getEmail()!=null&&!"".equals(userBalanceDetails.getEmail())){
		    UserBalances userBalances = new UserBalances();
		    userBalances.setEmail(userBalanceDetails.getEmail());
		    userBalances.setBalanceType("0");
		    UserBalances userBalancesTmp;
		    userBalancesTmp = userBalancesMapper.queryByEmail(userBalances);
		        if(userBalancesTmp.getEmail()!=null&&!"".equals(userBalancesTmp.getEmail())){
			        Float oldBalance= userBalancesTmp.getBalance();
			        Float detailBalance=userBalanceDetails.getDetailBalance();
			        Float newBalance=oldBalance-detailBalance;
			        userBalancesTmp.setBalance(newBalance);
			        userBalancesTmp.setUpdatedAt(new Date());
			        userBalancesMapper.updateByPrimaryKey(userBalancesTmp);
			       userBalanceDetailsMapper.deleteByPrimaryKey(id);
            }
        } 
    }
	
	//对新注册用户赠送20元（定时任务）
	public void dofree(){
		Date now = new Date();
		Date date = DateUtil.beforeDays(now, Long.valueOf(39));
//		List<UserBalanceDetails> UserBalanceDetailsList;
		List<UserBalances> userBalancesList;
//		UserBalances userBalances;
		UserBalancesCriteria userBalancesCriteria = new UserBalancesCriteria();
		userBalancesCriteria.createCriteria().andCreatedAtBetween(date, now).andBalanceTypeEqualTo("1");
		userBalancesList=userBalancesMapper.selectByExample(userBalancesCriteria);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.info(userBalancesList.get(0));
		for (UserBalances userBalances : userBalancesList) {
//		UserBalanceDetails userBalanceDetails = new UserBalanceDetails();
//		if(userBalanceDetails.getEmail()!=null&&!"".equals(userBalanceDetails.getEmail())){
//			 userBalances=new UserBalances();
			logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			logger.info(userBalances);
			UserBalances userBalancesTmp = new UserBalances();
			userBalancesTmp.setEmail(userBalances.getEmail());
			userBalancesTmp.setBalanceType("0");
			userBalancesTmp.setBalance(Float.parseFloat("20"));
			userBalancesTmp.setCreatedAt(now);
			userBalancesMapper.insert(userBalancesTmp);
		
		}
		}
		
	public void sendEmail(){
		SimpleEmail email = new SimpleEmail();   
	        email.setTLS(true);       
	        email.setHostName("smtp.qq.com");
	        email.setAuthentication("812163445@qq.com", "gouguaichun0215"); //发送方用户名和密码
	        try {   
	            email.addTo("qjwang@anchora.me"); //接受方  
	            email.setFrom("812163445@qq.com"); // 发送方 
	            email.setSubject("Java Mail Test"); //标题  
	            email.setCharset("GBK");   
	            email.setMsg("您的应用由于长时间未使用已被停止，若需要请登录平台重新启动或联系我们，我们给予处理，谢谢！" ); // 内容
	            email.send();   
	  
	       } catch (EmailException e) {   
	           e.printStackTrace();   
	        }
	}
}

