function getAllQuotasByPage(choosePage){
    var quotaName=$('.quotaName').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllQuotasByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,name:quotaName}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td><td>'+v.name.value+'</td><td>'+v.createdAt+'</td><td>'+v.totalServices+'</td><td>'+v.memoryLimit+'</td><td>'+v.totalRoutes+'</td><td>--</td></tr>';     
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Quotas_pagination"><ul></ul></div>');
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
    $("#tab_quota").click(function(){
        changeMenuClass('tab_quota');
        $boxHeader.html($("#tab_quota a").text());
        var searchForm = [
                          {span:dictionary['label.QuotaName'], name:'quotaName'}
                           ];
       var tableColumns = ['ID',
                        dictionary['label.QuotaName'],
                        dictionary['label.CreateTime'],
                        dictionary['label.ServiceCount'],
                        dictionary['label.MemoryCount'],
                        dictionary['label.RouterCount'],
                        dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        $tableContent.html(generateTableTitle(tableColumns));
   
        getAllQuotasByPage(1);
        return false;
    });
});

