
function getAllUserBalanceDetailsByPage(choosePage){
	var email=$('.email').val();
	var balanceType=$('.balanceType').val();
	var balanceStatus=$('.balanceStatus').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllUserBalanceDetailsByPage.html",
        dataType:"json",
        //orderByClause:'id desc',逆序
        data:JSON.stringify({currentPage:choosePage,email:email,balanceType:balanceType,balanceStatus:balanceStatus,orderByClause:'id desc'}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                	 
                	html+='<tr><td>'+v.id+'</td><td>'+v.email+'</td><td>'+v.tradeNo+'</td><td>'+v.detailBalance+'</td><td>'+v.balanceDate+'</td><td>'+findByKey(v.balanceType,balance.balanceType)+'</td><td>'+findByKey(v.balanceStatus,balance.balanceStatus)+'</td>';
                	
                	html+= generateTd(generateButton([{name:'editUserbalancedetails' + v.id, title:dictionary['label.Modify'], btnclass:'icon-edit'}])
                            + generatePopupMultibox({id:'editUserbalancedetails' + v.id ,
                                title:dictionary['label.ModifyUserbalancedetailsInfo'],
                                confirm:dictionary['label.ConfirmUpdate'],
                                btnClass:'userbalancedetails-update',
                                list:[
                                      {span:dictionary['label.email'],name:'change-userbalancedetails-email',value:v.email},
                                      {span:dictionary['label.DetailBalance'],name:'change-userbalancedetails-detailBalance',value:v.detailBalance},
                                      {name:'change-userbalancedetails-id', type:'hidden', value:v.id}]})
                            +generateButton([{name:'delUserbalancedetails' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generatePopupbox({id:'delUserbalancedetails' + v.id, title:dictionary['label.deleteUserbalancedetails'], content:dictionary['label.Delete'], btnClass:'userbalancedetails-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="UserBalanceDetails_pagination"><ul></ul></div>');
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
    $("#tab_userbalancedetails").click(function(){
        changeMenuClass('tab_userbalancedetails');
        $boxHeader.html($("#tab_userbalancedetails a").text());
        var searchForm = [{span:dictionary['label.email'], name:'email'} 
                         ,{span:dictionary['label.BalanceType'], name:'balanceType', type:'select', list:balance.balanceType}
                         ,{span:dictionary['label.BalanceStatus'], name:'balanceStatus', type:'select', list:balance.balanceStatus}
                          ];
        
        var tableColumns = ['ID',
                            dictionary['label.email'],
                            dictionary['label.TradeNo'],
                            dictionary['label.DetailBalance'],
                            dictionary['label.BalanceDate'],
                            dictionary['label.BalanceType'],
                            dictionary['label.BalanceStatus'],
                            dictionary['label.Operating']];

        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        $boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="create_userbalancedetails">'+dictionary['label.AddUserbalancedetails']+'</a>');
        var create = generatePopupMultibox({id:'create_userbalancedetails' ,
                     title:dictionary['label.AddUserbalancedetails'],
                     confirm:dictionary['label.Confirm'],
                     btnClass:'userbalancedetails-add',
                     list:[{span:dictionary['label.email'],name:'change-userbalancedetails-email'},
                           {span:dictionary['label.DetailBalance'],name:'change-userbalancedetails-detailBalance'}
                     ]});

        $tableContent.html(create+generateTableTitle(tableColumns));
        
        getAllUserBalanceDetailsByPage(1);
        return false;
    });
    
    $(".userbalancedetails-add").live("click",function(){
    	var noEmpty=$(this).parent().parent().find("input");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert']+'');
                return;
            }
        }
        var email=$(this).parent().parent().find(".change-userbalancedetails-email").val();
        var detailBalance=$(this).parent().prev().find(".change-userbalancedetails-detailBalance").val();
        $.ajax({
            type: "POST",
            url:"admin/createUserBalanceDetails.html",
            dataType:"json",
            data:JSON.stringify({email:email,detailBalance:detailBalance}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllUserBalanceDetailsByPage(1);
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        $(this).parent().parent().find("input").val('');
        $('.pop-close').click();
        return false;
    });

    $(".userbalancedetails-update").live("click",function(){
        var noEmpty=$(this).parent().parent().find(".not-empty");
        for (var i=0;i<noEmpty.length;i++) {
            if (noEmpty[i].value==''){
                alert(''+dictionary['label.Alert2']+'');
                return;
            }
        }
        var nowPage=$("#Userbalancedetails_pagination ul li.actived").text();
        var email=$(this).parent().parent().find(".change-userbalancedetails-email").val();
        var detailBalance=$(this).parent().parent().find(".change-userbalancedetails-detailBalance").val();
        var id=$(this).parent().parent().find(".change-userbalancedetails-id").val();
        $.ajax({
            type: "POST",
            url:"admin/updateUserBalanceDetails.html",
            dataType:"json",
            data:JSON.stringify({email:email,detailBalance:detailBalance,id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllUserBalanceDetailsByPage(nowPage);
                }else if (data.returnCode == "0002"){
                    location.href="login.jsp";
                }else{
                    alert(data.returnMessage);
                }
            }
        });
        return false;
    });

    $(".userbalancedetails-delete-sure").live("click",function(){
        var nowPage=$("#Userbalancedetails_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        $.ajax({
            type: "POST",
            url:"admin/deleteUserBalanceDetails.html",
            dataType:"json",
            data:JSON.stringify({id:id}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllUserBalanceDetailsByPage(nowPage);
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
