package me.anchora.inpaasmgr.utils;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static Locale getLocale(HttpServletRequest request) {
        Locale locale;
        //这样便可以获取一个cookie数组
        Cookie[] cookies = request.getCookies();
        String cookieLang = null;
        if(cookies != null) {
            for(Cookie cookie : cookies){
            	//判断获取cookie的name属性值是否为“lang”
//服务器给客户端浏览器返回响应的时候可以附带cookie保存在客户端硬盘或者内存中，服务器可以向客户端发送多个cookie让客户端保存。cookie的本质其实就是一个name/value对，用于保存服务器需要客户端保存的信息（如验证信息等）。这句话的意思是服务器从客户端浏览器取回它保存在浏览器的所有cookie，并且遍历每一个cookie，看看是否有一个cookie的name是"lang"。
              //参考home.jsp中的<fmt:setLocale value="${cookie.lang.value}" />，cookie的name属性值“lang”,
            	//cookie.lang.value == 'zh_CN',cookie.lang.value == 'en_CN',cookie.getName()为"lang",cookie.getValue(为'zh_CN'或'en_CN'
            	if("lang".equals(cookie.getName())) {
                    cookieLang = cookie.getValue();
                    break;
                }
            }
        }
        
        if(cookieLang != null && cookieLang.length() > 0 && cookieLang.indexOf("_") > 0) {
            locale = new Locale(cookieLang.split("_")[0], cookieLang.split("_")[1]);
        } else {
            locale = request.getLocale();
        }
        
        return locale;
    }
}
