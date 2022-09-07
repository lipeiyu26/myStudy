package com.example.socket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;

@Controller
public class SocketController {

    public void socket() throws IOException {
        WebsocketOne ws = new WebsocketOne();
        HashMap<String, String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");

        // 发送数据给服务端
        ws.sendMessageAll(map.toString());
    }

}
