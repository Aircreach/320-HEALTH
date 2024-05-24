package com.air.health.user.manager;

import com.air.health.common.encoder.HashMapEncoder;
import com.air.health.common.model.AirException;
import com.air.health.common.model.WSMsgModel;
import com.air.health.common.model.WSType;
import com.air.health.common.encoder.WSMsgEncoder;
import com.air.health.common.util.CommonUtil;
import com.alibaba.fastjson.JSON;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: SocketManager
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.user.manager
 * @Date 2024/5/18 22:08
 * @description:
 */
@Slf4j
@Component
@ServerEndpoint(value = "/socket/{sid}", encoders = { WSMsgEncoder.class, HashMapEncoder.class })
public class WebSocketServer {

    //静态变量，记录当前在线连接数
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();


    public void sendText(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    public void sendObject(Session session, Object object) throws IOException, EncodeException {
        if (session != null) {
            synchronized (session) {
                session.getBasicRemote().sendObject(object);
            }
        }
    }


    public Long sendAll(WSMsgModel msg) throws IOException, EncodeException {
        Long num = 0L;
        for (Map.Entry<String, Session> entry : sessionPools.entrySet()) {
            Session session = entry.getValue();
            sendObject(session, msg);
            num ++;
        }
        return num;
    }

    public void sendMsg(String key, WSMsgModel msg) throws EncodeException, IOException {
        Session session = sessionPools.get(key);
        sendObject(session, msg);
    }

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userId) {
        sessionPools.put(userId, session);
        addOnlineCount();
        log.info("[WebSocket connect] " + userId + " total: " + onlineNum);
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "sid") String userId) {
        sessionPools.remove(userId);
        subOnlineCount();
        log.info("[WebSocket disconnect] " + userId + " total: " + onlineNum);
    }

    //接收客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException, EncodeException {

        if (!CommonUtil.isJSON(message, Map.class)) {
//            log.info("[webSocket isOnline]......" + message);
            sendText(sessionPools.get(message), "WebSocket");
            return;
        }
        if (CommonUtil.isJSON(message, Map.class)) {
            log.info("[webSocket message]  receive: " + message);
            Map<String, Object> map = JSON.parseObject(message, Map.class);
            String type = (String) map.get("type");
            Session session = null;
            Map<String, Object> data = new HashMap<>();
            switch (type) {
                case "candidate":
                    session = sessionPools.get(map.get("to"));
                    data.put("candidate", map.get("candidate"));
                    break;
                case "awake":
                case "alive":
                    session = sessionPools.get(map.get("to"));
                    break;
                case "offer":
                    session = sessionPools.get(map.get("to"));
                    data.put("offer", map.get("offer"));
                    break;
                case "answer":
                    session = sessionPools.get(map.get("to"));
                    data.put("answer", map.get("answer"));
                    break;
                default:
                    sendObject(sessionPools.get(map.get("from")), new WSMsgModel(WSType.ERROR, "未知错误"));
            }
            if (session == null) {
                sendObject(sessionPools.get(map.get("from")), new WSMsgModel(WSType.ERROR, "目标用户离线中......."));
            }
            data.put("type", map.get("type"));
            data.put("from", map.get("from"));
            data.put("to", map.get("to"));
            sendObject(session, new WSMsgModel(WSType.VIDEO, data));
        }
    }

    //错误
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

}
