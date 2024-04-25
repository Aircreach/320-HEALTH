package com.air.health.common.util;

/**
 * @Title: StringHelper
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/4/23 16:48
 * @description:
 */
public class StringHelper {

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperLetters(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
