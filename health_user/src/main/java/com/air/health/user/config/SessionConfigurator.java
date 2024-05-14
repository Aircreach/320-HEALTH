package com.air.health.user.config;

import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

import javax.servlet.http.HttpSession;


public class SessionConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        HttpSession httpSession = (HttpSession) request.getHttpSession();

        // 获取httpsession对象
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}

