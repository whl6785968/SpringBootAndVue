package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.*;
import com.sandalen.jwts.entity.User;
import com.sandalen.jwts.entity.UserExample;
import com.sandalen.jwts.entity.UserRole;
import com.sandalen.jwts.entity.UserRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserMapper1 userMapper1;

    @Autowired
    private UserRoleMapper userRoleMapper;

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

    public List<User> getAllUser(){
        List<User> users = userMapper1.getUser();
        return users;
    }

    public boolean changeAuth(UserRole userRole){
        int i = userRoleMapper.updateByPrimaryKey(userRole);
        if(i != 0){
            return true;
        }
        return false;
    }

    public UserRole getUserRole(UserRoleExample example){
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        return userRoles.get(0);
    }

    public List<User> getUserExceptSelf(int id){
        List<User> users = userMapper1.getUserExceptSelf(id);
        return users;
    }
}
