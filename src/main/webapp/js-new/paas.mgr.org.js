function getAllOrgsByPage(choosePage){
    var orgName=$('.orgName').val();
    
    $.ajax({
        type: "POST",
        url:"admin/getAllOrgsByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,name:orgName,orderByClause:'a.id desc'}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td><td>'+v.name.value+'</td><td>'+v.billingEnabled+'</td><td>'+v.quotaDefinitions.name.value+'</td><td>'+v.status+'</td><td>'+v.createdAt+'</td>'
                    + generateTd(generateButton([{name:'delOrg' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generateHidden([{id:'org-delete-guid' + v.id, value:v.guid},
                                              {id:'org-delete-id' + v.id, value:v.id}])
                            + generatePopupbox({id:'delOrg' + v.id, title:dictionary['label.deleteOrg'], content:dictionary['label.ConfirmDel'], btnClass:'org-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Orgs_pagination"><ul></ul></div>');
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
    $("#tab_org").click(function(){
        changeMenuClass('tab_org');
        $boxHeader.html($("#tab_org a").text());
        var searchForm = [
                          {span:dictionary['label.Org'], name:'orgName'}
                          ];
        var tableColumns = ['ID',
                            dictionary['label.Org'],
                            dictionary['label.Billing'],
                            dictionary['label.QuotaName'],
                            dictionary['label.Status'],
                            dictionary['label.CreateTime'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        $tableContent.html(generateTableTitle(tableColumns));

        getAllOrgsByPage(1);
        return false;
    });
    
    $(".org-delete-sure").live("click",function(){
        var nowPage=$("#Orgs_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        var orgid=$('#org-delete-id' + id).val();
        var guid=$('#org-delete-guid' + id).val();
        $.ajax({
            type: "POST",
            url:"admin/deleteOrg.html",
            dataType:"json",
            data:JSON.stringify({guid:guid,id:orgid}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllOrgsByPage(nowPage);
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
