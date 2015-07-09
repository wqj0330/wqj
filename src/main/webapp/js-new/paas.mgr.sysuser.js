function getAllLoginByPage(choosePage){
    var userName=$('.userName').val();
    var lastLoginIp=$('.lastLoginIp').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllLoginByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,userName:userName,lastLoginIp:lastLoginIp}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td><td>'+v.userName+'</td><td title="'+ v.password +'">'+cutValue(v.password)+'</td><td>'+v.timezone+'</td><td>'+v.createAt+'</td><td>'+v.updateAt+'</td><td>'+v.lastLogin+'</td><td>'+v.lastLoginIp+'</td>';
                    
                    html+= generateTd(generateButton([{name:'editlogin' + v.id, title:dictionary['label.Modify'], btnclass:'icon-edit'}])
                            + generatePopupMultibox({id:'editlogin' + v.id ,
                                title:dictionary['label.ModifyOperator'],
                                confirm:dictionary['label.ConfirmUpdate'],
                                btnClass:'login-update',
                                list:[{span:dictionary['label.username'],name:'change-login-userName', value:v.userName},
                                      {span:dictionary['label.Password'],name:'change-login-password', value:v.password},
                                      {span:dictionary['label.TimeZone'],name:'change-login-timezone', type:'select', list:timez, value:v.timezone},
                                      {name:'change-login-id', type:'hidden', value:v.id}]})
                            
                            +generateButton([{name:'delLogin' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generatePopupbox({id:'delLogin' + v.id, title:dictionary['label.Delete'], content:dictionary['label.ConfirmDelOperator'], btnClass:'login-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                    
                    
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Login_pagination"><ul></ul></div>');
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
    $("#tab_login").click(function(){
        changeMenuClass('tab_login');
        $boxHeader.html($("#tab_login a").text());
        var searchForm = [
                          {span:dictionary['label.username'], name:'userName'}
                          //,{span:dictionary['label.LastAccessIP'], name:'lastLoginIp'}
                          ];
        
        var tableColumns = ['ID',
                            dictionary['label.username'],
                            dictionary['label.Password'],
                            dictionary['label.TimeZone'],
                            dictionary['label.CreateTime'],
                            dictionary['label.UpdateTime'],
                            dictionary['label.LastAccessTime'],
                            dictionary['label.LastAccessIP'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        

                                    

            
            $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_login">'+dictionary['label.AddOperator']+'</a>');
            var create = generatePopupMultibox({id:'create_login' ,
                        title:dictionary['label.AddOperator'],
                        confirm:dictionary['label.Confirm'],
                        btnClass:'login-add',
                        list:[{span:dictionary['label.username'],name:'change-login-userName'},
                              {span:dictionary['label.Password'],name:'change-login-password'},
                              {span:dictionary['label.TimeZone'],name:'change-login-timezone', type:'select', list:timez}
                        
                        ]});
            
        $tableContent.html(create + generateTableTitle(tableColumns));

        getAllLoginByPage(1);
        return false;
    });
    
    $(".login-add").live("click",function(){
        var noEmpty=$(this).parent().parent().find("input");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert']+'');
                return;
            }
        }
        var userName=$(this).parent().parent().find(".change-login-userName").val();
        var password=$(this).parent().parent().find(".change-login-password").val();
        var timezone=$(this).parent().parent().find(".change-login-timezone option:selected").val();
        $.ajax({
            type: "POST",
            url:"admin/insertLogin.html",
            dataType:"json",
            data:JSON.stringify({userName:userName,password:password,timezone:timezone}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllLoginByPage(1);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $(this).parent().parent().find("input").val('');
        $('.pop-close').click();
    });

    $(".login-update").live("click",function(){
        var noEmpty=$(this).parent().parent().find("input");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert']+'');
                return;
            }
        }
        var nowPage=$("#Login_pagination ul li.actived").text();
        var id=$(this).parent().parent().find(".change-login-id").val();
        var userName=$(this).parent().parent().find(".change-login-userName").val();
        var password=$(this).parent().parent().find(".change-login-password").val();
        var timezone=$(this).parent().parent().find(".change-login-timezone option:selected").val();
        $.ajax({
            type: "POST",
            url:"admin/updateLogin.html",
            dataType:"json",
            data:JSON.stringify({id:id,userName:userName,password:password,timezone:timezone}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllLoginByPage(nowPage);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $('.pop-close').click();
    });

    $(".login-delete-sure").live("click",function(){
        var nowPage=$("#Login_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "POST",
            url:"admin/deleteLogin.html",
            dataType:"json",
            data:JSON.stringify({id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllLoginByPage(nowPage);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
    });
    
});
