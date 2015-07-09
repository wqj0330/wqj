/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.base;

import static me.anchora.inpaasmgr.Constants.DEFAULT_TIMEONE;

import javax.servlet.http.HttpServletRequest;

import me.anchora.inpaasmgr.exception.AppException;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.CacheService;
import me.anchora.inpaasmgr.utils.ConfigUtil;
import me.anchora.inpaasmgr.utils.LogUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.RequestUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class BaseController {
	
    public PaasResult result;
    private static Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    protected CacheService cacheService;
    
    @Autowired
    protected MessageSource messageSource;

    public String exception(HttpServletRequest request, Throwable e) {
        LogUtil.exception(logger, e);

        String returnMsg;
        if (e instanceof AppException) {
            returnMsg = ((AppException) e).getMessage(messageSource, RequestUtil.getLocale(request));
            result.setCodeAndMsg(((AppException) e).getMessageKey(), returnMsg);
        } else {
            returnMsg = messageSource.getMessage(MsgEnum.SYS_ERROR.getCode(), null, MsgEnum.SYS_ERROR.getDescription(), RequestUtil.getLocale(request));
            result.setCodeAndMsg(MsgEnum.SYS_ERROR.getCode(), returnMsg);
        }

        return returnMsg;
    }
 
    public String getTimezone(HttpServletRequest request) {
        String timezone = (String)request.getSession().getAttribute("timezone");
        if (timezone == null || timezone.length() == 0) {
            timezone = ConfigUtil.getConfig(cacheService, "timezone");
        }
        if (timezone == null || timezone.length() == 0) {
            logger.info("timezone has not bean configured! Default value " + DEFAULT_TIMEONE + " has been setted.");
            timezone = DEFAULT_TIMEONE;
        }
        return timezone;
    }
}
