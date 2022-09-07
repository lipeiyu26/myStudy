package com.lpy.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther:LPY
 * @Data:${DATA}
 * @Description:com.lpy.jsp.controller
 * @version:1.0
 */
@Controller
public class ShowjSP {
    @RequestMapping("/hello")
    public String show(Model model){
        model.addAttribute("star","zhangsan");
        return "star";
    }
}
