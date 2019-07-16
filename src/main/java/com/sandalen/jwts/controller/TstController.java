package com.sandalen.jwts.controller;

import com.sandalen.jwts.entity.Menu;
import com.sandalen.jwts.entity.Role;
import com.sandalen.jwts.entity.User;
import com.sandalen.jwts.service.MenuService;
import com.sandalen.jwts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TstController {
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping("/tstM")
    public String ts(){
        User user = userService.getUser(1);
        System.out.println(user.getUsername());
        return user.toString();
    }

    @ResponseBody
    @RequestMapping("/hh")
    public String ts2(){
        List<Menu> all = menuService.getAll();
        System.out.println(all.size());
        for (Menu m : all){
            List<Role> roles = m.getRoles();
            System.out.println(roles.size());
        }

        return null;
    }
}
