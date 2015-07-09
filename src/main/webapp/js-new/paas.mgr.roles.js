function getAllRolesByPage(choosePage){
    var roleName=$('.roleName').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllRolesByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,roleName:roleName}),
        beforeSend: function(xhr){
            flag=false;
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td><td title="'+v.roleName+'">'+cutValue(v.roleName, 30)+'</td><td>'+v.createdAt+'</td><td>'+v.updatedAt+'</td><td>'+v.remark+'</td>';
                    
                    html+= generateTd(generateButton([{name:'editRoles' + v.id, title:dictionary['label.Modify'], btnclass:'icon-edit'}])
                            + generatePopupMultibox({id:'editRoles' + v.id ,
                                title:dictionary['label.ModifyRolesInfo'],
                                confirm:dictionary['label.ConfirmUpdate'],
                                btnClass:'roles-update',
                                list:[{span:dictionary['label.RoleName'],name:'change-roles-roleName', value:v.roleName},
                                      {name:'change-roles-id', type:'hidden', value:v.id}]})
                            +generateButton([{name:'delRoles' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generatePopupbox({id:'delRoles' + v.id, title:dictionary['label.deleteRoles'], content:dictionary['label.Delete'], btnClass:'roles-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Roles_pagination"><ul></ul></div>');
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
    $("#tab_roles").click(function(){
        changeMenuClass('tab_roles');
        $boxHeader.html($("#tab_roles a").text());
        var searchForm = [
                        {span:dictionary['label.RoleName'], name:'roleName'}
                           ];
       var tableColumns = ['ID',
                           dictionary['label.RoleName'],
                           dictionary['label.CreatedAt'],
                           dictionary['label.UpdateAt'],
                           dictionary['label.remark'],
                           dictionary['label.Operating']];
 
       showHidden();
       $boxSearch.html(generateSearchForm(searchForm));
       $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_roles">'+dictionary['label.AddRoles']+'</a>');
          var create = generatePopupMultibox({id:'create_roles' ,
                       title:dictionary['label.AddRoles'],
                       confirm:dictionary['label.Confirm'],
                       btnClass:'roles-add',
                       list:[{span:dictionary['label.RoleName'],name:'change-roles-roleName'}
                       
                       ]});
           
       $tableContent.html(create + generateTableTitle(tableColumns));

       getAllRolesByPage(1);
       return false;
   });
    
    $(".roles-add").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var roleName=$(this).parent().parent().find(".change-roles-roleName").val();
        $.ajax({
            type: "POST",
            url:"admin/createRoles.html",
            dataType:"json",
            data:JSON.stringify({roleName:roleName}),
            success:function(data){
            	if(data.returnCode == "000"){
                    getAllRolesByPage(1);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $(this).parent().parent().find("input").val('');
        $('.pop-close').click();
    });

    $(".roles-update").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var nowPage=$("#Roles_pagination ul li.actived").text();
        var roleName=$(this).parent().parent().find(".change-roles-roleName").val();
        var id=$(this).parent().parent().find(".change-roles-id").val();
        $.ajax({
            type: "POST",
            url:"admin/updateRoles.html",
            dataType:"json",
            data:JSON.stringify({roleName:roleName,id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllRolesByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });

    $(".roles-delete-sure").live("click",function(){
        var nowPage=$("#Roles_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "POST",
            url:"admin/deleteRoles.html",
            dataType:"json",
            data:JSON.stringify({id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllRolesByPage(nowPage);
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