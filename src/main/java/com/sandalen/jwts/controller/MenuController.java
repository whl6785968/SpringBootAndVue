package com.sandalen.jwts.controller;

import com.sandalen.jwts.entity.Menu;
import com.sandalen.jwts.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/tstMenu")
    public List<Menu> getMenuByUserId(String userId){
        System.out.println(userId);
        int userid = Integer.parseInt(userId);
        List<Menu> menuByUserId = menuService.getMenuByUserId(userid);
        return menuByUserId;

    }
}
