package com.lpy.mybatis.userServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lpy.mybatis.mapper.UserMapper;
import com.lpy.mybatis.pojo.UserExample;
import com.lpy.mybatis.userService.UserServiceDML;
import com.lpy.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:LPY
 * @Data:${DATA}
 * @Description:com.lpy.mybatis.UserServiceImpl
 * @version:1.0
 */
@Service
public class UserServiceImpl implements UserServiceDML{
    @Autowired
    private UserMapper userMapper;
    //查询
    @Override
    public List<User> getUser() {
        //开启分页，并设置分页条件
        PageHelper.startPage(1,2);
        UserExample example=new UserExample();
        List<User> userList = userMapper.selectByExample(example);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userList;
    }
//添加
    @Override
    public void addUser(User user) {
        User u=new User();
        u.setAge(user.getAge());
        u.setId(user.getId());
        u.setName(user.getName());
        userMapper.insertSelective(u);
    }
}
