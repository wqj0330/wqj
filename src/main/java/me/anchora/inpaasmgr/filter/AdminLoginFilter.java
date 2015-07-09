/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.utils.LogUtil;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.RequestUtil;

import org.apache.log4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;

public class AdminLoginFilter implements Filter {
    private static Logger logger = Logger.getLogger(AdminLoginFilter.class);
    
    private ResourceBundleMessageSource messageSource;

    public AdminLoginFilter() {
        if(messageSource == null) {
            messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("i18n/messages");
        }
    }
    //初始化配置
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
     // 进行权限验证(LoginController.java中request.getSession().setAttribute("login", loginReturn);)
        Login login = (Login) req.getSession().getAttribute("login");//追踪tomcat服务器，获取“login”当前信息
        PaasResult result = new PaasResult();
        try {
            if (login != null) {
                chain.doFilter(request, response);
            } else {
                result.setCodeAndMsg(MsgEnum.LOGIN.getCode(), messageSource.getMessage(MsgEnum.LOGIN.getCode(), null, MsgEnum.LOGIN.getDescription(), RequestUtil.getLocale(req)));
                result.asynchronousPrintResult(res, result.returnResult());
            }
        } catch (Exception e) {
            LogUtil.exception(logger, e);
            result.setCodeAndMsg(MsgEnum.SYS_ERROR.getCode(), messageSource.getMessage(MsgEnum.SYS_ERROR.getCode(), null, MsgEnum.SYS_ERROR.getDescription(), RequestUtil.getLocale(req)));
            result.asynchronousPrintResult(res, result.returnResult());
        }
    }

    public void destroy() {
    }
}
