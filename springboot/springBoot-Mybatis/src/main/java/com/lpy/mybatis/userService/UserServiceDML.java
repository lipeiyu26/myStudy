package com.lpy.mybatis.userService;

import com.lpy.mybatis.pojo.User;

import java.util.List;

/**
 * @Auther:LPY
 * @Data:${DATA}
 * @Description:com.lpy.mybatis.UserService
 * @version:1.0
 */
public interface UserServiceDML {
    //查询所有的信息
    public List<User> getUser();
    //添加信息
    public void addUser(User user);
}
