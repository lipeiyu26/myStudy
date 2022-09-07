package com.example.socket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

@RestController
public class ServerController {
    @RequestMapping(value = "/server",method={RequestMethod.POST, RequestMethod.GET})
    public String server(HttpServletRequest request) throws IOException {
        try {
            String msg = request.getParameter("msg");
            String user = request.getParameter("user");
            //注意，为了便于测试，这里写死了
            user = "abc";
            //获取用户的webSocket对象
            WebSocket ws = WebSocket.getClients().get(user);
            //发送消息
            ws.sendMessage(msg);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "<script language='javascript' type='text/javascript'>\n" +
                "window.location.href='server.html';\n" +
                "</script>";
    }
}
