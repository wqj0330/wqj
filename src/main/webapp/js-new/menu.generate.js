$(function(){
    $.ajax({
        type: "POST",
    //生成菜单，在home.jsp中配置， 登入/刷新home.jsp时自动触发menu.generate.js，生成/显示菜单 -->
        url:"admin/getMenus.html",
        dataType:"json",
		async:false,
		//是取session里面的username给这个username赋值
        data:JSON.stringify({username:'${sessionScope.userName}'}),
        beforeSend: function(xhr){
            $sidebar.html('<div class="loading"><img src="img-new/loading.gif" /></div>');
        },
        success:function(data) {
            var html='<ul class="function-list">';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
//v.styleClass(可用图标，对应icon-roof，对应MenusServiceImpl中firstLevelMenu.put("styleClass", menu.getStyleClass())中的"styleClass")
                	//'<li class="roof"><div class="warp"><span class="fun-bgcolor"><i class="'+v.styleClass+'"></i></span><a '
                	//则可参见项目test4jxxy中的home.jsp中的
//                	<li class="roof">
//                    <div class="warp">
//                    <span class="fun-bgcolor"><i class="icon-roof"></i></span>
                    html += '<li class="roof"><div class="warp"><span class="fun-bgcolor"><i class="'+v.styleClass+'"></i></span><a ';
                    //一级菜单v.subMenus为空或未知
                    if((v.subMenus == undefined || v.subMenus.length == 0) && v.menuItemId != null) {
//v.menuItemId（菜单ID，对应tab_(),对应firstLevelMenu.put("menuItemId", menu.getMenuItemId())中的“menuItemId”）
//即home.jsp中的<a id="tab_（）" （或其他，反正是一级菜单的firstLevelMenu）       
                    	html += 'id="'+v.menuItemId+'"';
                    }
                //即home.jsp中的href="#" class="aa"><fmt:message key="label.（一级菜单名）"/></a>';
                //v.title(菜单名，对应MenusServiceImpl中firstLevelMenu.put("title", menu.getMenuDetails().getMenuValue()中的"title")
                //v.title包括国际化，因为menu.getMenuDetails().getMenuValue()有中英文两个值
                    html += ' href="#" class="aa">'+v.title+'</a>';
                    if(v.subMenus != undefined && v.subMenus.length > 0) {
                        html += '<img style="float:right;" src="img-new/arrow-1.png" />';
                    }
                    html += '</div>';
                    //二级菜单v.subMenus不为空
                    if(v.subMenus != undefined && v.subMenus.length > 0) {
                  //home.jsp中的<ul class="manage-list">
                        html += '<ul class="manage-list">';
                        $.each(v.subMenus , function(i, j){
//j.menuItemId（菜单ID，对应tab_(),二级菜单的，对应secondryLevelMenu.put("menuItemId", menu.getMenuItemId())中的“menuItemId”）  
//j.title（菜单名，对应lab.()，对应secondryLevelMenu.put("title", menu.getMenuDetails().getMenuValue())中的“title”）            
//'<li id="'+j.menuItemId+'"><span class="man-bgcolor"></span><a href="#">'+j.title+'</a></li>'对应home.jsp中的<li id="tab_（）"><span class="man-bgcolor"></span><a href="#"><fmt:message key="label.（）"/></a></li>
                        	html += '<li id="'+j.menuItemId+'"><span class="man-bgcolor"></span><a href="#">'+j.title+'</a></li>';
                        });
                        
                        html += '</ul>';
                    }
                    
                    html += '</li>';
                });
                
                html += '</ul>';
                $sidebar.html(html);
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
            }else{
                alert(data.returnMessage);
            }
        }
    });

    $('.function-list').menutree({
        animate: true,
        speed: 500,
//      jQuery制作左侧折叠伸缩菜单导航特效
        openCallback: function($clickedEl) {},
        closeCallback: function($clickedEl) {}
    });
});