package me.anchora.inpaasmgr.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.anchora.inpaasmgr.base.BaseController;
import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.LoginCriteria;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.LoginService;
import me.anchora.inpaasmgr.utils.CriteriaUtil;
import me.anchora.inpaasmgr.utils.EntryUtil;
import me.anchora.inpaasmgr.utils.IpUtils;
import me.anchora.inpaasmgr.utils.Md5;
import me.anchora.inpaasmgr.utils.PaasResult;
import me.anchora.inpaasmgr.utils.PageTool;
import me.anchora.inpaasmgr.utils.SecurityUtil;
import me.anchora.inpaasmgr.utils.SystemUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BaseController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    // "/loginAjax.html"在login.js中配置
    @RequestMapping(value = "/loginAjax.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void loginAjax(HttpServletRequest request, HttpServletResponse response) {
    //	String returnUrl = "home.jsp";
        result = new PaasResult();
        try {
            Login login = (Login) EntryUtil.getObject(request, Login.class);
//        	Login login = new Login();
//			login.setUserName((String)request.getParameter("username"));
//			login.setPassword((String)request.getParameter("password"));
//			login.setValidateCode((String)request.getParameter("validateCode"));
			
            if (login.getUserName() == null || "".equals(login.getUserName())) {
                logger.info(MsgEnum.LOGIN_10001.getDescription());
                SystemUtil.throwException(MsgEnum.LOGIN_10001.getCode());
            }
            if (login.getPassword() == null || "".equals(login.getPassword())) {
                logger.info(MsgEnum.LOGIN_10002.getDescription());
                SystemUtil.throwException(MsgEnum.LOGIN_10002.getCode());
            }
            if (login.getValidateCode() == null || "".equals(login.getValidateCode())) {
                logger.info(MsgEnum.LOGIN_10003.getDescription());
                SystemUtil.throwException(MsgEnum.LOGIN_10003.getCode());
                //request.getSession()代表这是会话级别,getAttribute("属性名称")用于获取属性值,ValidateCodeServlet.java中 session.setAttribute("validateCode", randomCode.toString());
            } else if (!login.getValidateCode().equalsIgnoreCase((String) request.getSession().getAttribute("validateCode"))) {
                // if(!"inpaasmgr".equals(login.getValidateCode())) {
                logger.info(MsgEnum.LOGIN_10004.getDescription());
                SystemUtil.throwException(MsgEnum.LOGIN_10004.getCode());
                // }
            }
            
            login.setPassword(Md5.MD5(login.getPassword()));//加密前台获取（输入）密码          
            
            LoginCriteria criteria = new LoginCriteria();
            //前台获取（输入）的用户名（login.getUserName()）及密码（login.getPassword()）
            criteria.createCriteria().andUserNameEqualTo(login.getUserName()).andPasswordEqualTo(login.getPassword());
           //在数据库中查询前台获取（输入）的用户名（login.getUserName()）及密码（login.getPassword()）在数据库中存在且匹配的所有数据
            List<Login> loginList = loginService.login(criteria);

            if (loginList == null || loginList.size() == 0) {
                logger.info(MsgEnum.LOGIN_10005.getDescription());
                SystemUtil.throwException(MsgEnum.LOGIN_10005.getCode());
            } else {
                Login loginReturn = loginList.get(0);
                loginReturn.setLastLogin(loginReturn.getCurrentLogin());
                loginReturn.setCurrentLogin(new Date());
                loginReturn.setUpdateAt(loginReturn.getCurrentLogin());
                loginReturn.setLastLoginIp(loginReturn.getCurrentLoginIp());
                loginReturn.setCurrentLoginIp(IpUtils.getRemoteIpAddr(request));
                loginService.updateLastLogin(loginReturn);

                logger.info(login.getUserName() + " Login Success!");
                
                request.getSession().setAttribute("errorInfo", null);
                request.getSession().setAttribute("login", loginReturn);//进入时把“login”信息设为loginReturn
                request.getSession().setAttribute("userName", loginReturn.getUserName());
                request.getSession().setAttribute("timezone", loginReturn.getTimezone());
                request.getSession().setAttribute("locale", loginReturn.getLocale());
         //       return returnUrl;
            }
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    //    return returnUrl;
    }

    @RequestMapping(value = "/logout.html", method = { RequestMethod.POST, RequestMethod.GET })
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        String returnUrl = "login.jsp"; //退出则跳到登录页面login.jsp
        try {
            logger.info(request.getSession().getAttribute("userName") + " Logout Success!");
            request.getSession().setAttribute("errorInfo", null);
            request.getSession().setAttribute("login", null);//退出时把“login”信息设为空
            request.getSession().setAttribute("userName", null);
            request.getSession().setAttribute("timezone", null);
            request.getSession().setAttribute("locale", null);
            returnUrl = "login.jsp";
        } catch (Exception e) {
            super.exception(request, e);
        }

        return returnUrl;
    }

    @RequestMapping(value = "/admin/getAllLoginByPage.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllLoginByPage(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
            Login login = (Login) EntryUtil.getObject(request, Login.class);
            LoginCriteria criteria = (LoginCriteria)CriteriaUtil.createCriteria(login, LoginCriteria.class);
            
            PageTool.pageSetting(login, cacheService);
            List<Login> resultList = loginService.queryLoginByPage(criteria, login.getRowBounds());
            Integer count = loginService.queryCount(criteria);

            result.addList(resultList);
            result.addTotalPage(PageTool.pageCount(count, login.getPageSize()));
            result.addCurrentPage(login.getCurrentPage());
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/getAllUsers.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void getAllUsers(HttpServletRequest request, HttpServletResponse response) {

        result = new PaasResult();
        try {
        	Login login = (Login) EntryUtil.getObject(request, Login.class);
        	LoginCriteria criteria = (LoginCriteria)CriteriaUtil.createCriteria(login, LoginCriteria.class);

            List<Login> resultList = loginService.queryAllUsers(criteria);

            result.addList(resultList, getTimezone(request));
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
    
    @RequestMapping(value = "/admin/insertLogin.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void insert(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            Login login = (Login) EntryUtil.getObject(request, Login.class);
            loginService.insert(login);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/updateLogin.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void update(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            Login login = (Login) EntryUtil.getObject(request, Login.class);
            loginService.update(login);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/deleteLogin.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            Login login = (Login) EntryUtil.getObject(request, Login.class);
            login.setUserName((String) request.getSession().getAttribute("userName"));
            loginService.delete(login);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }

    @RequestMapping(value = "/admin/changePwd.html", method = { RequestMethod.POST, RequestMethod.GET })
    public void changePwd(HttpServletRequest request, HttpServletResponse response) {
        result = new PaasResult();
        try {
            Login login = (Login) EntryUtil.getObject(request, Login.class);
            login.setUserName((String) request.getSession().getAttribute("userName"));
            loginService.changePwd(login);
        } catch (Exception e) {
            super.exception(request, e);
        }

        result.asynchronousPrintResult(response, result.returnResult());
    }
}
