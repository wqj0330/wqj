$(function(){
	//$.cookie("username"); 读取保存在cookie中名为的username的值
	if ($.cookie("username")&&$.cookie("password")){
		//$("#username")对应login.jsp中<input id="username" .../>中的“username”,即记录登录用户名及密码为保存在cookie中名为的username以及password的值
		//val()是返回属性value对应的值。比如<input type="text" id="userName" value="123" />，使用$("#userName").val()就得到了"123"。
		$("#username").val($.cookie("username"));
		$("#password").val($.cookie("password"));
		//记录最新登录用户名和密码
		$("#rem")[0].checked = true;
	}
	//id="resrc",(login.jsp中)刷新验证码
	$("#resrc").click(function(){
		$(this).attr("src", "validateCodeServlet?dt=" + Math.random());
	});
	//国际化
	$('.login-box').bind('keypress',function(event){
        if(event.keyCode == "13"){
            $("#login").click();
        }
    });
	//id="login"(login.jsp中),登录触发
	$("#login").click(function(){
		//id="rem"(login.jsp中)，记录用户名和密码
		if ($("#rem")[0].checked){
         //$.cookie("username","admin"); 将值"admin"写入cookie名为username的cookie中;expires:有限日期，可以是一个整数或一个日期(单位：天)
			$.cookie("username", $("#username").val(),{path:'/',expires:10});
			$.cookie("password", $("#password").val(),{path:'/',expires:10});
		}else{
			// $.cookie("username",NULL);　　　销毁名称为username的cookie
			$.cookie("username", null,{path:'/'});
			$.cookie("password", null,{path:'/'});
		}
		$.ajax({
			type: "POST",
			url:"loginAjax.html",
			dataType:"json",
			//在此处设置了Login中的属性userName的值为login.jsp中id="username"的值（即前端输入的值），同理，Login中属性password,validateCode获取的值分别为输入的前端输入的值
			//"#username"为login.jsp中id="username"，userName则映射Login中属性（字段）username
			//userName:$("#username").val() 解释：val()是返回属性value对应的值。比如<input type="text" id="username" value="123" />，使用$("#username").val()就得到了"123"
			data:JSON.stringify({userName:$("#username").val(),password:$("#password").val(),validateCode:$("#validateCode").val()}),
			success:function(data) {
				if(data.returnCode == "000"){
					//登录成功，则跳转到home.jsp
					location.href="home.jsp";
				}else{
					alert(data.returnMessage);
				}
			}
		});
	});
	
});	
	