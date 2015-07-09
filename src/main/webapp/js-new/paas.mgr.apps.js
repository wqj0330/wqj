function getAllAppsByPage(choosePage){
    var orgName=$('.orgName').val();
    var spaceName=$('.spaceName').val();
    var appName=$('.appName').val();
    var appState=$('.appState').val();
    var appPackageState=$('.appPackageState').val();
    var appObj = new Object();
    var orgObj = new Object();
    var spaceObj = new Object();
    orgObj.name=orgName;
    spaceObj.name=spaceName;
    appObj.currentPage=choosePage;
    appObj.name=appName;
    appObj.state=appState;
    appObj.packageState=appPackageState;
    appObj.organizations=orgObj;
    appObj.spaces=spaceObj;
    appObj.orderByClause='a.id desc';
    $.ajax({
        type: "POST",
        url:"admin/getAllAppsByPage.html",
        dataType:"json",
        data:JSON.stringify(appObj),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    var url = v.url.split('<br>')[0];
                    html+='<tr>'
                        +'<td>'+v.id+'</td>'
                        +'<td><a style="color:#0078ff;" target="_blank" title="'+ url +'" href="http://'+url+'">' + v.name.value + '</a></td>'
                            + '<td title="'+v.organizations.name.value+' '+v.spaces.name.value+'">'+cutValue(v.organizations.name.value, 13)+'<br>'+v.spaces.name.value+'</td>'
                            + '<td>'+v.instances+'/'+v.memory+'M</td>'
                            + '<td>'+v.diskQuota+'M</td>'
                            + '<td>'+v.packageState+'</td>'
                            + '<td>'+v.state+'</td>'
                            + '<td>'+v.stacks.name+'</td>'
                            + '<td>'+v.createdAt+'</td>'
                            + '<td><input type="hidden" value="'+v.name.value+'" />';
                    html+=generateButton([{name:'apps-config' + v.id, title:dictionary['label.appConfig'], btnclass:'config-apps-config icon-set-up-tab'}])
                    	+ generateHidden([{id:'config-apps-config-appguid' + v.id, value:v.guid}])
                    	+ generateHidden([{id:'config-apps-config-appname' + v.id, value:v.name.value}])
                    	+ '<div id="apps-config' + v.id + '" class="pop-out">'
                                    +'<div class="popout-box pop-config">'
                                    + '<h2><a href="#" class="pop-close"><img src="img-new/pop-close.png" /></a>'+ dictionary['label.appConfig'] + '</h2>'
                                    + '<div class="pop-main apps-appConfig-content">'
                                     +'</div>'
                                 +'</div>'
                             +'</div>';
                        if(v.state == 'STARTED') {
                            html+=generateButton([{name:'apps-detail' + v.id, title:dictionary['label.Detail'], btnclass:'show-apps-detail icon-detail'}])
                            + generateHidden([{id:'show-apps-detail-appguid' + v.id, value:v.guid}])
                            + '<div id="apps-detail' + v.id + '" class="pop-out-detail">'
                                    +'<div class="popout-box pop-detail">'
                                    + '<h2><a href="#" class="pop-close"><img src="img-new/pop-close.png" /></a>'+ dictionary['label.appDetail'] + '</h2>'
                                    + '<div class="pop-main apps-detail-content"">'
                                     +'</div>'
                                 +'</div>'
                             +'</div>'
                                
                            +generateButton([{name:'stop-apps' + v.id, title:dictionary['label.Stop'], btnclass:'icon-stop'}])
                            + generatePopupbox({id:'stop-apps' + v.id, 
                                title:dictionary['label.ConfirmStop'], 
                                content:dictionary['label.ConfirmStop'], 
                                btnClass:'apps-stop-sure', 
                                confirm:dictionary['label.Confirm']})
                                
                            +generateButton([{name:'restart-apps' + v.id, title:dictionary['label.Restart'], btnclass:'icon-restart'}])
                            + generatePopupbox({id:'restart-apps' + v.id, 
                                title:dictionary['label.ConfirmRestart'], 
                                content:dictionary['label.ConfirmRestart'], 
                                btnClass:'apps-restart-sure', 
                                confirm:dictionary['label.Confirm']});
                            
                            
                            
                        } else {
                            html+=generateButton([{name:'start-apps' + v.id, title:dictionary['label.Start'], btnclass:'icon-start'}])
                            + generatePopupbox({id:'start-apps' + v.id, 
                                title:dictionary['label.ConfirmStart'], 
                                content:dictionary['label.ConfirmStart'], 
                                btnClass:'apps-start-sure', 
                                confirm:dictionary['label.Confirm']});
                            
                        }
                        
                        html+=generateButton([{name:'delete-apps' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                        + generatePopupbox({id:'delete-apps' + v.id, 
                            title:dictionary['label.ConfirmDel'], 
                            content:dictionary['label.ConfirmDel'], 
                            btnClass:'apps-delete-sure', 
                            confirm:dictionary['label.Confirm']});
                        

                    +'</td>'
                    +'</tr>';       
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Apps_pagination"><ul></ul></div>');
                var totalPages=data.totalPage;
                var id=$('.pagination').attr('id');
                $('.pagination').paging(totalPages,choosePage,id);
                handleTable();
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
            }else{
                alert(data.returnMessage);
            }
        }
    });
}

$(function() {
    $("#tab_app").click(function(){
        changeMenuClass('tab_app');
        $boxHeader.html($("#tab_app a").text());
        
        var searchForm = [
                          {span:dictionary['label.AppName'], name:'appName'},
                          {span:dictionary['label.AppStatus'], name:'appState', type:'select', list:datas.appState},
                          //{span:dictionary['label.CodeStatus'], name:'appPackageState', type:'select', list:datas.appPackageState},
                          {span:dictionary['label.Org'], name:'orgName'},
                          {span:dictionary['label.Space'], name:'spaceName'}
                          ];
        var tableColumns = ['ID',
                            dictionary['label.AppName'],
                            dictionary['label.OrgShort']+'<br>'+ dictionary['label.Space'],
							//'URL',
                            dictionary['label.InstanceMemory'],
                            dictionary['label.Disk'],
                            dictionary['label.Code'],
                            dictionary['label.AppStatus'],
                            'Stack',
                            dictionary['label.CreateTime'],
                            dictionary['label.Operating']
        ];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        $tableContent.html(generateTableTitle(tableColumns));

        getAllAppsByPage(1);
        return false;
    });
    
    $(".apps-start-sure").live("click",function(){
        var nowPage=$("#Apps_pagination ul li.actived").text();
        var appName=$(this).parents("td").children()[0].value;
        $.ajax({
            type: "POST",
            url:"admin/startApp.html",
            dataType:"json",
            data:JSON.stringify({appName:appName}),
            beforeSend: function(xhr){
                $(".pagination").remove();
                $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /><p>'+dictionary['label.Waitting']+'</p></div>');
            },
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllAppsByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    });

    $(".apps-stop-sure").live("click",function(){
        var nowPage=$("#Apps_pagination ul li.actived").text();
        var appName=$(this).parents("td").children()[0].value;
        $.ajax({
            type: "POST",
            url:"admin/stopApp.html",
            dataType:"json",
            data:JSON.stringify({appName:appName}),
            beforeSend: function(xhr){
                $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /><p>'+dictionary['label.Waitting']+'</p></div>');
                $(".pagination").remove();
            },
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllAppsByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    }); 

    $(".apps-restart-sure").live("click",function(){
        var nowPage=$("#Apps_pagination ul li.actived").text();
        var appName=$(this).parents("td").children()[0].value;
        $.ajax({
            type: "POST",
            url:"admin/restartApp.html",
            dataType:"json",
            data:JSON.stringify({appName:appName}),
            beforeSend: function(xhr){
                $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /><p>'+dictionary['label.Waitting']+'</p></div>');
                $(".pagination").remove();
            },
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllAppsByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    });

    $(".apps-delete-sure").live("click",function(){
        var nowPage=$("#Apps_pagination ul li.actived").text();
        var appName=$(this).parents("td").children()[0].value;
        $.ajax({
            type: "POST",
            url:"admin/deleteApp.html",
            dataType:"json",
            data:JSON.stringify({appName:appName}),
            beforeSend: function(xhr){
                $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /><p>'+dictionary['label.Waitting']+'</p></div>');
                $(".pagination").remove();
            },
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllAppsByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    });
    
    $(".show-apps-detail").live("click",function(){
        var $tis=$(this);
        var appid = $(this).parents("tr").find("td").eq(0).text();
        var appguid = $('#show-apps-detail-appguid' + appid).val();
        $.ajax({
            type: "POST",
            //对应VarzController中的admin/getAppsDetail.html
            url:"admin/getAppsDetail.html",
            dataType:"json",
            //CommonVo中的guid的值为appguid（ data:JSON.stringify({guid:appguid})），即值为apps中的guid的值  ？？？
            data:JSON.stringify({guid:appguid}),
            beforeSend: function(xhr){
                $tis.parent().next().next().find(".apps-detail-content").html('<table class="all-table" cellpadding="0" cellspacing="0"><div class="loading"><img src="img-new/loading.gif" /></div></table>');
            },
            success:function(data) {
                if(data.returnCode == "000"){
                        html = '<table class="all-table" cellpadding="0" cellspacing="0">'
                +'<thead><tr>'
                    +'<th>'+dictionary['label.instanceId']+'</th>'
                    +'<th>'+dictionary['label.Status']+'</th>'
                    +'<th>CPU</th>'
                    +'<th>'+dictionary['label.Memory']+'</th>'
                    +'<th>'+dictionary['label.Disk']+'</th>'
                    +'<th>'+dictionary['label.uptime']+'</th>'
                    +'<th>DEA</th>'
                +'</tr></thead>'
                +'<tbody>';
                        $.each(data.list , function(k, v){
                        	//详情(应用详细信息)的字段,AppEvents中的instance_index，VarzServiceImpl中computed_pcpu（appConfigsMapper，appMonitDatasMapper，cfMonitDatasMapper），VarzServiceImpl中used_memory_in_bytes，
                            //VarzServiceImpl中的getAppDetail(String guid)方法中最后return的resultList(resultList.add(resultMap)),resultMap中包含以下属性（instance_index，state，computed_pcpu等等）
                        	//VarzServiceImpl中的getAppDetail(String guid)方法获取到各属性的具体值
                        	html += '<tr><td>'+v.instance_index+'</td><td>'+v.state+'</td><td>'+v.computed_pcpu.toFixed(2)+'%</td><td>'+(v.used_memory_in_bytes/1024/1024).toFixed(2)+'M</td><td>'+(v.used_disk_in_bytes/1024/1024).toFixed(2)+'M</td><td>'+getUptime(v.now-v.state_running_timestamp*1000)+'</td><td>'+v.host+'</td></tr>';
                        });     
                        $tis.parent().next().next().find(".apps-detail-content").html(html + '</tbody></table>');
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    });
    
    $(".config-apps-config").live("click",function(){
        var $tis=$(this);
        var appid = $(this).parents("tr").find("td").eq(0).text();
        var appguid = $('#config-apps-config-appguid' + appid).val();
        var appname = $('#config-apps-config-appname' + appid).val();
        $.ajax({
            type: "POST",
            url:"admin/findAppConfigsByAppGuid.html",
            dataType:"json",
            data:JSON.stringify({appGuid:appguid}),
            beforeSend: function(xhr){
                $tis.parent().next().next().next().find(".apps-appConfig-content").html('<table class="all-table" cellpadding="0" cellspacing="0"><div class="loading"><img src="img-new/loading.gif" /></div></table>');
            },
            success:function(data) {
                if(data.returnCode == "000"){
                	if(data.result.id == null) {
                		html = generateForm({
                			confirm:dictionary['label.confirm'],
                			btnClass:'config-app-config',
                			list:[{span:dictionary['label.appconfig.isautoscaling'],name:'config-isautoscaling',type:'select',list:datas.isautoscaling},
                			      {span:dictionary['label.appconfig.autoscaling.type'],name:'config-autoscaling-type',type:'select',list:datas.autoscalingType},
                			      {span:dictionary['label.appconfig.autoscaling.value'],name:'config-autoscaling-value'},
                			      {span:dictionary['label.appconfig.autoscaling.trigger.type'],name:'config-autoscaling-trigger-type',type:'select',list:datas.autoscalingTriggerType},
                			      {span:dictionary['label.appconfig.autoscaling.trigger.up.value'],name:'config-autoscaling-trigger-up-value'},
                			      {span:dictionary['label.appconfig.autoscaling.trigger.down.value'],name:'config-autoscaling-trigger-down-value'},
                			      {name:'appconfig-appguid', type:'hidden', value:appguid},
                			      {name:'appconfig-appname', type:'hidden', value:appname}
                			      ]});
                	} else {
                		html = generateForm({
                			confirm:dictionary['label.confirm'],
                			btnClass:'config-app-config',
                			list:[{span:dictionary['label.appconfig.isautoscaling'],name:'config-isautoscaling',type:'select',list:datas.isautoscaling,value:data.result.isAutoscaling},
                			      {span:dictionary['label.appconfig.autoscaling.type'],name:'config-autoscaling-type',type:'select',list:datas.autoscalingType,value:data.result.autoscalingType},
                			      {span:dictionary['label.appconfig.autoscaling.value'],name:'config-autoscaling-value',value:data.result.autoscalingValue},
                			      {span:dictionary['label.appconfig.autoscaling.trigger.type'],name:'config-autoscaling-trigger-type',type:'select',list:datas.autoscalingTriggerType,value:data.result.autoscalingTriggerType},
                			      {span:dictionary['label.appconfig.autoscaling.trigger.up.value'],name:'config-autoscaling-trigger-up-value',value:data.result.autoscalingTriggerUpValue},
                			      {span:dictionary['label.appconfig.autoscaling.trigger.down.value'],name:'config-autoscaling-trigger-down-value',value:data.result.autoscalingTriggerDownValue},
                			      {name:'appconfig-appguid', type:'hidden', value:appguid},
                			      {name:'appconfig-appname', type:'hidden', value:appname},
                			      {name:'appconfig-id', type:'hidden', value:data.result.id}]});
                	}
                	$tis.parent().next().next().next().find(".apps-appConfig-content").html(html);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    });

    $(".config-app-config").live("click",function(){
        var id = $(this).parent().parent().find(".appconfig-id").val();
        var appGuid = $(this).parent().parent().find(".appconfig-appguid").val();
        var appName = $(this).parent().parent().find(".appconfig-appname").val();
        var isAutoscaling = $(this).parent().parent().find(".config-isautoscaling option:selected").val();
        var autoscalingType = $(this).parent().parent().find(".config-autoscaling-type option:selected").val();
        var autoscalingValue = $(this).parent().parent().find(".config-autoscaling-value").val();
        var autoscalingTriggerType = $(this).parent().parent().find(".config-autoscaling-trigger-type option:selected").val();
        var autoscalingTriggerUpValue = $(this).parent().parent().find(".config-autoscaling-trigger-up-value").val();
        var autoscalingTriggerDownValue = $(this).parent().parent().find(".config-autoscaling-trigger-down-value").val();
        var data = {appGuid:appGuid,appName:appName,isAutoscaling:isAutoscaling,
        		autoscalingType:autoscalingType,autoscalingValue:autoscalingValue,
        		autoscalingTriggerType:autoscalingTriggerType,autoscalingTriggerUpValue:autoscalingTriggerUpValue,
        		autoscalingTriggerDownValue:autoscalingTriggerDownValue};
        
        if(id != null && id != '' && id != undefined) {
        	data.id=id;
        }
        $.ajax({
            type: "POST",
            url:"admin/createOrUpdateAppConfigs.html",
            dataType:"json",
            data:JSON.stringify(data),
            success:function(data) {
                if(data.returnCode == "000"){
                    $(".pop-close").click();
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });
    
});