package com.lsyedu.myssm.util;

/**
 * @author lsy
 * @version 1.0
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
