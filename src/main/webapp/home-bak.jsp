<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${!empty cookie.lang}">
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
       <span class="drop"><i class="icon-user"></i> ${sessionScope.userName}<b class="caret"></b>
          <div class="sig-out" style="display: none;">
             <ul class="dropdown-menu">
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
            <ul class="function-list">
               <li class="roof">
                  <div class="warp">
                     <span class="fun-bgcolor"><i class="icon-roof"></i></span>
                     <a id="tab_overview" href="#" class="aa"><fmt:message key="label.PlatformOverview"/></a>
                  </div>
               </li>
               <li class="roof">
			      <div class="warp">
				      <span class="fun-bgcolor"><i class="icon-manage"></i></span>
				      <a href="#" class="aa"><fmt:message key="label.PlatformManagement"/></a>
					  <img style="float:right;" src="img-new/arrow-1.png" />
			      </div>
			      <ul class="manage-list">
					  
				     <li id="tab_host_monit"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.PlatformMonitoring"/></a></li>
				     <li id="tab_job_monit"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.jobManagement"/></a></li>

				     <li id="tab_quota"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.quotaManagement"/></a></li>
				     <li id="tab_org"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.orgManagement"/></a></li>
				     <li id="tab_space"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.sapceManagement"/></a></li>
				     <li id="tab_uaausers"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.userManagement"/></a></li>
				     <li id="tab_app"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.appsManagement"/></a></li>
				     <li id="tab_domain"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.domainsManagement"/></a></li>
				     <li id="tab_service"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.servicesManagement"/></a></li>
				     <li id="tab_service_binding"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.servicesBindingManagement"/></a></li>
			      </ul>
               </li>
               <li class="roof">
                  <div class="warp">
                     <span class="fun-bgcolor"><i class="icon-system"></i></span>
                     <a href="#" class="aa"><fmt:message key="label.SystemSettings"/></a>
					 <img style="float:right;" src="img-new/arrow-1.png" />
                  </div>
                   <ul class="manage-list">
					 <li id="tab_config"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.SystemConfiguration"/></a></li>
					 <li id="tab_login"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.OperatorManagement"/></a></li>
				   </ul>
               </li>
            </ul>
        </div>
        <div class="contents">
            <h2 class="h2-man" id="contents-header"></h2>
            <div class="man-search cc" id="contents-search">
            </div>
            <div class="look-chart" id="look-chart" style="display:none;">
            </div>
            <div class="table-box" id="table-box">
           </div>
           <div class="pagination pagination-centered" id="page-line" style="display:none;">
           </div>
        </div>
    </div>

</div>

<script src="js-new/jquery-1.8.3.min.js"></script>
<script src="js-new/jquery.charts.min.js"></script>
<script src="js-new/jquery.cookie.min.js"></script>
<script src="js-new/switch-lan.js"></script>
<script src="js-new/menu-tree.js"></script>
<script src="js-new/paas.paging.js"></script>
<script src="js-new/paas.popup.js"></script>
<script src="js-new/paas.common.js"></script>
<script src="js-new/paas.overview.js"></script>
<script src="js-new/paas.mgr.host.monit.js"></script>
<script src="js-new/paas.mgr.job.monit.js"></script>
<script src="js-new/paas.mgr.quota.js"></script>
<script src="js-new/paas.mgr.org.js"></script>
<script src="js-new/paas.mgr.space.js"></script>
<script src="js-new/paas.mgr.uaauser.js"></script>
<script src="js-new/paas.mgr.apps.js"></script>
<script src="js-new/paas.mgr.domain.js"></script>
<script src="js-new/paas.mgr.service.js"></script>
<script src="js-new/paas.mgr.servicebinding.js"></script>
<script src="js-new/paas.mgr.systemconfig.js"></script>
<script src="js-new/paas.mgr.sysuser.js"></script>
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
