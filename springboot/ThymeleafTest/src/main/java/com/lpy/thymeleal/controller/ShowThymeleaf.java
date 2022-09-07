package com.lpy.thymeleal.controller;

import com.lpy.thymeleal.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:LPY
 * @Data:${DATA}
 * @Description:com.lpy.thymeleal.controller
 * @version:1.0
 */
@Controller
public class ShowThymeleaf {
    @RequestMapping("/hello")
    public String showPage(Model model){
        List<User> list = new ArrayList();
        User user1 = new User("111", "1111");
        User user2 = new User("2222", "2222");
        list.add(user1);
        list.add(user2);
        model.addAttribute("list","list");
//        model.addAttribute("name","zhangsan");
        return "star";
    }
}
