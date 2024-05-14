package com.air.health.common.model;

import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: Result
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/1/22 13:12
 * @description:
 */
public class Result extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 构造函数
    public Result() {
        put("code", HttpStatus.SC_OK);
        put("msg", "success");
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result success(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result success() {
        return new Result();
    }

    public static Result error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    /**
     *
     * @param code => 端口号业务层(0: 数据库, 1: 逻辑层, 2: 用户输入错误, 3: 未知)错误码
     * @param msg
     * @return
     */
    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
