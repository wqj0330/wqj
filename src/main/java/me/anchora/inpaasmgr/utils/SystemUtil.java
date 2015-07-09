package me.anchora.inpaasmgr.utils;

import me.anchora.inpaasmgr.exception.AppException;

public class SystemUtil {
    public static void throwException(Throwable ex, String msgKey, String[] args) {
        if (ex instanceof AppException) {
            throw (AppException) ex;
        }
        throw new AppException(ex, msgKey, args);
    }

    public static void throwException(Throwable ex, String msgKey) {
        throwException(ex, msgKey, null);
    }

    public static void throwException(String msgKey) {
        throwException(null, msgKey, null);
    }

    public static void throwException(String msgKey, String[] args) {
        throwException(null, msgKey, args);
    }

    public static void throwException(Throwable ex) {
        throwException(ex, null, null);
    }
}
