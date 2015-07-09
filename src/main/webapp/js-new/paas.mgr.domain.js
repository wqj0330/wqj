function getAllDomainsByPage(choosePage){
    var domainName=$('.domainName').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllDomainsByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,name:domainName,orderByClause:'id desc'}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td><td>'+v.name.value+'</td><td>'+v.createdAt+'</td><td>--</td></tr>';     
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Domains_pagination"><ul></ul></div>');
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
    $("#tab_domain").click(function(){
        changeMenuClass('tab_domain');
        $boxHeader.html($("#tab_domain a").text());
        var searchForm = [
                          {span:dictionary['label.DomainName'], name:'domainName'}
                          ];
        var tableColumns = ['ID',
                            dictionary['label.DomainName'],
                            dictionary['label.CreateTime'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        $tableContent.html(generateTableTitle(tableColumns));
        getAllDomainsByPage(1);
        return false;
    });
    
});