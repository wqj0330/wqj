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
import java.util.UUID;

import me.anchora.inpaasmgr.base.BaseService;
import me.anchora.inpaasmgr.dao.ccdb.AppsMapper;
import me.anchora.inpaasmgr.dao.ccdb.CcUsersMapper;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsAuditorsMapper;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsManagersMapper;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsMapper;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsUsersMapper;
import me.anchora.inpaasmgr.dao.ccdb.SpacesAuditorsMapper;
import me.anchora.inpaasmgr.dao.ccdb.SpacesDevelopersMapper;
import me.anchora.inpaasmgr.dao.ccdb.SpacesManagersMapper;
import me.anchora.inpaasmgr.dao.ccdb.SpacesMapper;
import me.anchora.inpaasmgr.dao.inpaas.PaasUsersMapper;
import me.anchora.inpaasmgr.dao.uaadb.UaaUsersMapper;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.ccdb.Apps;
import me.anchora.inpaasmgr.entry.ccdb.AppsCriteria;
import me.anchora.inpaasmgr.entry.ccdb.CcUsers;
import me.anchora.inpaasmgr.entry.ccdb.CcUsersCriteria;
import me.anchora.inpaasmgr.entry.ccdb.Organizations;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsAuditors;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsCriteria;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsManagers;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsManagersCriteria;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsUsers;
import me.anchora.inpaasmgr.entry.ccdb.Spaces;
import me.anchora.inpaasmgr.entry.ccdb.SpacesAuditors;
import me.anchora.inpaasmgr.entry.ccdb.SpacesCriteria;
import me.anchora.inpaasmgr.entry.ccdb.SpacesDevelopers;
import me.anchora.inpaasmgr.entry.ccdb.SpacesManagers;
import me.anchora.inpaasmgr.entry.ccdb.SpacesManagersCriteria;
import me.anchora.inpaasmgr.entry.inpaas.PaasUsers;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsers;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsersCriteria;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.UaaUsersService;
import me.anchora.inpaasmgr.utils.CloudFoundryClientUtil;
import me.anchora.inpaasmgr.utils.DateUtil;
import me.anchora.inpaasmgr.utils.ListUtil;
import me.anchora.inpaasmgr.utils.SystemUtil;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component(value = "uaaUsersService")
public class UaaUsersServiceImpl extends BaseService implements UaaUsersService {

    private static Logger logger = Logger.getLogger(UaaUsersServiceImpl.class);

    @Autowired
    private UaaUsersMapper uaaUsersMapper;

    @Autowired
    private CcUsersMapper ccUsersMapper;

    @Autowired
    private OrganizationsMapper organizationsMapper;

    @Autowired
    private OrganizationsAuditorsMapper organizationsAuditorsMapper;

    @Autowired
    private OrganizationsManagersMapper organizationsManagersMapper;

    @Autowired
    private OrganizationsUsersMapper organizationsUsersMapper;

    @Autowired
    private SpacesMapper spacesMapper;

    @Autowired
    private SpacesAuditorsMapper spacesAuditorsMapper;

    @Autowired
    private SpacesManagersMapper spacesManagersMapper;

    @Autowired
    private SpacesDevelopersMapper spacesDevelopersMapper;

    @Autowired
    private AppsMapper appsMapper;
    
    @Autowired
    private PaasUsersMapper paasUsersMapper;
    
    public List<UaaUsers> queryUaaUsersByPage(UaaUsersCriteria criteria) {
        return uaaUsersMapper.selectByExample(criteria);
    }

    public Integer queryCount(UaaUsersCriteria criteria) {
        return uaaUsersMapper.countByExample(criteria);
    }

    public List<Map<String, Object>> usersChart(CommonVo commonVo, Locale locale) {
        String days = commonVo.getDays();
        if (days == null || "".equals(days)) {
            logger.info("Parameter 'days' has not been setted. Default value(" + CHART_DAYS + ") has been setted.");
            days = CHART_DAYS;
        }

        List<Date> dayList = new ArrayList<Date>();
        Date date = DateUtil.endOfDay(new Date()); //时间为当天最后时间，即yyyy-MM-dd 23:59:59
        dayList.add(date);
        for (int i = 1; i < Integer.parseInt(days); i++) {
            date = DateUtil.beforeHours(date, 24L);
            dayList.add(date);
        }

        @SuppressWarnings("unchecked")
        List<Date> severalDate = (List<Date>) ListUtil.getSeveralData(dayList, Long.valueOf(commonVo.getPointNum()));
        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
        Map<String, Object> lastMap;
        List<Map<String, Object>> list;
        Integer count;
        String type;
        UaaUsersCriteria criteria;
        Integer active = 0;// 活跃数

        for (Date dateTmp : severalDate) {
            criteria = new UaaUsersCriteria();
            criteria.createCriteria().andCreatedLessThanOrEqualTo(dateTmp);
            type = super.getMsg(MsgEnum.USER_ALL.getCode(), locale);

            if (map.containsKey(type)) {
                list = map.get(type);
            } else {
                list = new ArrayList<Map<String, Object>>();
                map.put(type, list);
            }
            lastMap = new HashMap<String, Object>();
            count = uaaUsersMapper.countByExample(criteria);
            lastMap.put("time", dateTmp);
            lastMap.put("count", count);
            list.add(lastMap);
            
//             //活跃用户增长走势
//            type = super.getMsg(MsgEnum.USER_ACTIVE.getCode(), locale);
//            if (map.containsKey(type)) {
//                list = map.get(type);
//            } else {
//                list = new ArrayList<Map<String, Object>>();
//                map.put(type, list);
//            }
//
//            active = activeCount(dateTmp);
//            lastMap = new HashMap<String, Object>();
//            lastMap.put("time", dateTmp);
//            lastMap.put("count", active);
//            list.add(lastMap);
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
    
    public Integer activeCount(Date dateTmp) {
        Integer uaaUserConut = 0;
        Map<Integer, Map<String, Object>> tmpmap = new HashMap<Integer, Map<String, Object>>();
        Map<Integer, Map<String, Object>> tmp1map = new HashMap<Integer, Map<String, Object>>();
        Map<String, Map<String, Object>> tmp2map = new HashMap<String, Map<String, Object>>();
        Map<String, Map<String, Object>> tmp3map = new HashMap<String, Map<String, Object>>();
        Map<String, Object> appsmap = new HashMap<String, Object>();
        Map<String, Object> spaceManagersmap = new HashMap<String, Object>();
        Map<String, Object> ccUsersmap = new HashMap<String, Object>();
        Map<String, Object> uaaUsersmap = new HashMap<String, Object>();

        AppsCriteria appsCriteria = new AppsCriteria();
        appsCriteria.createCriteria().andCreatedAtLessThanOrEqualTo(dateTmp);
        List<Apps> appsList = appsMapper.selectByExample(appsCriteria);
        for(Apps apps:appsList){
            appsmap.put("space_id", apps.getSpaceId());
            tmpmap.put(apps.getSpaceId(), appsmap);    
        }
        SpacesManagersCriteria spacesManagersCriteria = new SpacesManagersCriteria();
        List<SpacesManagers> spacesManagersList = spacesManagersMapper.selectByExample(spacesManagersCriteria);
        for (SpacesManagers spacesManagers : spacesManagersList) {
            if (tmpmap.containsKey(spacesManagers.getSpaceId())) {
                spaceManagersmap.put("user_id", spacesManagers.getUserId());
                tmp1map.put(spacesManagers.getUserId(), spaceManagersmap);
            }
        }
        CcUsersCriteria ccUsersCriteria = new CcUsersCriteria();
        ccUsersCriteria.createCriteria().andCreatedAtLessThanOrEqualTo(dateTmp);
        List<CcUsers> ccUsersList = ccUsersMapper.selectByExample(ccUsersCriteria);
        for (CcUsers ccUsers : ccUsersList) {
            if (tmp1map.containsKey(ccUsers.getId())) {
                ccUsersmap.put("user_id", ccUsers.getGuid());
                tmp2map.put(ccUsers.getGuid(), ccUsersmap);
            }
        }
        UaaUsersCriteria uaaUsersCriteria = new UaaUsersCriteria();
        uaaUsersCriteria.createCriteria().andCreatedLessThanOrEqualTo(dateTmp);
        List<UaaUsers> uaaUsersList = uaaUsersMapper.selectByExample(uaaUsersCriteria);
        for(UaaUsers uaaUsers : uaaUsersList){
            uaaUsersmap.put("user_id",uaaUsers.getId());
            tmp3map.put(uaaUsers.getId(), uaaUsersmap);
        }
        for(String userId : tmp2map.keySet()){
            if(tmp3map.containsKey(userId)){
                uaaUserConut++;
                }
        }
//        for(int n = 0; n < uaacount; n++){
//            if (tmp2map.containsKey(uaaUsersList.get(n).getId())) {
//                uaaUsersmap.put("user_id", uaaUsersList.get(n).getUsername());
//                tmp3map.put(uaaUsersList.get(n).getUsername(),uaaUsersmap);
//            }
//        }
//        PaasUsersCriteria paasUsersCriteria = new PaasUsersCriteria();
//        paasUsersCriteria.createCriteria().andCreatedAtLessThanOrEqualTo(dateTmp);
//        List<PaasUsers> paasUsersList = paasUsersMapper.selectByExample(paasUsersCriteria);
//        Integer paascount = paasUsersMapper.countByExample(paasUsersCriteria);
//        for(int j=0; j<paascount; j++){
//            if (tmp3map.containsKey(paasUsersList.get(j).getUserEmail())) {
//                uaaUserConut++;
//            }   
//        }
        return uaaUserConut;
    }

    
    public List<UaaUsers> queryUaaUsersByPage(UaaUsersCriteria criteria, RowBounds rowBounds) {

        List<UaaUsers> result = uaaUsersMapper.selectByExampleWithRowbounds(criteria, rowBounds);

        List<CommonVo> commonVoList = ccUsersMapper.selectWithOrgSpace(new CommonVo(), new RowBounds());

        Map<String, CommonVo> map = new HashMap<String, CommonVo>();
        for (CommonVo commonVo : commonVoList) {
            map.put(commonVo.getGuid(), commonVo);
        }

        CommonVo commonVoTmp;
        String quotaOrgspaces;
        for (UaaUsers uaaUsers : result) {
            if (map.containsKey(uaaUsers.getId())) {
                commonVoTmp = map.get(uaaUsers.getId());
                quotaOrgspaces = commonVoTmp.getOrgspace();
                
                String quotaName = "";
                String orgId = "";
                String orgGuid = "";
                String orgSpace = "";
                for(String quotaOrgspace : quotaOrgspaces.split(", ")) {
                    if (quotaOrgspace.split("##").length == 4) {
                        quotaName += quotaOrgspace.split("##")[0] + ",";
                        orgId += quotaOrgspace.split("##")[1] + ",";
                        orgGuid += quotaOrgspace.split("##")[2] + ",";
                        orgSpace += quotaOrgspace.split("##")[3] + ",";
                    } else {
                        orgSpace += quotaOrgspace + ",";
                    }
                }
                
                if(quotaName.length() > 0) {
                    quotaName = quotaName.substring(0, quotaName.length() - 1);
                }
                if(orgId.length() > 0) {
                    orgId = orgId.substring(0, orgId.length() - 1);
                }
                if(orgGuid.length() > 0) {
                    orgGuid = orgGuid.substring(0, orgGuid.length() - 1);
                }
                if(orgSpace.length() > 0) {
                    orgSpace = orgSpace.substring(0, orgSpace.length() - 1);
                }
               
                uaaUsers.setQuotaName(quotaName);
                uaaUsers.setOrgId(orgId);
                uaaUsers.setOrgGuid(orgGuid);
                uaaUsers.setOrgspace(orgSpace);
                uaaUsers.setCcUsersId(commonVoTmp.getId());
            }
        }
        return result;
    }

    public void delete(UaaUsers uaaUsers) {
        if (uaaUsers == null || uaaUsers.getEmail() == null || uaaUsers.getEmail().length() == 0) {
            SystemUtil.throwException(MsgEnum.USERS_10002.getCode());
        }
        
        if (uaaUsers == null || uaaUsers.getUserId() == null) {
            SystemUtil.throwException(MsgEnum.USERS_10006.getCode());
        }

        String paasAdmin = getPaaSAdmin();
        if (paasAdmin.equals(uaaUsers.getEmail())) {
            SystemUtil.throwException(MsgEnum.USERS_10005.getCode());
        }

        if (uaaUsers == null || uaaUsers.getGuid() == null || uaaUsers.getGuid().length() == 0) {
            SystemUtil.throwException(MsgEnum.USERS_10004.getCode());
        }
        
        CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens());
        
        if (uaaUsers != null && uaaUsers.getOrgGuid() != null && uaaUsers.getOrgGuid().length() != 0 && uaaUsers.getOrgId() != null) {
            SpacesManagersCriteria spacesManagersCriteria;
            List<SpacesManagers> spacesManagersList;
            for(String orgId : uaaUsers.getOrgId().split(",")) {
                SpacesCriteria spacesCriteria = new SpacesCriteria();
                spacesCriteria.createCriteria().andOrganizationIdEqualTo(Integer.valueOf(orgId));
                List<Spaces> spacesList = spacesMapper.selectByExample(spacesCriteria);
                for (Spaces space : spacesList) {
                    spacesManagersCriteria = new SpacesManagersCriteria();
                    spacesManagersCriteria.createCriteria().andSpaceIdEqualTo(space.getId()).andUserIdEqualTo(uaaUsers.getUserId());
                    spacesManagersList = spacesManagersMapper.selectByExample(spacesManagersCriteria);
                    
                    if(spacesManagersList != null && spacesManagersList.size() > 0) {
                        try {
                            cloudFoundryClient.deleteSpace(space.getGuid());
                        } catch (Exception e) {
                            logger.info(e);
                            CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
                            cloudFoundryClient.deleteSpace(space.getGuid());
                        }
                    }
                }
            }
            
            OrganizationsManagersCriteria organizationsManagersCriteria;
            List<OrganizationsManagers> organizationsManagersList;
            OrganizationsCriteria organizationsCriteria;
            List<Organizations> organizationsList;
            for(String orgGuid : uaaUsers.getOrgGuid().split(",")) {
                organizationsCriteria = new OrganizationsCriteria();
                organizationsCriteria.createCriteria().andGuidEqualTo(orgGuid);
                organizationsList = organizationsMapper.selectByExample(organizationsCriteria);
                
                if(organizationsList == null || organizationsList.size() == 0) {
                    SystemUtil.throwException(MsgEnum.ORGANIZATIONS_10001.getCode(), new String[] { orgGuid });
                }
                
                organizationsManagersCriteria = new OrganizationsManagersCriteria();
                organizationsManagersCriteria.createCriteria().andOrganizationIdEqualTo(organizationsList.get(0).getId()).andUserIdEqualTo(uaaUsers.getUserId());
                organizationsManagersList = organizationsManagersMapper.selectByExample(organizationsManagersCriteria);
                
                if(organizationsManagersList != null && organizationsManagersList.size() > 0) {
                    try {
                        cloudFoundryClient.deleteOrganization(orgGuid);
                    } catch (Exception e) {
                        logger.info(e);
                        CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
                        cloudFoundryClient.deleteOrganization(orgGuid);
                    }
                }
            }
        }
        
        uaaUsersMapper.deleteByPrimaryKey(uaaUsers.getGuid());
        
        CcUsersCriteria ccUsersCriteria = new CcUsersCriteria();
        ccUsersCriteria.createCriteria().andGuidEqualTo(uaaUsers.getGuid());
        ccUsersMapper.deleteByExample(ccUsersCriteria);
    }

    //创建用户
    public void doRegister(String email, String password, Integer quotaId) {

        if (email == null || email.length() == 0) {
            SystemUtil.throwException(MsgEnum.USERS_10002.getCode());
        }

        if (password == null || password.length() == 0) {
            SystemUtil.throwException(MsgEnum.USERS_10003.getCode());
        }

        if (quotaId == null) {
            SystemUtil.throwException(MsgEnum.QUOTADEFINITIONS_10002.getCode());
        }

        UaaUsersCriteria uaaUsersCriteria = new UaaUsersCriteria();
        uaaUsersCriteria.createCriteria().andEmailEqualTo(email);
        List<UaaUsers> uaaUsersList = uaaUsersMapper.selectByExample(uaaUsersCriteria);
        if (uaaUsersList != null && uaaUsersList.size() > 0) {
            SystemUtil.throwException(MsgEnum.USERS_10001.getCode(), new String[] { email });
        }

        Date now = new Date();
        String userGuid = UUID.randomUUID().toString();
        //创建UaaUsers用户
        UaaUsers uaaUser = new UaaUsers();
        uaaUser.setEmail(email);
        uaaUser.setUsername(email);
        uaaUser.setPassword(encryptPassword(password));
        uaaUser.setId(userGuid);
        uaaUser.setVersion(0L);
        uaaUser.setAuthority(0L);
        uaaUser.setActive(true);
        uaaUser.setAuthorities("uaa.user");
        uaaUser.setCreated(now);
        uaaUser.setLastmodified(now);
        uaaUser.setFamilyname(email);
        uaaUser.setGivenname(email);
        uaaUsersMapper.insert(uaaUser);
        //创建PaasUsers用户
        PaasUsers paasUsers = new PaasUsers();
        paasUsers.setUserEmail(email);
        paasUsers.setUserPwd(encryptPassword(password));
        paasUsers.setUserCfPwd(password);//user_cf_pwd字段不能为空
        paasUsers.setUserPlatform("tiger");//user_platform字段不能为空
        paasUsersMapper.insert(paasUsers);

        Organizations org = new Organizations();
        org.setGuid(UUID.randomUUID().toString());
        org.setCreatedAt(now);
        org.setName(email + "-org");
        org.setBillingEnabled(false);
        org.setQuotaDefinitionId(quotaId);
        org.setStatus("active");
        organizationsMapper.insertSelective(org);
        Integer orgId = org.getId();

        //创建CcUsers用户，UaaUsers的id与CcUsers的guid一一对应
        CcUsers ccUser = new CcUsers();
        ccUser.setGuid(userGuid);//UaaUsers的id与CcUsers的guid一一对应
        ccUser.setActive(true);
        ccUser.setCreatedAt(now);
        ccUser.setAdmin(false);
        ccUsersMapper.insertSelective(ccUser);
        Integer ccUserId = ccUser.getId();

        OrganizationsAuditors organizationsAuditors = new OrganizationsAuditors();
        organizationsAuditors.setUserId(ccUserId);
        organizationsAuditors.setOrganizationId(orgId);
        organizationsAuditorsMapper.insertSelective(organizationsAuditors);

        OrganizationsManagers organizationsManagers = new OrganizationsManagers();
        organizationsManagers.setUserId(ccUserId);
        organizationsManagers.setOrganizationId(orgId);
        organizationsManagersMapper.insertSelective(organizationsManagers);

        OrganizationsUsers organizationsUsers = new OrganizationsUsers();
        organizationsUsers.setUserId(ccUserId);
        organizationsUsers.setOrganizationId(orgId);
        organizationsUsersMapper.insertSelective(organizationsUsers);

        Spaces spaces[] = new Spaces[] { new Spaces(), new Spaces(), new Spaces() };
        spaces[0].setName("开发环境");
        spaces[1].setName("测试环境");
        spaces[2].setName("生产环境");

        Integer spaceId;
        for (int i = 0; i < spaces.length; i++) {
            spaces[i].setGuid(UUID.randomUUID().toString());
            spaces[i].setOrganizationId(orgId);
            spaces[i].setCreatedAt(now);
            spacesMapper.insertSelective(spaces[i]);
            spaceId = spaces[i].getId();

            SpacesAuditors spacesAuditors = new SpacesAuditors();
            spacesAuditors.setUserId(ccUserId);
            spacesAuditors.setSpaceId(spaceId);
            spacesAuditorsMapper.insertSelective(spacesAuditors);

            SpacesManagers spacesManagers = new SpacesManagers();
            spacesManagers.setUserId(ccUserId);
            spacesManagers.setSpaceId(spaceId);
            spacesManagersMapper.insertSelective(spacesManagers);

            SpacesDevelopers spacesDevelopers = new SpacesDevelopers();
            spacesDevelopers.setUserId(ccUserId);
            spacesDevelopers.setSpaceId(spaceId);
            spacesDevelopersMapper.insertSelective(spacesDevelopers);
        }
    }
 //加密方法，BCrypt，是一个跨平台的文件加密工具
    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}