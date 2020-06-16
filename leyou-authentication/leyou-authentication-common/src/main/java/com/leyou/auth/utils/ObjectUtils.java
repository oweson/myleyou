package com.leyou.auth.utils;

import org.apache.commons.lang3.StringUtils;

public class ObjectUtils {
    /**
     * 1 obj  to  string
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     * 2 obj  to  long
     */
    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Long.valueOf(StringUtils.substringBefore(obj.toString(), "."));
        }
        if (obj instanceof Number) {
            return Long.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    /**
     * 3 obj  to int
     */

    public static Integer toInt(Object obj) {
        return toLong(obj).intValue();
    }
}