/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.base;

import static me.anchora.inpaasmgr.Constants.ADMIN;

import java.util.Date;
import java.util.Locale;

import me.anchora.inpaasmgr.entry.Accesstokens;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.CacheService;
//import me.anchora.inpaasmgr.service.MopaasMailService;
import me.anchora.inpaasmgr.utils.ConfigUtil;
import me.anchora.inpaasmgr.utils.LogUtil;
import me.anchora.inpaasmgr.utils.SecurityUtil;
import me.anchora.inpaasmgr.utils.SystemUtil;

import org.apache.log4j.Logger;
import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import java.util.TimeZone;
//import me.anchora.inpaasmgr.utils.PropertyUtil;

public class BaseService {
    private static Logger logger = Logger.getLogger(BaseService.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    protected CacheService cacheService;

    @Autowired
    protected CloudFoundryClient cloudFoundryClient;
//    
//    @Autowired
//    protected MopaasMailService mopaasMailService;
    
    private Accesstokens accesstokens;

    protected String getMsg(String code, Locale locale) {
        return messageSource.getMessage(code, null, MsgEnum.getByCode(code).getDescription(), locale);
    }

    protected String getMsg(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, MsgEnum.getByCode(code).getDescription(), locale);
    }

    public BaseService() {
        //TimeZone.setDefault(TimeZone.getTimeZone(PropertyUtil.getProperty("cf.timezone")));
    }

    protected Accesstokens getAccesstokens() {
        return getAccesstokens(false);
    }
    
    protected Accesstokens getAccesstokens(Boolean isFalse) {

        String paasAdmin = getPaaSAdmin();
        String paasAdminPassword = getPaaSAdminPassword();
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info(paasAdmin);
        logger.info(paasAdminPassword);
        CloudCredentials credentials = new CloudCredentials(paasAdmin, SecurityUtil.decrypt(paasAdminPassword));
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info(credentials);
        cloudFoundryClient.setCloudCredentials(credentials);
        
        OAuth2AccessToken oat;
        try {
            if(isFalse || accesstokens == null || accesstokens.getExpiration()== null || accesstokens.getExpiration().before(new Date(new Date().getTime() - 20000))) {
                accesstokens = new Accesstokens();
                oat = cloudFoundryClient.login();
                
                if(!oat.getValue().isEmpty()
                        && !oat.getRefreshToken().getValue().isEmpty()
                        && oat.getExpiration().getTime()>0) {
                    accesstokens.setAccessToken(oat.getValue());
                    accesstokens.setRefreshToken(oat.getRefreshToken().getValue());
                    accesstokens.setExpiration(oat.getExpiration());
                } else {
                    SystemUtil.throwException(MsgEnum.BASE_10002.getCode());
                }
            }
        } catch (Exception e) {
            LogUtil.exception(logger, e);
            SystemUtil.throwException(e, MsgEnum.BASE_10002.getCode());
        }

        return accesstokens;
    }

    protected String getPaaSAdmin() {
        String paasAdmin = ConfigUtil.getConfig(cacheService, "paas_admin");
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info(paasAdmin);
        if (paasAdmin == null || "".equals(paasAdmin)) {
            logger.info("paas_admin has not bean configured! Default value " + ADMIN + " has been setted.");
            paasAdmin = ADMIN;
        }
        return paasAdmin;
        
    }

    protected String getPaaSAdminPassword() {
        String paasAdminPassword = ConfigUtil.getConfig(cacheService, "paas_admin_pwd");
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info(paasAdminPassword);
        if (paasAdminPassword == null || "".equals(paasAdminPassword)) {
            SystemUtil.throwException(MsgEnum.BASE_10001.getCode());
        }
        return paasAdminPassword;
        
    }
    }
