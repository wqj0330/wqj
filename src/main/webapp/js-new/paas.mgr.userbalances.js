function getAllUserBalancesByPage(choosePage){
    var balanceType=$('.balanceType').val();
    var email=$('.email').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllUserBalancesByPage.html",
        dataType:"json",
        //orderByClause:'id desc',逆序
        data:JSON.stringify({currentPage:choosePage,balanceType:balanceType,email:email,orderByClause:'id desc'}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                	 
                    html+='<tr><td>'+v.id+'</td><td>'+v.email+'</td><td>'+v.balance+'</td><td>'+findByKey(v.balanceType,balance.balanceType)+'</td><td>'+v.createdAt+'</td><td>'+v.updatedAt+'</td><td>'+v.remark+'</td>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="UserBalances_pagination"><ul></ul></div>');
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
    $("#tab_userbalances").click(function(){
        changeMenuClass('tab_userbalances');
        $boxHeader.html($("#tab_userbalances a").text());
        var searchForm = [{span:dictionary['label.BalanceType'], name:'balanceType', type:'select', list:balance.balanceType}
                          ,{span:dictionary['label.email'], name:'email'}                      
                          ];
        
        var tableColumns = ['ID',
                            dictionary['label.email'],
                            dictionary['label.Balance'],
                            dictionary['label.BalanceType'],
                            dictionary['label.CreatedAt'],
                            dictionary['label.UpdateAt'],
                            dictionary['label.remark']];

        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));

        $tableContent.html(generateTableTitle(tableColumns));
        
		$boxHeader.append('<a class="btn btn-popbutton add-btn-a" data-popup-id="export">'+dictionary['label.ExportCSV']+'</a>');
	    $tableContent.html(generatePopupbox({id:'export', title:dictionary['label.ExportCSV'], btnClass:'export-sure', confirm:dictionary['label.Confirm']})
	    		+generateHiddenExportForm()+generateTableTitle(tableColumns));

        
        getAllUserBalancesByPage(1);
        return false;
    });
    
    function generateHiddenExportForm(){
		 var formTable = '<form id="exportID" style="display:none"  method="post" action="admin/exportUserBalancesCSV.html">';		
		  formTable+='<input type="hidden" name="balanceType" id="balanceType" value="">';
		  formTable+='<input type="hidden" name="email" id="email" value="">';
		  formTable+='</form>';
		  return formTable;
	}
	
	$(".export-sure").live("click",function(){
	    var balanceType=$('.balanceType').val();
	    var email=$('.email').val();
	    $("#balanceType").val(balanceType);
	    $("#email").val(email);
	    $("#exportID").submit();
	    $('.pop-close').click();
	});
    
});
