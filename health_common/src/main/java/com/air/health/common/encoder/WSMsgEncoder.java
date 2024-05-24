package com.air.health.common.encoder;

import com.air.health.common.model.WSMsgModel;
import com.alibaba.fastjson.JSON;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WSMsgEncoder implements Encoder.Text<WSMsgModel> {

    /**
     * 这里的参数 msgModel 要和  Encoder.Text<T>保持一致
     * @param msgModel
     * @return
     * @throws EncodeException
     */
    @Override
    public String encode(WSMsgModel msgModel) throws EncodeException {
        try {
            return JSON.toJSONString(msgModel);
        } catch (Exception e){
            log.info("ServerEncoder编码异常: {}", e.getMessage());
        }
        return null;
    }
}