function getAllMenuDetailsByPage(choosePage){
    var menuName=$('.menuName').val();
    var menuValue=$('.menuValue').val();
    var langName=$('.langName').val();
    var remark=$('.remark').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllMenuDetailsByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,menuName:menuName,menuValue:menuValue,langName:langName,remark:remark}),
        beforeSend: function(xhr){
            flag=false;
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td>';
                    
                    if(v.menus != null) {
                    	html+='<td>'+v.menus.menuName+'</td>';
                    } else {
                    	html+='<td>'+v.menus+'</td>';
                    }
                    
                    if(v.menuLanguages != null) {
                    	html+='<td>'+v.menuLanguages.langName+'</td>';
                    } else {
                    	html+='<td>'+v.menuLanguages+'</td>';
                    }

                    html+='<td>'+v.menuValue+'</td><td>'+v.remark+'</td>';
                    
                    html+= generateTd(generateButton([{name:'editMenuDetails' + v.id, title:dictionary['label.Modify'], btnclass:'icon-edit'}])
                            + generatePopupMultibox({id:'editMenuDetails' + v.id ,
                                title:dictionary['label.ModifyMenuDetailsInfo'],
                                confirm:dictionary['label.ConfirmUpdate'],
                                btnClass:'menuDetails-update',
                                list:[{span:dictionary['label.MenuName'],name:'change-menuDetails-menuId', value:v.menuId, type:'select', list: allMenus},
                                      {span:dictionary['label.LangName'],name:'change-menuDetails-langId', value:v.langId, type:'select', list:allMenuLanguages},
                                      {span:dictionary['label.MenuValue'],name:'change-menuDetails-menuValue', value:v.menuValue},
                                      {span:dictionary['label.Remarks'],name:'change-menuDetails-remark', value:v.remark},
                                      {name:'change-menuDetails-id', type:'hidden', value:v.id}]})
                            
                            +generateButton([{name:'delMenuDetails' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generatePopupbox({id:'delMenuDetails' + v.id, title:dictionary['label.deleteMenuDetails'], content:dictionary['label.Delete'], btnClass:'menuDetails-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="MenuDetails_pagination"><ul></ul></div>');
                var totalPages=data.totalPage;
                var id=$('.pagination').attr('id');
                $('.pagination').paging(totalPages,choosePage,id);
                handleTable();
                flag=true;
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
                flag=true;
            }else{
                alert(data.returnMessage);
                flag=true;
            }
        }
    });
}

$(function(){
    $("#tab_menudetails").click(function(){
        changeMenuClass('tab_menudetails');
        $boxHeader.html($("#tab_menudetails a").text());
        var searchForm = [ {span:dictionary['label.MenuName'], name:'menuName'}
        				  ,{span:dictionary['label.LangName'], name:'langName'}
                          ,{span:dictionary['label.MenuValue'], name:'menuValue'}
                          ,{span:dictionary['label.Remarks'], name:'remark'}
                          ];
        var tableColumns = ['ID',
                            dictionary['label.MenuName'],
                            dictionary['label.LangName'],
                            dictionary['label.MenuValue'],
                            dictionary['label.Remarks'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        
        $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_menuDetails">'+dictionary['label.AddMenuDetails']+'</a>');
        $tableContent.html(generatePopupMultibox({id:'create_menuDetails' ,
            title:dictionary['label.AddMenuDetails'],
            confirm:dictionary['label.Confirm'],
            btnClass:'menuDetails-add',
            list:[{span:dictionary['label.MenuName'],name:'change-menuDetails-menuId', type:'select', list: allMenus},
                  {span:dictionary['label.LangName'],name:'change-menuDetails-langId', type:'select', list:allMenuLanguages},
                  {span:dictionary['label.MenuValue'],name:'change-menuDetails-menuValue'},
                  {span:dictionary['label.Remarks'],name:'change-menuDetails-remark'}]}) + generateTableTitle(tableColumns)
                  );

        getAllMenuDetailsByPage(1);
        return false;
    });

    $(".menuDetails-add").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var menuId=$(this).parent().parent().find(".change-menuDetails-menuId").val();
        var langId=$(this).parent().parent().find(".change-menuDetails-langId").val();
        var menuValue=$(this).parent().parent().find(".change-menuDetails-menuValue").val();
        var remark=$(this).parent().parent().find(".change-menuDetails-remark").val();
        var inputs = $(this).parent().parent().find("input");
        $.ajax({
            type: "POST",
            url:"admin/createMenuDetails.html",
            dataType:"json",
            data:JSON.stringify({menuId:menuId,langId:langId,menuValue:menuValue,remark:remark}),
            success:function(data) {
                if(data.returnCode == "000"){
                    $('.pop-close').click();
                    inputs.val('');
                    getAllMenuDetailsByPage(1);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });

    $(".menuDetails-update").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var nowPage=$("#MenuDetails_pagination ul li.actived").text();
        var menuId=$(this).parent().parent().find(".change-menuDetails-menuId").val();
        var langId=$(this).parent().parent().find(".change-menuDetails-langId").val();
        var menuValue=$(this).parent().parent().find(".change-menuDetails-menuValue").val();
        var remark=$(this).parent().parent().find(".change-menuDetails-remark").val();
        var id=$(this).parent().parent().find(".change-menuDetails-id").val();
        $.ajax({
            type: "POST",
            url:"admin/updateMenuDetails.html",
            dataType:"json",
            data:JSON.stringify({menuId:menuId,langId:langId,menuValue:menuValue,remark:remark,id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllMenuDetailsByPage(nowPage);
                    $('.pop-close').click();
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });

    $(".menuDetails-delete-sure").live("click",function(){
        var nowPage=$("#MenuDetails_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "POST",
            url:"admin/deleteMenuDetails.html",
            dataType:"json",
            data:JSON.stringify({id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllMenuDetailsByPage(nowPage);
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