function getAllMenusByPage(choosePage){
    var menuName=$('.menuName').val();
//    var parentMenuName=$('.parentMenuName').val();
//    var remark=$('.remark').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllMenusByPage.html",
        dataType:"json",
//        data:JSON.stringify({currentPage:choosePage,menuName:menuName,parentMenuName:parentMenuName,remark:remark}),
        data:JSON.stringify({currentPage:choosePage,menuName:menuName}),
        beforeSend: function(xhr){
            flag=false;
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td><td>';
                    
                    if(v.parentMenu != null) {
                    	html+=v.parentMenu.menuName;
                    } else {
                    	html+=v.parentMenu;
                    }
                    
                    html+='</td><td>'+v.menuName+'</td><td>'+v.styleClass+'</td><td>'+v.menuItemId+'</td><td>'+v.menuOrder+'</td><td>'+v.remark+'</td>';
                    
                    html+= generateTd(generateButton([{name:'editMenus' + v.id, title:dictionary['label.Modify'], btnclass:'icon-edit'}])
                            + generatePopupMultibox({id:'editMenus' + v.id ,
                                title:dictionary['label.ModifyMenusInfo'],
                                confirm:dictionary['label.ConfirmUpdate'],
                                btnClass:'menus-update',
                                list:[{span:dictionary['label.MenuName'],name:'change-menus-menuName', value:v.menuName},
                                      {span:dictionary['label.ParentMenuName'],name:'change-menus-parentMenuId', value:v.parentMenuId, type:'select', list: allMenus},
                                      {span:dictionary['label.StyleClass'],name:'change-menus-styleClass', value:v.styleClass},
                                      {span:dictionary['label.MenuItemId'],name:'change-menus-menuItemId', value:v.menuItemId},
                                      {span:dictionary['label.MenuOrder'],name:'change-menus-menuOrder', value:v.menuOrder},
                                      {span:dictionary['label.Remarks'],name:'change-menus-remark', value:v.remark},
                                      {name:'change-menus-id', type:'hidden', value:v.id}]})
                            
                            +generateButton([{name:'delMenus' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generatePopupbox({id:'delMenus' + v.id, title:dictionary['label.deleteMenus'], content:dictionary['label.Delete'], btnClass:'menus-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Menus_pagination"><ul></ul></div>');
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
    $("#tab_menus").click(function(){
        changeMenuClass('tab_menus');
        $boxHeader.html($("#tab_menus a").text());
        var searchForm = [ {span:dictionary['label.MenuName'], name:'menuName'}
//                          ,{span:dictionary['label.ParentMenuName'], name:'parentMenuName'}
//                          ,{span:dictionary['label.Remarks'], name:'remark'}
                          ];
        var tableColumns = ['ID',
                            dictionary['label.ParentMenuName'],
                            dictionary['label.MenuName'],
                            dictionary['label.StyleClass'],
                            dictionary['label.MenuItemId'],
                            dictionary['label.MenuOrder'],
                            dictionary['label.Remarks'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        
        $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_menus">'+dictionary['label.AddMenus']+'</a>');
        $tableContent.html(generatePopupMultibox({id:'create_menus' ,
            title:dictionary['label.AddMenus'],
            confirm:dictionary['label.Confirm'],
            btnClass:'menus-add',
            list:[{span:dictionary['label.MenuName'],name:'change-menus-menuName'},
                  {span:dictionary['label.ParentMenuName'],name:'change-menus-parentMenuId', type:'select', list:allMenus},
                  {span:dictionary['label.StyleClass'],name:'change-menus-styleClass'},
                  {span:dictionary['label.MenuItemId'],name:'change-menus-menuItemId'},
                  {span:dictionary['label.MenuOrder'],name:'change-menus-menuOrder'},
                  {span:dictionary['label.Remarks'],name:'change-menus-remark'}]}) + generateTableTitle(tableColumns)
                  );

        getAllMenusByPage(1);
        return false;
    });

    $(".menus-add").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var menuName=$(this).parent().parent().find(".change-menus-menuName").val();
        var parentMenuId=$(this).parent().parent().find(".change-menus-parentMenuId").val();
        var styleClass=$(this).parent().parent().find(".change-menus-styleClass").val();
        var menuItemId=$(this).parent().parent().find(".change-menus-menuItemId").val();
        var menuOrder=$(this).parent().parent().find(".change-menus-menuOrder").val();
        var remark=$(this).parent().parent().find(".change-menus-remark").val();
        var inputs = $(this).parent().parent().find("input");
        $.ajax({
            type: "POST",
            url:"admin/createMenus.html",
            dataType:"json",
            data:JSON.stringify({menuName:menuName,parentMenuId:parentMenuId,styleClass:styleClass,menuItemId:menuItemId,menuOrder:menuOrder,remark:remark}),
            success:function(data) {
                if(data.returnCode == "000"){
                    $('.pop-close').click();
                    inputs.val('');
                    getAllMenus();
                    getAllMenusByPage(1);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });

    $(".menus-update").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var nowPage=$("#Menus_pagination ul li.actived").text();
        var menuName=$(this).parent().parent().find(".change-menus-menuName").val();
        var parentMenuId=$(this).parent().parent().find(".change-menus-parentMenuId").val();
        var styleClass=$(this).parent().parent().find(".change-menus-styleClass").val();
        var menuItemId=$(this).parent().parent().find(".change-menus-menuItemId").val();
        var menuOrder=$(this).parent().parent().find(".change-menus-menuOrder").val();
        var remark=$(this).parent().parent().find(".change-menus-remark").val();
        var id=$(this).parent().parent().find(".change-menus-id").val();
        $.ajax({
            type: "POST",
            url:"admin/updateMenus.html",
            dataType:"json",
            data:JSON.stringify({menuName:menuName,parentMenuId:parentMenuId,styleClass:styleClass,menuItemId:menuItemId,menuOrder:menuOrder,remark:remark,id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllMenusByPage(nowPage);
                    $('.pop-close').click();
                    getAllMenus();
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });

    $(".menus-delete-sure").live("click",function(){
        var nowPage=$("#Menus_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "POST",
            url:"admin/deleteMenus.html",
            dataType:"json",
            data:JSON.stringify({id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllMenusByPage(nowPage);
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