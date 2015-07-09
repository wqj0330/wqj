//平台总览所有数据图取得
function changeTimeUsersCountChart(time, pointNum){
	$.ajax({
		type: "POST",
		url:"admin/usersCountChart.html",
		dataType:"json",
		data:JSON.stringify({days:time,pointNum:pointNum}),
		beforeSend: function(xhr){
			$("#overview_info11_line").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
		},
		success:function(data) {
//			if(data.returnCode == "000"){
//                var dataList = new Array();
//                var dataList1 = new Array();        
//                var datas = new Array();
//                var datas1 = new Array();     
//                $.each(data.list[0].datas , function(k, v){
//                    datas.push([new Date(v.time.slice(0,10)).getTime(), v.count]);
//                    datas[k].value=v.count;
//                });
//                $.each(data.list[1].datas , function(k, v){
//                	datas1.push([new Date(v.time.slice(0,10)).getTime(), v.count]);               
//                	datas[k].value2=v.count;
//				});
//               
//                datas = datas.sort();
//                dataList.push({ name:data.list[0].type, data: datas });
//                dataList1.push({ name:data.list[1].type, data: datas1 });
//               
//                function generateChart(divId, title, yTitle,datas,datas1, pointFormat) {
//                    $("#" + divId).jquerycharts({
//                        chart: {
//                            type: 'spline'
//                        },
//                        title: {
//                            text: '<b>' + title + '</b>',
//                            align: 'left'
//                        },
//                        xAxis: {
//                            type: 'datetime',
//                            dateTimeLabelFormats: {
//                                day: '%m-%e'
//                            },
//                            title: {
//                                text: 'Date'
//                            }
//                        },
//                        yAxis: {
//                            title: {
//                                text: yTitle
//                            },
//                            min: 0
//                        },
//                        tooltip: {
//                            headerFormat: '<b>{series.name}</b><br>',
//                            pointFormat: pointFormat
//                        },
//                        series:[{
//                            name: data.list[0].type,
//                            data: datas
//                        }, {
//                            name: data.list[1].type,
//                            data: datas1
//                        }               
//                            ]
//                      
//                    });
//                }
//                generateChart('overview_info11_line', dictionary['label.RegisterUserGrowth'], 'Num',datas,datas1,'{point.x:%Y-%m-%d}<br>{point.y:.0f}');
//                
//			}else if (data.returnCode == "0002"){
//				location.href="login.jsp";
//			}else{
//				alert(data.returnMessage);
//			}
//		}
//	});
			
			if(data.returnCode == "000"){
			    var dataList = new Array();
                var datas = new Array();
                $.each(data.list[0].datas, function(k, v){
                    datas.push([new Date(v.time.slice(0,10)).getTime(), v.count]);
                });
                datas = datas.sort();
                dataList.push({ name: data.list[0].type, data: datas });

                generateChart('overview_info11_line', dictionary['label.RegisterUserGrowth'], 'Num', dataList, '{point.x:%Y-%m-%d}<br>{point.y:.0f}');
            }else if (data.returnCode == "0002"){
				location.href="login.jsp";
			}else{
				alert(data.returnMessage);
			}
		}
	});		
}

function changeTimeAppsCountChart(time, pointNum){
	$.ajax({
		type: "POST",
		url:"admin/appsCountChart.html",
		dataType:"json",
		data:JSON.stringify({days:time,pointNum:pointNum}),
		beforeSend: function(xhr){
			$("#overview_info12_line").html('<div class="loading"><img src="img-new/loading.gif" /></div>');
		},
		success:function(data) {
			if(data.returnCode == "000"){
			    var dataList = new Array();
                var datas = new Array();
                $.each(data.list[0].datas, function(k, v){
                    datas.push([new Date(v.time.slice(0,10)).getTime(), v.count]);
                });
                datas = datas.sort();
                dataList.push({ name: data.list[0].type, data: datas });

                generateChart('overview_info12_line', dictionary['label.AppGrowthTrend'], 'Num', dataList, '{point.x:%Y-%m-%d}<br>{point.y:.0f}');
            }else if (data.returnCode == "0002"){
				location.href="login.jsp";
			}else{
				alert(data.returnMessage);
			}
		}
	});
}

function changeTime(ths, chooseDays, pointNum) {
   if($(ths).parent().hasClass('changeUserDays')) {
        changeTimeUsersCountChart(chooseDays, pointNum);
    };
    
    if($(ths).parent().hasClass('changeAppsDays')) {
       changeTimeAppsCountChart(chooseDays, pointNum);
    };
    
}

function genernateBar(name) {
	bar = '<div class="chart-title cc"><span class="time-link '+name+'">'
        +'<a class="ovweek time-select" href="#" title="'+dictionary['label.Week-t']+'">'+dictionary['label.Week']+'</a> '
		+'<a class="ovmonth" href="#" title="'+dictionary['label.Month-t']+'">'+dictionary['label.Month']+'</a> '
		+'<a class="ovthreemonth" href="#" title="'+dictionary['label.ThreeMonth-t']+'">'+dictionary['label.ThreeMonth']+'</a> '
		+'<a class="ovsixmonth" href="#" title="'+dictionary['label.SixMonth-t']+'">'+dictionary['label.SixMonth']+'</a> '
		+'<a class="ovyear" href="#" title="'+dictionary['label.Year-t']+'">'+dictionary['label.Year']+'</a>'
		+'</span></div>';
	return bar;
}

function changeClass(ths) {
    $(ths).parent().find(' > a').removeClass('time-select');
    $(ths).addClass('time-select');
}

$(function(){
    $("#tab_overview").click(function(){
		$boxHeader.html($("#tab_overview").text());
        $boxSearch.hide();
        $pageLine.hide();
        $("#tab_overview").parent().parent().parent().find(' > li > ul').hide(500);
        $("#tab_overview").parent().parent().parent().find(' > li > ul > li').removeClass('selected');
        $("#tab_overview").parent().parent().parent().find(' > li').removeClass('roof-select');
        $("#tab_overview").parent().parent().parent().find(' > li > div > img').attr('src', 'img-new/arrow-1.png');
        $("#tab_overview").parent().parent().addClass('root-pandect');
        
		$tableContent.html(
		    '<div id="overview_info1" class="chart-box cc">'
		        
				+'<div class="chart-info">'
					+ genernateBar('changeUserDays')
					+'<div id="overview_info11_line" class="data-info"></div>'
				+'</div>'
				
				+'<div class="chart-info">'
						+ genernateBar('changeAppsDays')
					+'<div id="overview_info12_line" class="data-info"></div>'
				+'</div>'
				
			+'</div>');
		changeTimeUsersCountChart("7");
		changeTimeAppsCountChart("7");
		return false;
	});
	
    $('#tab_overview').trigger("click");
    
	$(".ovweek").live("click",function(){
	    var chooseDays="7";
        var pointNum="7";
        changeClass(this);
        changeTime(this, chooseDays, pointNum);
	});
	
    $(".ovmonth").live("click",function(){
        var chooseDays="30";
        var pointNum="30";
        changeClass(this);
        changeTime(this, chooseDays, pointNum);
    });
        
    $(".ovthreemonth").live("click",function(){
        var chooseDays="90";
        var pointNum="90";
        changeClass(this);
        changeTime(this, chooseDays, pointNum);
    });
    
    $(".ovsixmonth").live("click",function(){
        var chooseDays="180";
        var pointNum="180";
        changeClass(this);
        changeTime(this, chooseDays, pointNum);
    });
    
    $(".ovyear").live("click",function(){
        var chooseDays="365";
        var pointNum="365";
        changeClass(this);
        changeTime(this, chooseDays, pointNum);
    });

});