package com.air.health.instruction.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Title: JSONArrayListTypeHandler
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.handler
 * @Date 2024/4/27 17:11
 * @description:
 */
@Slf4j
public class JSONListTypeHandler extends AbstractJsonTypeHandler<List<Object>> {
    @Override
    protected List<Object> parse(String json) {
        return JSON.parseArray(json, Object.class);
    }

    @Override
    protected String toJson(List<Object> obj) {
        return JSON.toJSONString(obj);
    }
}
