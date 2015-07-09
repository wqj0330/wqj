function getAllRoleMenusByPage(choosePage){
	var roleName=$('.roleName').val();
    var menuName=$('.menuName').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllRoleMenusByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,roleName:roleName,menuName:menuName}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){	
                	 html+='<tr><td>'+v.id+'</td>';
                     
                     if(v.roles != null) {
                     	html+='<td>'+v.roles.roleName+'</td>';
                     } else {
                     	html+='<td>'+v.roles+'</td>';
                     }
                     
                     if(v.menus != null) {
                     	html+='<td>'+v.menus.menuName+'</td>';
                     } else {
                     	html+='<td>'+v.menus+'</td>';
                     }

                     html+='<td>'+v.hasRight+'</td><td>'+v.createdAt+'</td><td>'+v.updatedAt+'</td><td>'+v.remark+'</td>';
                  
                  // html+='<tr><td>'+v.id+'</td><td>'+v.roleId+'</td><td>'+ v.menuId+'</td><td>'+v.hasRight+'</td><td>'+v.createdAt+'</td><td>'+v.updatedAt+'</td><td>'+v.remark+'</td>';
                    
                    html+= generateTd(generateButton([{name:'editroleMenus' + v.id, title:dictionary['label.Modify'], btnclass:'icon-edit'}])
                            + generatePopupMultibox({id:'editroleMenus' + v.id ,
                                title:dictionary['label.ModifyRoleMenusInfo'],
                                confirm:dictionary['label.ConfirmUpdate'],
                                btnClass:'roleMenus-update',
                                list:[{span:dictionary['label.RoleName'],name:'change-roleMenus-roleId',type:'select', list:allRoles, value:v.roleId},
                                      {span:dictionary['label.MenuName'],name:'change-roleMenus-menuId',type:'select', list:allMenus, value:v.menuId},
                                      {span:dictionary['label.HasRight'],name:'change-roleMenus-hasRight', type:'select', list:hasR, value:v.hasRight},
                                      {name:'change-roleMenus-id', type:'hidden', value:v.id}]})
                            
                            +generateButton([{name:'delRoleMenus' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generatePopupbox({id:'delRoleMenus' + v.id, title:dictionary['label.Delete'], content:dictionary['label.ConfirmDelRoleMenus'], btnClass:'roleMenus-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                    
                    
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="RoleMenus_pagination"><ul></ul></div>');
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

$(function(){
    $("#tab_rolemenus").click(function(){
        changeMenuClass('tab_rolemenus');
        $boxHeader.html($("#tab_rolemenus a").text());
        var searchForm = [
                          {span:dictionary['label.RoleName'], name:'roleName'},
                          {span:dictionary['label.MenuName'], name:'menuName'}
                          ];
        
        var tableColumns = ['ID',
                            dictionary['label.RoleName'],
                            dictionary['label.MenuName'],
                            dictionary['label.HasRight'],
                            dictionary['label.CreatedAt'],
                            dictionary['label.UpdateAt'],
                            dictionary['label.remark'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
            $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_roleMenus">'+dictionary['label.AddRoleMenus']+'</a>');
            var create = generatePopupMultibox({id:'create_roleMenus' ,
                        title:dictionary['label.AddRoleMenus'],
                        confirm:dictionary['label.Confirm'],
                        btnClass:'roleMenus-add',
                        list:[{span:dictionary['label.RoleName'],name:'change-roleMenus-roleId',type:'select', list:allRoles},
                              {span:dictionary['label.MenuName'],name:'change-roleMenus-menuId',type:'select', list:allMenus},
                              {span:dictionary['label.HasRight'],name:'change-roleMenus-hasRight', type:'select', list:hasR}
                        
                        ]});
            
        $tableContent.html(create + generateTableTitle(tableColumns));

        getAllRoleMenusByPage(1);
        return false;
    });
    
    $(".roleMenus-add").live("click",function(){
        var noEmpty=$(this).parent().parent().find("input");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert']+'');
                return;
            }
        }
        var roleId=$(this).parent().parent().find(".change-roleMenus-roleId option:selected").val();
        var menuId=$(this).parent().parent().find(".change-roleMenus-menuId option:selected").val();
        var hasRight=$(this).parent().parent().find(".change-roleMenus-hasRight option:selected").val();
        $.ajax({
            type: "POST",
            url:"admin/createRoleMenus.html",
            dataType:"json",
            data:JSON.stringify({roleId:roleId,menuId:menuId,hasRight:hasRight}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllRoleMenusByPage(1);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $(this).parent().parent().find("input").val('');
        $('.pop-close').click();
    });

    $(".roleMenus-update").live("click",function(){
        var noEmpty=$(this).parent().parent().find("input");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert']+'');
                return;
            }
        }
        var nowPage=$("#RoleMenus_pagination ul li.actived").text();
        var id=$(this).parent().parent().find(".change-roleMenus-id").val();
        var roleId=$(this).parent().parent().find(".change-roleMenus-roleId option:selected").val();
        var menuId=$(this).parent().parent().find(".change-roleMenus-menuId option:selected").val();
        var hasRight=$(this).parent().parent().find(".change-roleMenus-hasRight option:selected").val();
        $.ajax({
            type: "POST",
            url:"admin/updateRoleMenus.html",
            dataType:"json",
            data:JSON.stringify({id:id,roleId:roleId,menuId:menuId,hasRight:hasRight}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllRoleMenusByPage(nowPage);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $('.pop-close').click();
    });

    $(".roleMenus-delete-sure").live("click",function(){
        var nowPage=$("#RoleMenus_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "POST",
            url:"admin/deleteRoleMenus.html",
            dataType:"json",
            data:JSON.stringify({id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllRoleMenusByPage(nowPage);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    });
    
});