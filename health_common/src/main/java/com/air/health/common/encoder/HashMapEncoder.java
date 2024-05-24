package com.air.health.common.encoder;

import com.alibaba.fastjson.JSONObject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;

/**
 * @Title: AirExceptionHandler
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.exception
 * @Date 2024/4/5 15:21
 * @description:
 */

public class HashMapEncoder implements Encoder.Text<HashMap> {
    private static final Logger log = LoggerFactory.getLogger(HashMapEncoder.class);

    /**
     * 这里的参数 hashMap 要和  Encoder.Text<T>保持一致
     * @param hashMap
     * @return
     * @throws EncodeException
     */
    @Override
    public String encode(HashMap hashMap) throws EncodeException {

       try {
           return JSONObject.toJSONString(hashMap);
       }catch (Exception e){
           log.info("ServerEncoder编码异常: {}", e.getMessage());
       }
        return null;
    }
}

