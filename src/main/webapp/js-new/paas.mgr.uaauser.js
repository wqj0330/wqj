function getAllUaaUsersByPage(choosePage){
    var uaaUserName=$('.uaaUserName').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllUaaUsersByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,username:uaaUserName,orderByClause:'created desc'}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.ccUsersId+'</td><td>'+v.username+'</td><td>'+getOrg(v.orgspace) +'</td><td>'+getSpace(v.orgspace) +'</td><td>'+v.quotaName +'</td><td>'+v.created+'</td>'
                    + generateTd(generateButton([{name:'delUaaUser' + replaceAt(v.username), title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generateHidden([{id:'user-delete-guid' + replaceAt(v.username), value:v.id},
                                              {id:'user-delete-orgguid' + replaceAt(v.username), value:v.orgGuid},
                                              {id:'user-delete-orgid' + replaceAt(v.username), value:v.orgId}])
                            + generatePopupbox({id:'delUaaUser' + replaceAt(v.username), title:dictionary['label.DeleteUser'], content:dictionary['label.ConfirmDel'], btnClass:'user-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="UaaUsers_pagination"><ul></ul></div>');
                var totalPages=data.totalPage;
                var id=$('.pagination').attr('id');
                $('.pagination').paging(totalPages,choosePage,id);
                handleTable();
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
            }else{
                alert(data.returnMessage);
            };
        }
    });
}

function getOrg(orgSpace) {
    if(orgSpace == null) {
        return '--';
    }
    var org = '';
    var orgs = orgSpace.split('),');
    $.each(orgs, function(k, v) {
        org += v.split('(')[0] + '<br>';
    });
    
    return org;
}

function getSpace(orgSpace) {
    if(orgSpace == null) {
        return '--';
    }
    var space = '';
    var orgs = orgSpace.split('),');
    $.each(orgs, function(k, v) {
        v = v.replace(')', '');
        space += v.split('(')[1] + '<br>';
    });
    
    return space;
}

$(function(){
    $("#tab_uaausers").click(function(){
        $boxHeader.html($("#tab_uaausers a").text());
        changeMenuClass('tab_uaausers');
        
        var searchForm = [
                          {span:dictionary['label.username'], name:'uaaUserName'}
                          ];
        
        var tableColumns = ['ID',
                            dictionary['label.username'],
                            dictionary['label.Org'],
                            dictionary['label.Space'],
                            dictionary['label.Quota'],
                            dictionary['label.CreateTime'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        
        $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_user">'+dictionary['label.CreateUser']+'</a>');
        $tableContent.html(generatePopupMultibox({id:'create_user' ,
            title:dictionary['label.CreateUser'],
            confirm:dictionary['label.ConfirmCreate'],
            btnClass:'user-add',
            list:[{span:dictionary['label.UserEmail'],name:'create-user-email'},
                  {span:dictionary['label.UserQuota'],name:'create-user-quotaId', type:'select'},
                  {span:dictionary['label.Password'],name:'create-user-password'}]}) + generateTableTitle(tableColumns));

        $.ajax({
            url:"admin/getAllQuotasByPage.html",
            dataType:"json",
            success:function(data) {
                if(data.returnCode == "000"){
                    $.each(data.list, function(k, v){
                        $(".create-user-quotaId").append('<option value="'+v.id+'">'+v.name.value+'</option>');
                    });
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });

        getAllUaaUsersByPage(1);
        return false;
    });
    
    $(".user-add").live("click",function(){
        var email=$(".create-user-email").val();
        var quotaId=$(".create-user-quotaId").val();
        var passwd=$(".create-user-password").val();
        $.ajax({
            type: "POST",
            url:"admin/createUaaUsers.html",
            dataType:"json",
            data:JSON.stringify({email:email,password:passwd,quotaId:quotaId}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllUaaUsersByPage(1);
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
    
    
    $(".user-delete-sure").live("click",function(){
        var nowPage=$("#UaaUsers_pagination ul li.actived").text();
        var email=$(this).parents("tr").find("td").eq(1).text();
        var userid=$(this).parents("tr").find("td").eq(0).text();
        var user=replaceAt(email);
        var guid=$('#user-delete-guid' + user).val();
        var orgguid=$('#user-delete-orgguid' + user).val();
        var orgid=$('#user-delete-orgid' + user).val();
        $.ajax({
            type: "POST",
            url:"admin/deleteUser.html",
            dataType:"json",
            data:JSON.stringify({userId:userid,guid:guid,email:email,orgId:orgid,orgGuid:orgguid}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllUaaUsersByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $('.pop-close').click();
    });

});