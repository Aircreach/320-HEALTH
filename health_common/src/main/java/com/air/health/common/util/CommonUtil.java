package com.air.health.common.util;

import com.alibaba.fastjson2.JSON;

import java.util.Map;

/**
 * @Title: CommonUtil
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/5/22 23:15
 * @description:
 */
public class CommonUtil {

    public static <T> boolean isJSON(String str, Class<T> objectClass) {
        try {
            JSON.parseObject(str, objectClass);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
