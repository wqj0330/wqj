function getAllRoleUsersByPage(choosePage){
    var roleName=$('.roleName').val();
    var userName=$('.userName').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllRoleUsersByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,roleName:roleName,userName:userName}),
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
                    
                    if(v.roles != null) {
                    	html+='<td>'+v.roles.roleName+'</td>';
                    } else {
                    	html+='<td>'+v.roles+'</td>';
                    }
                    if(v.users != null) {
                    	html+='<td>'+v.users.userName+'</td>';
                    } else {
                    	html+='<td>'+v.users+'</td>';
                    }
                    
                    html+='<td>'+v.createdAt+'</td><td>'+v.updatedAt+'</td><td>'+v.remark+'</td>';
                    html+= generateTd(generateButton([{name:'editRoleUsers' + v.id, title:dictionary['label.Modify'], btnclass:'icon-edit'}])
                            + generatePopupMultibox({id:'editRoleUsers' + v.id ,
                                title:dictionary['label.ModifyRoleUsersInfo'],
                                confirm:dictionary['label.ConfirmUpdate'],
                                btnClass:'roleUsers-update',
                                list:[{span:dictionary['label.RoleName'],name:'change-roleUsers-roleId',type:'select', list:allRoles, value:v.roleId},
                                      {span:dictionary['label.username'],name:'change-roleUsers-userId',type:'select', list:allUsers, value:v.userId},
                                      {name:'change-roleUsers-id', type:'hidden', value:v.id}]})
                            +generateButton([{name:'delRoleUsers' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generatePopupbox({id:'delRoleUsers' + v.id, title:dictionary['label.deleteRoleUsers'], content:dictionary['label.Delete'], btnClass:'roleUsers-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="RoleUsers_pagination"><ul></ul></div>');
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
    $("#tab_roleusers").click(function(){
        changeMenuClass('tab_roleusers');
        $boxHeader.html($("#tab_roleusers a").text());
        var searchForm = [
                        {span:dictionary['label.RoleName'], name:'roleName'},
                        {span:dictionary['label.username'], name:'username'}
                           ];
       var tableColumns = ['ID',
                           dictionary['label.RoleName'],
                           dictionary['label.username'],
                           dictionary['label.CreatedAt'],
                           dictionary['label.UpdateAt'],
                           dictionary['label.remark'],
                           dictionary['label.Operating']];
 
       showHidden();
       $boxSearch.html(generateSearchForm(searchForm));
           $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_roleUsers">'+dictionary['label.AddRoleUsers']+'</a>');
           var create = generatePopupMultibox({id:'create_roleUsers' ,
                       title:dictionary['label.AddRoleUsers'],
                       confirm:dictionary['label.Confirm'],
                       btnClass:'roleUsers-add',
                       list:[{span:dictionary['label.RoleName'],name:'change-roleUsers-roleId',type:'select', list:allRoles},
                             {span:dictionary['label.username'],name:'change-roleUsers-userId',type:'select', list:allUsers}
                       ]});
           
       $tableContent.html(create + generateTableTitle(tableColumns));

       getAllRoleUsersByPage(1);
       return false;
   });
     
  //角色用户管理_增加角色用户
    $(".roleUsers-add").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var roleId=$(this).parent().parent().find(".change-roleUsers-roleId").val();
        var userId=$(this).parent().parent().find(".change-roleUsers-userId").val();
        $.ajax({
            type: "POST",
            url:"admin/createRoleUsers.html",
            dataType:"json",
            data:JSON.stringify({roleId:roleId,userId:userId}),
            success:function(data){
            	if(data.returnCode == "000"){
                    getAllRoleUsersByPage(1);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $(this).parent().parent().find("input").val('');
        $('.pop-close').click();
    });

    //角色用户管理_更新角色用户
    $(".roleUsers-update").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var nowPage=$("#RoleUsers_pagination ul li.actived").text();
        var roleId=$(this).parent().parent().find(".change-roleUsers-roleId").val();
        var userId=$(this).parent().parent().find(".change-roleUsers-userId").val();
        var id=$(this).parent().parent().find(".change-roleUsers-id").val();
        $.ajax({
            type: "POST",
            url:"admin/updateRoleUsers.html",
            dataType:"json",
            data:JSON.stringify({roleId:roleId,userId:userId,id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllRoleUsersByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });

    //角色用户管理_删除角色用户用户
    $(".roleUsers-delete-sure").live("click",function(){
        var nowPage=$("#RoleUsers_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "POST",
            url:"admin/deleteRoleUsers.html",
            dataType:"json",
            data:JSON.stringify({id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllRoleUsersByPage(nowPage);
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