package com.sandalen.jwts.config;

import com.sandalen.jwts.entity.User;
import com.sandalen.jwts.entity.UserRole;
import com.sandalen.jwts.entity.UserRoleExample;
import com.sandalen.jwts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/getAllUser")
    public List<User> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return allUser;
    }

    @RequestMapping("/user/changeAuth")
    public boolean changeAuth(String id,String auth){
        int userId = Integer.parseInt(id);
        int userAuth = Integer.parseInt(auth);

        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        UserRole userRole = userService.getUserRole(example);
        userRole.setRoleId(userAuth);

        boolean result = userService.changeAuth(userRole);
        return result;
    }
}
