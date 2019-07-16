package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.*;
import com.sandalen.jwts.entity.User;
import com.sandalen.jwts.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserMapper1 userMapper1;

    public User getUser(Integer id){
        User user = userMapper1.selectByPrimaryKey(id);
        return user;
    }

    public User getUsername(UserExample example){
        List<User> users = userMapper1.selectByExample(example);
        return users.get(0);
    }

    public User getRole(String username){
        User role = userMapper1.getRole(username);
        return role;
    }
}
