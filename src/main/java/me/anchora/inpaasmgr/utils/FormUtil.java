package me.anchora.inpaasmgr.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.log4j.Logger;

public class FormUtil {
    private static final Logger logger = Logger.getLogger(FormUtil.class);

    @SuppressWarnings("rawtypes")
    public static String toForm(Object obj) {
        StringBuffer sb = new StringBuffer();
        String result = "";
        if(obj instanceof Map) {
            for(Object key : ((Map)obj).keySet()) {
                sb.append(key + "=" + ((Map)obj).get(key) + "&");
            }
        } else {
            aa(obj, obj.getClass(), sb);
        }
        if (sb.length() > 1) {
            result = sb.toString().substring(0, sb.length() - 1);
        }

        return result;
    }
    
    private static void aa(Object obj, Class<?> clazz, StringBuffer res) {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            aa(obj, superclass, res);
        }
        
        String method;
        Object objResult;
        for (Field field : clazz.getDeclaredFields()) {
            method = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            if (field.getName() != null && "serialVersionUID".equals(field.getName())) {
                continue;
            }

            try {
                objResult = clazz.getMethod(method).invoke(obj);
                if (objResult == null) {
                    continue;
                }
                res.append(field.getName() + "=" + objResult + "&");
            } catch (Exception e) {
                logger.warn(e);
            }
        }
    }
}
