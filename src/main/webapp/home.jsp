<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 判断cookie里的lang是否为空 -->
<c:if test="${!empty cookie.lang}">
<!-- fmt标签库用于国际化处理<fmt:setLocale value="    " />,value表示指定区域，比如zh_CN; -->
     <fmt:setLocale value="${cookie.lang.value}" />
</c:if>
<fmt:setBundle basename="i18n/messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><fmt:message key="title.home"/></title>
<link href="css-main/main.css" rel="stylesheet" type="text/css" />
<link href="css-main/popup.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="favicon.ico">
<script>
    if ('${sessionScope.userName}' == '') {
        window.location.href = "login.jsp";
    }

    function loadOverview() {
        document.getElementById('tab_overview').click();
    }
</script>
</head>

<body onload="loadOverview()">
<div class="header cc">
   <div class="logo">
        <c:choose>
            <c:when test="${cookie.lang.value == 'zh_CN'}">
                <img src="img-new/main-title-logo.png" />
            </c:when>
            <c:when test="${cookie.lang.value == 'en_US'}">
                <img src="img-new/main-title-logo-en.png" />
            </c:when>
            <c:otherwise>
                <img src="img-new/main-title-logo.png" />
            </c:otherwise>
        </c:choose>
   </div>
   
   <div class="users-box">
                        <!-- sessionScope.userName是值取session中的userName -->
       <span class="drop"><i class="icon-user"></i> ${sessionScope.userName}<b class="caret"></b>
          <div class="sig-out" style="display: none;">
          <!-- dropdown-menu下拉菜单 -->
             <ul class="dropdown-menu">
             <!-- LOGOUT_10001（退出系统） 对应LoginController中的"/logout.html"-->
                <li><a href="logout.html"><fmt:message key="LOGOUT_10001"/></a></li>
             </ul>
          </div>
       </span>
       <span class="text-change">
            <c:choose>
                <c:when test="${cookie.lang.value == 'zh_CN'}">
                    <a class="english" href="#">English</a><span class="select"> | 中文</span>
                </c:when>
                <c:when test="${cookie.lang.value == 'en_US'}">
                    <span class="select">English | </span><a class="chinese" href="#">中文</a>
                </c:when>
                <c:otherwise>
                     <a class="english" href="#">English</a> | <a class="chinese" href="#">中文</a>
                </c:otherwise>
            </c:choose>
       </span>
   </div>
</div>


<div class="wrapper">
    <div class="main cc">
        <div class="sidebar" id="sidebar">
        </div>
        <div class="contents">
            <h2 class="h2-man" id="contents-header"></h2>
            <div class="man-search cc" id="contents-search">
            </div>
            <div class="look-chart" id="look-chart" style="display:none;">
            </div>
            <div class="table-box" id="table-box">
           </div>
           <div class="pop-log" style="display:none;">
               <div class="popout-box pop-log-box">
                   <h2><a href="#" class="pop-close" id="pop-log-close"><img src="img-new/pop-close.png" /></a><fmt:message key="label.HostLog"/></h2>
                   <div class="pop-main">
                      <div class="log-content"></div> 
                   </div>
               </div>
           </div>
           <div class="pagination pagination-centered" id="page-line" style="display:none;">
           </div>
        </div>
    </div>
</div>
<div id="mask-div" style="display:none;"></div>
<!-- 把js-new文件下的jquery-1.8.3.min.js导入JSP页面，这样在JSP页面里就可以调用JS里的function -->
<script src="js-new/jquery-1.8.3.min.js"></script>
<script src="js-new/jquery.charts.min.js"></script> <!-- 折线图，饼状图 -->
<script src="js-new/jquery.cookie.min.js"></script>
<jsp:include page="dictionary.jsp" />
<script src="js-new/switch-lan.js"></script>
<script src="js-new/menu-tree.js"></script>
<!-- 登入/刷新home.jsp时自动触发menu.generate.js，生成/显示菜单 -->
<script src="js-new/menu.generate.js"></script> 
<!-- 导入JavaScript文件 -->
<!-- 语法中的src属性指定JavaScript脚本文件所在的URL -->
<script src="js-new/paas.paging.js"></script>
<script src="js-new/paas.popup.js"></script>
<script src="js-new/paas.common.js"></script>
<script src="js-new/paas.mgr.systemconfig.js"></script>
<script src="js-new/paas.mgr.sysuser.js"></script>
<script src="js-new/paas.mgr.userbalances.js"></script>
<script src="js-new/paas.mgr.userbalancedetails.js"></script>
<script src="js-new/paas.overview.js"></script>
<script src="js-new/paas.mgr.roles.js"></script>
<script src="js-new/paas.mgr.menus.js"></script>
<script src="js-new/paas.mgr.menudetails.js"></script>
<script src="js-new/paas.mgr.roleusers.js"></script>
<script src="js-new/paas.mgr.rolemenus.js"></script>
<script src="js-new/paas.mgr.quota.js"></script>
<script src="js-new/paas.mgr.org.js"></script>
<script src="js-new/paas.mgr.space.js"></script>
<script src="js-new/paas.mgr.uaauser.js"></script>
<script src="js-new/paas.mgr.apps.js"></script>
<script src="js-new/paas.mgr.domain.js"></script>
<script>
    $('.function-list').menutree({
        animate: true,
        speed: 500,
        openCallback: function($clickedEl) {},
        closeCallback: function($clickedEl) {}
    });
</script>
<script>
    $(".caret").mouseover(function(){
        if($(".sig-out").is(':hidden')) {
            $(".sig-out").show(500);
        }
    });
    $(".dropdown-menu").mouseout(function(){
        $(".sig-out").hide(500);
    });
</script>
</body>
</html>
<jsp:include page="dictionary.jsp" />
