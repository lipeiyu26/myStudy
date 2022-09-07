package com.lpy.mybatis.controller;

import com.lpy.mybatis.pojo.User;
import com.lpy.mybatis.userService.UserServiceDML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Auther:LPY
 * @Data:${DATA}
 * @Description:com.lpy.mybatis.controller
 * @version:1.0
 */
@Controller
public class showData {
    @Autowired
    private UserServiceDML userServiceDML;
    //走页面
    @RequestMapping("/{page}")
    public String showTbItem(@PathVariable String page ){
        return "/WEB-INF/jsp/"+ page+ ".jsp";
    }

    //查询
    @RequestMapping("/select")
    public String showData(Model model){
        List<User> userList = userServiceDML.getUser();
        model.addAttribute("list",userList);
        return "/WEB-INF/jsp/showData.jsp";
    }
//添加
    @RequestMapping("/add")
    public String showAdd(User user,Model model){
        try {
            userServiceDML.addUser(user);
            model.addAttribute("Y","添加成功");

        } catch (Exception e) {
            model.addAttribute("Y","添加失败");

            e.printStackTrace();
        }
        return "/WEB-INF/jsp/addData.jsp";

    }

}
