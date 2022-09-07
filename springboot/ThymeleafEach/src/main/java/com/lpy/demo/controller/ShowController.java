package com.lpy.demo.controller;

import com.lpy.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:LPY
 * @Data:${DATA}
 * @Description:com.lpy.demo.controller
 * @version:1.0
 */
@Controller
public class ShowController {
    @RequestMapping("/hello")
    public String hello(Model model){
        List<User> userLis = new ArrayList<>();
        userLis.add(new User("zhangsan",1));
        userLis.add(new User("LISI",2));
        model.addAttribute("list",userLis);
        return "star";

    }
}
