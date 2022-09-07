//package com.example.socket;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@RestController
//@ServerEndpoint(value = "/websocket")   //房间号、类型（1，直播聊天）、用户Id、
//@Component
//public class WebSocket {
//
//    private static WebSocket webSocket;
//    private static Logger logger = LoggerFactory.getLogger(WebSocket.class);
//
//    //当前连接数
//    private static int onlineCount = 0;
//
//    //存放每个客户端对应的MyWebSocket对象。
//    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
//
//    //与某个客户端的连接会话
//    private Session session;
//
//    //客户端唯一标识sid(直播ID)
//    private String sid = "";
//    //用户类型
//    private Integer type=0;
//    //用户ID
//    private Integer userId = 0;
//    //用户昵称
//    private String nickName="";
//    //用户头像地址
//    private String headImageUrl="";
//    @Autowired
//    private  UserRepository userRepository;
//
//    public void sendMessage(String message) throws IOException {
//        this.session.getBasicRemote().sendText(message);
//    }
//
//    @PostConstruct
//    public void init() {
//        webSocket = this;
//        webSocket.userRepository = this.userRepository;
//    }
//
//    /**
//     * 连接建立成功调用的方法
//     */
//    @OnOpen
//    public void onOpen(Session session, @PathParam("sid") String sid,  @PathParam("type") Integer type,@PathParam("userId") Integer userId) {
//        moreWindow(sid,userId,type);
//        //在线数加1
//        addOnlineCount();
//        this.session = session;
//        //加入set中
//        webSocketSet.add(this);
//        this.sid = sid;
//        this.userId = userId;
//        this.type=type;
//        User user=WebSocket.webSocket.userRepository.findById(userId);
//        this.nickName=user.getNickName();
//        this.headImageUrl=user.getHeadImageUrl();
//        logger.info("用户ID:"+userId+"用户昵称:"+nickName+"新连接：sid=" + sid + " 当前在线人数" + getOnlineCount());
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            logger.error("websocket IO异常");
//        }
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        //群发消息
//        for (WebSocket item : webSocketSet) {
//            try {
//                if(item.sid.equals(this.sid)){
//                    item.sendMessage(message);
//                    System.out.println("--------------------"+message+"总人数"+onlineCount);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 同一用户打开多个窗口问题
//     *
//     * @param sid
//     */
//    public void moreWindow(String sid,Integer userId,Integer type) {
//        if (StringUtils.isEmpty(sid)) {
//            return;
//        }
//        if(webSocketSet.isEmpty()){
//            return;
//        }
//        for (WebSocket item : webSocketSet) {
//            if (item.sid.equals(sid)&&item.userId.equals(userId)&&item.type==type) {
//                //已经有相同的了
//                webSocketSet.remove(item);
//                subOnlineCount();
//            }
//        }
//    }
//
//    /**
//     * 发送消息给指定用户
//     *
//     * @param message
//     * @param sid
//     */
//    public static void sendMessage(String message, @PathParam("sid") String sid) {
//        logger.info("发送消息：sid=" + sid + " message：" + message);
//        for (WebSocket item : webSocketSet) {
//            try {
//                if (sid == null) {
//                    item.sendMessage(message);
//                    System.out.println("+++++++++++++++"+message);
//                } else if (item.sid.equals(sid)) {
//                    logger.info("开始发送消息：sid=" + sid);
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                logger.error("发送消息失败 sid:"+sid,e);
//                continue;
//            }
//        }
//    }
//
//    @OnClose
//    public void onClose() {
//        logger.info("连接关闭：sid=" + sid + " 当前在线人数" + getOnlineCount());
//        webSocketSet.remove(this);
//        subOnlineCount();
//    }
//
//    @OnError
//    public void onError(Session session, Throwable error) {
//        error.printStackTrace();
//    }
//
//    /**
//     * 当前在线人数
//     * @return
//     */
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    /**
//     * 添加在线人数
//     */
//    public static synchronized void addOnlineCount() {
//        WebSocket.onlineCount++;
//    }
//
//    /**
//     * 减少在线人数
//     */
//    public static synchronized void subOnlineCount() {
//        if (WebSocket.onlineCount <= 0) {
//            WebSocket.onlineCount = 0;
//            return;
//        }
//        WebSocket.onlineCount--;
//    }
//
//    /**
//     * 人数列表
//     */
//    @PostMapping(value = "/numberList")
//    public  String numberList(@RequestParam(value = "sid") String sid,@RequestParam(value = "type") Integer type){
//        Map map=new HashMap<>();
//        List<User> userList=new ArrayList<>();
//        Integer count=0;
//        for (WebSocket item : webSocketSet) {
//            if(item.sid!=null&&item.sid.equals(sid)&&item.type==type){
//                User user=new User();
//                user.setNickName(item.nickName);
//                user.setUserId(item.userId);
//                user.setHeadImageUrl(item.headImageUrl);
//                userList.add(user);
//                count++;
//            }
//        }
//        map.put("userList",userList);
//        map.put("count",count);
//        return map.toString();
//    }
//}
//
//
