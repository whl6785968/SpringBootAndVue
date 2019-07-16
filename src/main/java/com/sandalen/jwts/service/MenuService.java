package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.MenuMapper;
import com.sandalen.jwts.entity.Menu;
import com.sandalen.jwts.entity.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAllMenu(MenuExample example){
        List<Menu> menus = menuMapper.selectByExample(example);
        return menus;
    }

    public List<Menu> getAll(){
        List<Menu> menus = menuMapper.selectAll();
        return menus;
    }

    public List<Menu> getMenuByUserId(int userId){
        List<Menu> menus = menuMapper.getMenuByUserId(userId);
        return menus;
    }
}
