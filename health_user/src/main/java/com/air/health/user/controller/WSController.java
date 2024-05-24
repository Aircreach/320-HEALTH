package com.air.health.user.controller;

import com.air.health.common.model.WSMsgModel;
import com.air.health.user.manager.WebSocketServer;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.EncodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Title: WSController
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.user.entity
 * @Date 2024/5/21 13:13
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/soc")
public class WSController {
    @Autowired
    WebSocketServer webSocketServer;

    @PostMapping("notify")
    public Long notify(@RequestBody WSMsgModel msg) throws IOException, EncodeException {
        Long num = webSocketServer.sendAll(msg);
        return num;
    }
}
