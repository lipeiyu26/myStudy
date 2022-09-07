package com.example.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebsocketOne {

    private static int onlineCount = 0;
    private static Map<String, WebsocketOne> clients = new ConcurrentHashMap<String, WebsocketOne>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;

        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接" + username);
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
//        DataWrapper res = new DataWrapper();
        System.out.println("message:" + message);
        JSONObject req = JSONObject.parseObject(message);
// System.out.println("item:" + req.getJSONObject("item"));
// System.out.println("item:" + req.getInteger("code"));
        HashMap<String, String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");

        // 发送数据给服务端
        sendMessageAll(JSON.toJSONString(map));
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        // session.getAsyncRemote().sendText(message);
        for (WebsocketOne item : clients.values()) {
            if (item.username.equals(To))
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebsocketOne item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketOne.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketOne.onlineCount--;
    }

    public static synchronized Map<String, WebsocketOne> getClients() {
        return clients;
    }
}
