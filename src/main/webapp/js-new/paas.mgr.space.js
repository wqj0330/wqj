function getAllSpacesByPage(choosePage){
    var spaceName=$('.spaceName').val();
    $.ajax({
        type: "POST",
        url:"admin/getAllSpacesByPage.html",
        dataType:"json",
        data:JSON.stringify({currentPage:choosePage,name:spaceName,orderByClause:'a.id desc'}),
        beforeSend: function(xhr){
            $tableContent.find("table tbody").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
            $(".pagination").remove();
        },
        success:function(data) {
            var html='';
            if(data.returnCode == "000"){
                $.each(data.list , function(k, v){
                    html+='<tr><td>'+v.id+'</td><td>'+v.name.value+'</td><td>'+v.organizations.name.value+'</td><td>'+v.createdAt+'</td>'
                    + generateTd(generateButton([{name:'delSpace' + v.id, title:dictionary['label.Delete'], btnclass:'icon-delete'}])
                            + generateHidden([{id:'space-delete-guid' + v.id, value:v.guid}])
                            + generatePopupbox({id:'delSpace' + v.id, title:dictionary['label.deleteSpace'], content:dictionary['label.ConfirmDel'], btnClass:'space-delete-sure', confirm:dictionary['label.Confirm']})
                            )
                     + '</tr>';
                });
                $tableContent.find("table tbody").html(html);
                $tableContent.append('<div class="pagination pagination-centered" id="Spaces_pagination"><ul></ul></div>');
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
    $("#tab_space").click(function(){
        $boxHeader.html($("#tab_space a").text());
        changeMenuClass('tab_space');
        var searchForm = [
                          {span:dictionary['label.Space'], name:'spaceName'}
                          ];
        var tableColumns = ['ID',
                            dictionary['label.Space'],
                            dictionary['label.Org'],
                            dictionary['label.CreateTime'],
                            dictionary['label.Operating']];
        showHidden();
        $boxSearch.html(generateSearchForm(searchForm));
        $tableContent.html(generateTableTitle(tableColumns));
        
        getAllSpacesByPage(1);
        return false;
    });
    
    $(".space-delete-sure").live("click",function(){
        var nowPage=$("#Spaces_pagination ul li.actived").text();
        var id=$(this).parents("tr").find("td").eq(0).text();
        var guid=$('#space-delete-guid' + id).val();
        $.ajax({
            type: "POST",
            url:"admin/deleteSpace.html",
            dataType:"json",
            data:JSON.stringify({guid:guid}),
            success:function(data) {
                if(data.returnCode == "000"){
                    getAllSpacesByPage(nowPage);
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