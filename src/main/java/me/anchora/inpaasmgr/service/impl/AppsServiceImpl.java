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
import me.anchora.inpaasmgr.dao.ccdb.AppsMapper;
import me.anchora.inpaasmgr.dao.ccdb.CcUsersMapper;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsMapper;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsUsersMapper;
import me.anchora.inpaasmgr.dao.ccdb.SpacesMapper;
import me.anchora.inpaasmgr.dao.uaadb.UaaUsersMapper;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.ccdb.Apps;
import me.anchora.inpaasmgr.entry.ccdb.AppsCriteria;
import me.anchora.inpaasmgr.entry.ccdb.CcUsers;
import me.anchora.inpaasmgr.entry.ccdb.CcUsersCriteria;
import me.anchora.inpaasmgr.entry.ccdb.Organizations;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsUsers;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsUsersCriteria;
import me.anchora.inpaasmgr.entry.ccdb.Spaces;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsers;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsersCriteria;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.AppsService;
import me.anchora.inpaasmgr.utils.CloudFoundryClientUtil;
import me.anchora.inpaasmgr.utils.DateUtil;
import me.anchora.inpaasmgr.utils.ListUtil;
import me.anchora.inpaasmgr.utils.SystemUtil;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.cloudfoundry.client.lib.domain.CloudSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "appsService")
public class AppsServiceImpl extends BaseService implements AppsService {
    private static Logger logger = Logger.getLogger(AppsServiceImpl.class);

    @Autowired
    private OrganizationsMapper organizationsMapper;

    @Autowired
    private SpacesMapper spacesMapper;

    @Autowired
    private AppsMapper appsMapper;
    
    @Autowired
    private OrganizationsUsersMapper organizationsUsersMapper;
    
    @Autowired
    private CcUsersMapper ccUsersMapper;

    @Autowired
    private UaaUsersMapper uaaUsersMapper;

    public List<Apps> queryAppsByPage(AppsCriteria criteria) {
        return appsMapper.selectByExample(criteria);
    }

    public Integer queryMem() {
        return appsMapper.queryMem();
    }

    public Integer queryCount(Apps apps) {
        return appsMapper.countWithMany(apps);
    }

    public List<Map<String, Object>> appsCountChart(CommonVo commonVo, Locale locale) {
        String days = commonVo.getDays();
        if (days == null || "".equals(days)) {
            logger.info("Parameter 'days' has not been setted. Default value(" + CHART_DAYS + ") has been setted.");
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
        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
        Map<String, Object> lastMap;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Integer count;
        AppsCriteria criteria;
        String type;

        for (Date dateTmp : severalDate) {
            criteria = new AppsCriteria();
            criteria.createCriteria().andCreatedAtLessThanOrEqualTo(dateTmp).andNotDeletedEqualTo(true);
            type = super.getMsg(MsgEnum.APP_NUM.getCode(), locale);

            if (map.containsKey(type)) {
                list = map.get(type);
            } else {
                list = new ArrayList<Map<String, Object>>();
                map.put(type, list);
            }
            lastMap = new HashMap<String, Object>();
            count = appsMapper.countByExample(criteria);
            lastMap.put("time", dateTmp);
            lastMap.put("count", count);
            list.add(lastMap);
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

    public List<Apps> queryAppsByPage(Apps apps, RowBounds rowBounds) {
        List<Apps> result = appsMapper.selectWithMany(apps, rowBounds);
        return result;
    }

    public void startApps(String appName) {
        CloudSpace cs = preHandle(appName);
        try {
            cloudFoundryClient.startApplication(cs, appName);
        } catch (Exception e) {
            logger.info(e);
            CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
            cloudFoundryClient.startApplication(cs, appName);
        }
    }

    public void stopApps(String appName) {
        CloudSpace cs = preHandle(appName);
        try {
            cloudFoundryClient.stopApplication(cs, appName);
        } catch (Exception e) {
            logger.info(e);
            CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
            cloudFoundryClient.stopApplication(cs, appName);
        }
    }

    public void restartApps(String appName) {
        CloudSpace cs = preHandle(appName);
        try {
            cloudFoundryClient.restartApplication(cs, appName);
        } catch (Exception e) {
            logger.info(e);
            CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
            cloudFoundryClient.restartApplication(cs, appName);
        }
    }

    public void deleteApps(String appName) {
        CloudSpace cs = preHandle(appName);
        try {
            cloudFoundryClient.deleteApplication(cs, appName);
//            cloudFoundryClient.deleteOrphanedRoutes();
        } catch (Exception e) {
            logger.info(e);
            CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
            cloudFoundryClient.deleteApplication(cs, appName);
//            cloudFoundryClient.deleteOrphanedRoutes();
        }
    }

    private CloudSpace preHandle(String appName) {
        AppsCriteria appsCriteria = new AppsCriteria();
        appsCriteria.createCriteria().andNameEqualTo(appName);
        List<Apps> appsList = appsMapper.selectByExample(appsCriteria);

        if (appsList == null || appsList.size() == 0) {
            SystemUtil.throwException(MsgEnum.APPS_10001.getCode(), new String[]{appName});
        }

        Spaces spaces = spacesMapper.selectByPrimaryKey(appsList.get(0).getSpaceId());

        if (spaces == null) {
            SystemUtil.throwException(MsgEnum.SPACES_10001.getCode(), new String[]{appsList.get(0).getSpaceId() + ""});
        }

        Organizations organizations = organizationsMapper.selectByPrimaryKey(spaces.getOrganizationId());

        if (organizations == null) {
            SystemUtil.throwException(MsgEnum.ORGANIZATIONS_10001.getCode(), new String[]{spaces.getOrganizationId() + ""});
        }

        CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens());
        
        CloudSpace cs = CloudFoundryClientUtil.getCloudSpace(spaces, organizations);
        
        return cs;
    }
    
  //若应用停止，则发email给用户通知（定时任务）
  	public void sendEmail(){
      	Date now = new Date();
      	//距离现在1小时的信息
  		Date date = DateUtil.beforeHours(now, Long.valueOf(1));
  		List<Apps> appsList;
  		AppsCriteria appsCriteria = new AppsCriteria();
  		//查询App状态为"STOPPED"且距离现在1小时内的所有Apps的信息
  		appsCriteria.createCriteria().andStateEqualTo("STOPPED").andCreatedAtBetween(date,now);
  		appsList=appsMapper.selectByExample(appsCriteria);
  		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
  		logger.info(appsList);
  		for (Apps apps : appsList) {
  				logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
  				logger.info(apps);
  				List <OrganizationsUsers> organizationsUsersList;
  				OrganizationsUsersCriteria organizationsUsersCriteria = new OrganizationsUsersCriteria();
  				organizationsUsersCriteria.createCriteria().andOrganizationIdEqualTo(apps.getOrganizations().getId());
  				organizationsUsersList=organizationsUsersMapper.selectByExample(organizationsUsersCriteria);
  				logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  				logger.info(organizationsUsersList);
  				for(OrganizationsUsers organizationsUsers : organizationsUsersList){
  					List<CcUsers>  ccUsersList;
  					CcUsersCriteria ccUsersCriteria = new CcUsersCriteria();
  					ccUsersCriteria.createCriteria().andIdEqualTo(organizationsUsers.getUserId());
  					ccUsersList=ccUsersMapper.selectByExample(ccUsersCriteria);
  					logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
  					logger.info(ccUsersList);
  					for(CcUsers ccUsers : ccUsersList){
  						List<UaaUsers>  uaaUsersList;
  						UaaUsersCriteria uaaUsersCriteria = new UaaUsersCriteria();
  						uaaUsersCriteria.createCriteria().andIdEqualTo(ccUsers.getGuid());
  						uaaUsersList=uaaUsersMapper.selectByExample(uaaUsersCriteria);
  						for(UaaUsers uaaUsers : uaaUsersList){
  							logger.info("##########################");
  							logger.info(uaaUsers);
  							//以上就找出了与停止状态的App对应的UaaUser.
  							//接下来是对已找出的符合条件的UaaUser执行发送邮件的操作
  							SimpleEmail email = new SimpleEmail();   
  					        email.setTLS(true);       
  					        email.setHostName("smtp.qq.com");
  					        email.setAuthentication("812163445@qq.com", "gouguaichun0215"); //发送方用户名和密码
  					        try {   
  					            email.addTo(uaaUsers.getEmail()); //接受方  
  					            email.setFrom("812163445@qq.com"); // 发送方 
  					            email.setSubject("Java Mail Test"); //标题  
  					            email.setCharset("GBK");   
  					            email.setMsg("您的应用"+apps.getName()+"由于长时间未使用已被停止，若需要请登录平台重新启动或联系我们，我们给予处理，谢谢！" ); // 内容
  					            email.send();   
  					  
  					       } catch (EmailException e) {   
  					           e.printStackTrace();   
  					        }
  						}
  					}
  				}
  		  }
     }
}
