var $boxHeader=$("#contents-header"),
$boxSearch=$("#contents-search"),
$lookChart=$("#look-chart"),
$chartBox=$("#chart-box"),
$pageLine=$("#page-line"),
$lookChart=$("#look-chart"),
$tableContent=$("#table-box"),
$sidebar=$("#sidebar"),
datas = {'status':[{'key':'started', 'value':'started'}, {'key':'stopped', 'value':'stopped'}],
        'appState':[{'key':'STARTED', 'value':'STARTED'}, {'key':'STOPPED', 'value':'STOPPED'}],
        'appPackageState':[{'key':'STAGED', 'value':'STAGED'}, {'key':'PENDING', 'value':'PENDING'}, {'key':'FAILED', 'value':'FAILED'}],
        'noticeShow':[{'key':'t', 'value':dictionary['Show']}, {'key':'f', 'value':dictionary['NoShow']}],
        'emailStatus':[{'key':'0', 'value':dictionary['Unsent']}, {'key':'1', 'value':dictionary['Send']}],
        'isVirtual':[{'key':'0', 'value':dictionary['Yes']}, {'key':'1', 'value':dictionary['No']}],
        'balanceType':[{'key':'0','value':dictionary['BALANCETYPE_SYS_PRESENTED']},{'key':'1','value':dictionary['BALANCETYPE_USER_CHARGING']},{'key':'2','value':dictionary['BALANCETYPE_SHOP_INCOME']}],
	'isautoscaling':[{'key':'0', 'value':'No'}, {'key':'1', 'value':'Yes'}],
        'autoscalingType':[{'key':'0', 'value':dictionary['label.autoscalingType.vertical']}, {'key':'1', 'value':dictionary['label.autoscalingType.horizontal']}],
        'autoscalingTriggerType':[{'key':'0', 'value':'CPU'}, {'key':'1', 'value':'Memory'}, {'key':'2', 'value':'Auto'}],
        'invitecodeType':[{'key':'0', 'value':dictionary['label.invitecode_type_0']}, {'key':'1', 'value':dictionary['label.invitecode_type_1']}],
        'userType':[{'key':'0', 'value':dictionary['label.invitecode_type_0']}, {'key':'1', 'value':dictionary['label.invitecode_type_1']}]
};

var balance={'balanceType':[{'key':'0','value':dictionary['BALANCETYPE_SYS_PRESENTED']},{'key':'1','value':dictionary['BALANCETYPE_USER_CHARGING']},{'key':'2','value':dictionary['BALANCETYPE_SHOP_INCOME']}],
		'status':[{'key':'0', 'value':dictionary['Unprocessed']}, {'key':'1', 'value':dictionary['Processed']}],
		'noticeShow':[{'key':'t', 'value':dictionary['Show']}, {'key':'f', 'value':dictionary['NoShow']}],
        'balanceStatus':[{'key':'0', 'value':dictionary['Recharge']}, {'key':'1', 'value':dictionary['RechargeSuccess']}]};
var autostop={'status':[{'key':'solved','value':'solved'},{'key':'unsolved','value':'unsolved'}]};

var allMenus = new Array();
var allMenuLanguages = new Array();
var allRoles = new Array();
var allUsers= new Array();

getAllMenus();
getAllMenuLanguages();
getAllRoles();
getAllUsers();

//根据key从数组中找到数据
function findByKey(key, arrays) {
	var result = '--';
	for(var k = 0; k < arrays.length; k++) {
		if(arrays[k].key == key) {
			result = arrays[k].value;
			break;
		}
	}
	return result;
}

//从数组中生成下拉菜单(flag, only if flag is true, create a null value select option)
function select(flag, arrays, val) {
	var result = '';
	if(arrays == null || arrays == undefined) {
	    return result;
	}
	
	if(flag == true) {
		result += '<option value=""></option>';
	}
	
	for(var k = 0; k < arrays.length; k++) {
		if(val != null && val != undefined && val == arrays[k].key) {
			result += '<option value="' + arrays[k].key + '" selected >' + arrays[k].value + '</option>';
		} else {
			result += '<option value="' + arrays[k].key + '">' + arrays[k].value + '</option>';
		}
	}
	return result;
}

function formatNumber(srcStr, nAfterDot) {
	var srcStr, nAfterDot;
	var resultStr, nTen;
	srcStr = "" + srcStr + "";
	strLen = srcStr.length;
	dotPos = srcStr.indexOf(".", 0);
	if (dotPos == -1) {
		resultStr = srcStr + ".";
		for (i = 0; i < nAfterDot; i++) {
			resultStr = resultStr + "0";
		}
		return resultStr;
	} else {
		if ((strLen - dotPos - 1) >= nAfterDot) {
			nAfter = dotPos + nAfterDot + 1;
			nTen = 1;
			for (j = 0; j < nAfterDot; j++) {
				nTen = nTen * 10;
			}
			resultStr = Math.round(parseFloat(srcStr) * nTen) / nTen;
			return resultStr;
		} else {
			resultStr = srcStr;
			for (i = 0; i < (nAfterDot - strLen + dotPos + 1); i++) {
				resultStr = resultStr + "0";
			}
			return resultStr;
		}
	}
}

function getDate(date) {
	if(date == null || date == undefined) {
		return '--';
	}
	
	var index = date.indexOf(' ');
	var res = date;
	if(index != -1) {
		res = date.substring(0,index);
	}
	return res;
}

function nullToEmpty(str) {
	if(str == null || str == undefined) {
		return '';
	} else {
		return str;
	}
}

function getUptime(uptime) {
	if (isNaN(uptime)) { return '--';};
    if (uptime=='') { return '--';};

	var days = parseInt(uptime/1000/60/60/24);
	var hours = parseInt((uptime-days*1000*60*60*24)/1000/60/60);
	var minutes = parseInt((uptime-days*1000*60*60*24 - hours*1000*60*60)/1000/60);
	
	return days + 'd:' + hours + 'h:' + minutes + 'm';
}

function changeMenuClass(id) {
    $('#' + id).parent().parent().parent().find(' > li > ul > li').removeClass('selected');
    $('#' + id).addClass('selected');
}

function replaceAt(str) {
	var result;
	result = str.replace("@", "");
	result = result.replace(".", "");
	return result;
}

function showHidden() {
    if($boxSearch.is(':hidden')) {
        $boxSearch.show();
    }
    if($pageLine.is(':hidden')) {
        $pageLine.show();
    }
    if(!$lookChart.is(':hidden')) {
        $lookChart.hide();
    }
}

function generateButton(data) {
    var button = '';
    $.each(JSON.parse(JSON.stringify(data)), function(k, v){
        button += '<a class="'+v.name+'" data-popup-id="' + v.name +'" href="#" title="'+v.title+'"><i class="'+v.btnclass+'">';
    });
    
    button += '</i> </a>';
    return button;
}

function generateHidden(data) {
    var hidden = '';
    $.each(JSON.parse(JSON.stringify(data)), function(k, v){
        hidden += '<input type="hidden" id="'+v.id+'" value="'+v.value+'" />';
    });
    
    return hidden;
}

function handleTable() {
    $tableContent.find("table tbody tr").each(function(k, v){
        if(k % 2 == 1) {
            $(this).addClass("tt");
        }
        
        $(this).find("td").each(function(){
            if($(this).text()=="null"){
                $(this).text("--");
            }
        });
    });
}

function generatePopupbox(data) {
    var v = JSON.parse(JSON.stringify(data));
    var popupbox = '';
    popupbox += '<div id="' + v.id + '" class="pop-out">'
                    +'<div class="popout-box">'
                       + '<h2><a href="#" class="pop-close"><img src="img-new/pop-close.png" /></a>'+ v.title + '</h2>'
                       + '<div class="pop-main">'
                           //+ '<div class="out red">'+ v.content + '</div>'
                           + '<div class="pop-button"><button class="btn btn-popbutton '+ v.btnClass +'">'+ v.confirm + '</button></div>'
                        +'</div>'
                    +'</div>'
                +'</div>';
    return popupbox;
}

function generateLogPopupbox(data) {
    var v = JSON.parse(JSON.stringify(data));
    var popupbox = '';
    popupbox += '<div id="' + v.id + '" class="pop-log" style="display:none;">'
                    +'<div class="popout-box pop-log-box">'
                       + '<h2><a href="#" class="pop-close"><img src="img-new/pop-close.png" /></a>'+ v.title + '</h2>'
                       + '<div class="pop-main">'
                           + '<div class="log-content"></div>'
                        +'</div>'
                    +'</div>'
                +'</div>';
    return popupbox;
}

function generatePopupMultibox(data) {
    data = JSON.parse(JSON.stringify(data));
    var popupbox = '<div class="pop-out" id="' + data.id + '">'
        + '<div class="popout-box">'
        + '<h2><a href="#" class="pop-close"><img src="img-new/pop-close.png" /></a>' + data.title + '</h2>'
        + '<div class="pop-main">'
        + generateForm(data)
    + '</div>'
    + '</div>'
    + '</div>';
    
    return popupbox;
}

function generateForm(data) {
    html= '<form class="pop-multi-form">'
    	+ '<ul class="pop-multi-ul">'
    	+ generteLis(data.list, '')
    	+ '<li class="button-margin">'
		+ '<button class="btn btn-popbutton ' + data.btnClass + '">' + data.confirm + '</button>'
		+ '</li>'
		+ '</ul>'
		+ '</form>';
	return html;
}

function generateTd(data) {
    var td = '<td class="icon-style">';
    td += data;
    td += '</td>';
    return td;
}

function generateSearchForm(data) {
    var serchForm = '<form class="search-form">' +'<ul class="cc">';
    serchForm += generteLis(JSON.parse(JSON.stringify(data)), dictionary['label.enterContents']);
    serchForm += '<li><button type="submit" class="btn btn-inquiry">'+dictionary['label.search']+'</button></li></ul></form>';
    return serchForm;
}

function generteLis(data, defaultContent) {
    var lis = '';
    $.each(data, function(k, v){
        
        if (v.type == 'select') {
            lis += '<li><span>' + v.span + '</span><select class="' + v.name + '">';
            if(v.value != undefined) {
                lis += select(true, v.list, v.value);
            } else {
                lis += select(true, v.list);
            }
            lis += '</select></li>';
        } else {
            lis += '<li>';
            if(v.span != undefined){
                lis += '<span>' + v.span + '</span>';
            }
            
            if(v.type != undefined) {
            	if(v.type == 'textarea') {
            		lis += '<textarea class="' + v.name + '"';
            	} else {
            		lis += '<input type="' + v.type + '" class="' + v.name + '"';
            	}
            } else {
                lis += '<input type="text" class="' + v.name + '"';
            }
            
            if(v.type == 'textarea') {
            	lis += '>';
            	if(v.value != undefined) {
                    lis += v.value;
                }
            	lis += '</textarea>';
            } else {
            	
            	if(defaultContent != undefined && defaultContent != null) {
            		lis += ' placeholder="' + defaultContent + '"';
            	}
            	
            	if(v.value != undefined) {
            		lis += '" value="'+v.value+'"';
            	}
            	lis += '/>';
            }
            
            lis += '</li>';
            
        }
    });
    
    return lis;
}

function generateChart(divId, title, yTitle, dataList, pointFormat) {
    $("#" + divId).jquerycharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: '<b>' + title + '</b>',
            align: 'left'
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: {
                day: '%m-%e'
            },
            title: {
                text: 'Date'
            }
        },
        yAxis: {
            title: {
                text: yTitle
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: pointFormat
        },
        series: dataList
    });
}

function genertePieChart(divId, title, dataList) {
    $('#' + divId).jquerycharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: dataList
    });
}

function cutValue(value, length) {
    if(length == null || length == undefined) {
        length = 10;
    }
    if(value.length <= length) {
        return value;
    } else {
        value = value.substring(0, length-3) + '...';
    }
    
    return value;
}

function generateTableTitle(data) {
    var tableTitle = '<table class="all-table" cellpadding="0" cellspacing="0"><thead><tr>';
    $.each(data, function(k, v){
        tableTitle += '<th>' + v + '</th>';
    });
    tableTitle += '</tr></thead><tbody></tbody></table>';
        
    return tableTitle;
}

function getAllMenus() {
	if(allMenus.length > 0) {
		allMenus = new Array();
	}
    $.ajax({
        url:"admin/getAllMenus.html",
        dataType:"json",
        success:function(data) {
            if(data.returnCode == "000"){
            	var obj;
                $.each(data.list, function(k, v){
                	obj = new Object();
                	obj.key=v.id;
                	obj.value=v.menuName;
                	allMenus.push(obj);
                });
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
            }else{
                alert(data.returnMessage);
            }
            return false;
        }
    });
}

function getAllMenuLanguages() {
	if(allMenuLanguages.length > 0) {
		allMenuLanguages = new Array();
	}
    $.ajax({
        url:"admin/getAllMenuLanguages.html",
        dataType:"json",
        success:function(data) {
            if(data.returnCode == "000"){
            	var obj;
                $.each(data.list, function(k, v){
                	obj = new Object();
                	obj.key=v.id;
                	obj.value=v.langName;
                	allMenuLanguages.push(obj);
                });
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
            }else{
                alert(data.returnMessage);
            }
            return false;
        }
    });
}

function getAllRoles() {
	if(allRoles.length > 0) {
		allRoles = new Array();
	}
    $.ajax({
        url:"admin/getAllRoles.html",
        dataType:"json",
        success:function(data) {
            if(data.returnCode == "000"){
            	var obj;
                $.each(data.list, function(k, v){
                	obj = new Object();
                	obj.key=v.id;
                	obj.value=v.roleName;
                	allRoles.push(obj);
                });
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
            }else{
                alert(data.returnMessage);
            }
            return false;
        }
    });
}

function getAllUsers() {
	if(allUsers.length > 0) {
		allUsers = new Array();
	}
    $.ajax({
        url:"admin/getAllUsers.html",
        dataType:"json",
        success:function(data) {
            if(data.returnCode == "000"){
            	var obj;
                $.each(data.list, function(k, v){
                	obj = new Object();
                	obj.key=v.id;
                	obj.value=v.userName;
                	allUsers.push(obj);
                });
            }else if (data.returnCode == "0002"){
                location.href="login.jsp";
            }else{
                alert(data.returnMessage);
            }
            return false;
        }
    });
}

var timezoneMap = new Object();
timezoneMap['Etc/GMT+12']='[UTC-12:00] GMT-12:00';
timezoneMap['Etc/GMT+11']='[UTC-11:00] GMT-11:00';
timezoneMap['Pacific/Samoa']='[UTC-11:00] Samoa Standard Time';
timezoneMap['Pacific/Honolulu']='[UTC-10:00] Hawaii Standard Time';
timezoneMap['US/Alaska']='[UTC-09:00] Alaska Standard Time';
timezoneMap['America/Los_Angeles']='[UTC-08:00] Pacific Standard Time';
timezoneMap['Mexico/BajaNorte']='[UTC-08:00] Baja California';
timezoneMap['US/Arizona']='[UTC-07:00] Arizona';
timezoneMap['US/Mountain']='[UTC-07:00] Mountain Standard Time';
timezoneMap['America/Chihuahua']='[UTC-07:00] Chihuahua, La Paz';
timezoneMap['America/Chicago']='[UTC-06:00] Central Standard Time';
timezoneMap['America/Costa_Rica']='[UTC-06:00] Central America';
timezoneMap['America/Mexico_City']='[UTC-06:00] Mexico City, Monterrey';
timezoneMap['Canada/Saskatchewan']='[UTC-06:00] Saskatchewan';
timezoneMap['America/Bogota']='[UTC-05:00] Bogota, Lima';
timezoneMap['America/New_York']='[UTC-05:00] Eastern Standard Time';
timezoneMap['America/Caracas']='[UTC-04:00] Venezuela Time';
timezoneMap['America/Asuncion']='[UTC-04:00] Paraguay Time';
timezoneMap['America/Cuiaba']='[UTC-04:00] Amazon Time';
timezoneMap['America/Halifax']='[UTC-04:00] Atlantic Standard Time';
timezoneMap['America/La_Paz']='[UTC-04:00] Bolivia Time';
timezoneMap['America/Santiago']='[UTC-04:00] Chile Time';
timezoneMap['America/St_Johns']='[UTC-03:30] Newfoundland Standard Time';
timezoneMap['America/Araguaina']='[UTC-03:00] Brasilia Time';
timezoneMap['America/Argentina/Buenos_Aires']='[UTC-03:00] Argentine Time';
timezoneMap['America/Cayenne']='[UTC-03:00] French Guiana Time';
timezoneMap['America/Godthab']='[UTC-03:00] Greenland Time';
timezoneMap['America/Montevideo']='[UTC-03:00] Uruguay Time]';
timezoneMap['Etc/GMT+2']='[UTC-02:00] GMT-02:00';
timezoneMap['Atlantic/Azores']='[UTC-01:00] Azores Time';
timezoneMap['Atlantic/Cape_Verde']='[UTC-01:00] Cape Verde Time';
timezoneMap['Africa/Casablanca']='[UTC] Casablanca';
timezoneMap['Etc/UTC']='[UTC] Coordinated Universal Time';
timezoneMap['Atlantic/Reykjavik']='[UTC] Reykjavik';
timezoneMap['Europe/London']='[UTC] Western European Time';
timezoneMap['CET']='[UTC+01:00] Central European Time';
timezoneMap['Europe/Bucharest']='[UTC+02:00] Eastern European Time';
timezoneMap['Africa/Johannesburg']='[UTC+02:00] South Africa Standard Time';
timezoneMap['Asia/Beirut']='[UTC+02:00] Beirut';
timezoneMap['Africa/Cairo']='[UTC+02:00] Cairo';
timezoneMap['Asia/Jerusalem']='[UTC+02:00] Israel Standard Time';
timezoneMap['Europe/Minsk']='[UTC+02:00] Minsk';
timezoneMap['Europe/Moscow']='[UTC+03:00] Moscow Standard Time';
timezoneMap['Africa/Nairobi']='[UTC+03:00] Eastern African Time';
timezoneMap['Asia/Karachi']='[UTC+05:00] Pakistan Time';
timezoneMap['Asia/Kolkata']='[UTC+05:30] India Standard Time';
timezoneMap['Asia/Bangkok']='[UTC+05:30] Indochina Time';
timezoneMap['Asia/Shanghai']='[UTC+08:00] China Standard Time';
timezoneMap['Asia/Kuala_Lumpur']='[UTC+08:00] Malaysia Time';
timezoneMap['Australia/Perth']='[UTC+08:00] Western Standard Time (Australia)';
timezoneMap['Asia/Taipei']='[UTC+08:00] Taiwan';
timezoneMap['Asia/Tokyo']='[UTC+09:00] Japan Standard Time';
timezoneMap['Asia/Seoul']='[UTC+09:00] Korea Standard Time';
timezoneMap['Australia/Adelaide']='[UTC+09:30] Central Standard Time (South Australia)';
timezoneMap['Australia/Darwin']='[UTC+09:30] Central Standard Time (Northern Territory)';
timezoneMap['Australia/Brisbane']='[UTC+10:00] Eastern Standard Time (Queensland)';
timezoneMap['Australia/Canberra']='[UTC+10:00] Eastern Standard Time (New South Wales)';
timezoneMap['Pacific/Guam']='[UTC+10:00] Chamorro Standard Time';
timezoneMap['Pacific/Auckland']='[UTC+12:00] New Zealand Standard Time';


var timez = new Array();
for(var p in timezoneMap){
    timez.push({key:p, value:timezoneMap[p]});
}

var hasRightMap = new Object();
hasRightMap['Y']='Y';
hasRightMap['N']='N';

var hasR = new Array();
for(var p in hasRightMap){
	hasR.push({key:p, value:hasRightMap[p]});
}

var menuLanguagesMap = new Object();
menuLanguagesMap['en_US']='en_US';
menuLanguagesMap['zh_CN']='zh_CN';

var menuL= new Array();
for(var p in menuLanguagesMap){
	menuL.push({key:p, value:menuLanguagesMap[p]});
}