/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import static me.anchora.inpaasmgr.Constants.CHART_DAYS;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.anchora.inpaasmgr.base.BaseService;
import me.anchora.inpaasmgr.dao.inpaasmgr.UserBalancesMapper;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalances;
import me.anchora.inpaasmgr.entry.inpaasmgr.UserBalancesCriteria;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.UserBalancesService;
import me.anchora.inpaasmgr.utils.DateUtil;
import me.anchora.inpaasmgr.utils.ListUtil;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="userBalancesService")
public class UserBalancesServiceImpl extends BaseService implements UserBalancesService {	
	
	private static Logger logger = Logger.getLogger(UserBalancesServiceImpl.class);
	
    @Autowired
    private UserBalancesMapper userBalancesMapper;
	
	public void insert(UserBalances userBalances) {
		userBalances.setEmail(userBalances.getEmail());
		userBalances.setCreatedAt(new Date());
		userBalancesMapper.insert(userBalances);
	}

	public void update(UserBalances userBalances) {
		userBalances.setUpdatedAt(new Date());
		userBalancesMapper.updateByPrimaryKey(userBalances);
	}

	public void delete(Long id) {
		userBalancesMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	//(UserBalancesCriteria criteria, RowBounds rowBounds)包括分页处理
	public List<UserBalances> queryUserBalancesByPage(UserBalancesCriteria criteria, RowBounds rowBounds) {
	        List<UserBalances> result = userBalancesMapper.selectByExampleWithRowbounds(criteria, rowBounds);
	        return result;
	    }
	@Override
    public Integer queryCount(UserBalancesCriteria criteria) {
	        Integer result = userBalancesMapper.countByExample(criteria);
	        return result;
	    }

	@Override
	// 不包括分页处理，没有RowBounds
	 public List<UserBalances> queryAllUserBalances(UserBalancesCriteria criteria) {
	        List<UserBalances> result = userBalancesMapper.selectByExample(criteria);
	        return result;
	    }
	
	//图表
	@Override
	////CommonVo类中属性
	public List<Map<String, Object>> userBalancesCountChart(CommonVo commonVo,
			Locale locale) {
		//days为CommonVo属性，为paas.overview.js中data:JSON.stringify({days:time,pointNum:pointNum})中的days,值为time,即changeTimeUsersCountChart(time, pointNum)中的time.
		//即触发一周，一月，三月，六月，一年时days分别获得值7，30，90，180，365。
		String days = commonVo.getDays();
		//days为CommonVo类中属性，从前端js获取值
        if (days == null || "".equals(days)) {
            logger.info("Parameter 'days' has not been setted. Default value(" + CHART_DAYS + ") has been setted.");
            //import static me.anchora.inpaasmgr.Constants.CHART_DAYS;
            days = CHART_DAYS;
        }

        List<Date> dayList = new ArrayList<Date>();
        Date date = DateUtil.endOfDay(new Date());
        dayList.add(date);
        for (int i = 1; i < Integer.parseInt(days); i++) {
            date = DateUtil.beforeHours(date, 24L);
            dayList.add(date);
        }

        @SuppressWarnings("unchecked")
        List<Date> severalDate = (List<Date>) ListUtil.getSeveralData(dayList, Long.valueOf(commonVo.getPointNum()));
      //  List<Date> severalDate = (List<Date>) dayList;
        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
        Map<String, Object> lastMap;
        List<Map<String, Object>> list;
        Float count=0f;
        String type;
        UserBalancesCriteria userBalancescriteria;

        for (Date dateTmp : severalDate) {
            userBalancescriteria = new UserBalancesCriteria();
         //   userBalancescriteria.createCriteria().andCreatedAtGreaterThanOrEqualTo(dateTmp);
            //下面查询语句是查询用户的金额总和，包括系统赠送，用户充值等。
            userBalancescriteria.createCriteria().andCreatedAtLessThanOrEqualTo(dateTmp);
            //下面查询语句是查询BalanceType为“1”，即用户充值的金额总和。
        //    userBalancescriteria.createCriteria().andCreatedAtLessThanOrEqualTo(dateTmp).andBalanceTypeEqualTo("1");
            List<UserBalances> userBalancesList = userBalancesMapper.selectByExample(userBalancescriteria);
            //BaseService.java中的getMsg(String code, Locale locale)方法，BALANCE_ALL("BALANCE_ALL", "All balance"),All balance
            //继承BaseService.java中的getMsg(String code, Locale locale)方法，结果return messageSource.getMessage(code, null, MsgEnum.getByCode(code).getDescription(), locale);
            //即对BALANCE_ALL("BALANCE_ALL", "All balance")的code，也就是BALANCE_ALL进行获取国际化信息处理（MessageSource是Spring提供的一个用于获取国际化信息的接口）
            type = super.getMsg(MsgEnum.BALANCE_ALL.getCode(), locale);
            if (map.containsKey(type)) {
            	 //type为"BALANCE_ALL"
                list = map.get(type);
            } else {
                list = new ArrayList<Map<String, Object>>();
                map.put(type, list);
            }
      //      int j=userBalancesList.size()-1;
            if(userBalancesList.size()>0){
            for (int i = 0; i < userBalancesList.size(); i++)
            	   {
                    count += userBalancesList.get(i).getBalance();
             //       lastMap = new HashMap<String, Object>();
             //       lastMap.put("time", userBalancesList.get(i).getCreatedAt());
             //       lastMap.put("count", count);
              //      list.add(lastMap);
                    
            	  }
            
                   lastMap = new HashMap<String, Object>();
               //    lastMap.put("time", userBalancesList.get(userBalancesList.size()-1).getCreatedAt());
               //    logger.info(userBalancesList.size());
                   lastMap.put("time", dateTmp);
                   lastMap.put("count", count);
                   list.add(lastMap);
            }
            else{
            	lastMap = new HashMap<String, Object>();
                lastMap.put("time", dateTmp);
                lastMap.put("count", 0);
                list.add(lastMap);
            }
                    count = 0F;
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapTmp;
        for (String userFlag : map.keySet()) {
            mapTmp = new HashMap<String, Object>();
            mapTmp.put("type", userFlag);
            mapTmp.put("datas", map.get(userFlag));
            result.add(mapTmp);
        }
        return result;
    }
}
