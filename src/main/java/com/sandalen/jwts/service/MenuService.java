package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.MenuMapper;
import com.sandalen.jwts.dao.MenuRoleMapper;
import com.sandalen.jwts.dao.RoleMapper;
import com.sandalen.jwts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

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

    public List<Role> getRoleList(RoleExample example){
        List<Role> roles = roleMapper.selectByExample(example);
        return roles;
    }

    public List<Menu> getAllMenu(){
        List<Menu> allMenu = menuMapper.getAllMenu();
        return allMenu;
    }

    public List<Integer> getMenuByRid(MenuRoleExample example){
        List<MenuRole> menuRoles = menuRoleMapper.selectByExample(example);
        List<Integer> list = new ArrayList<>();
        for(MenuRole menuRole : menuRoles){
            list.add(menuRole.getMenuId());
        }
        return list;
    }

    public boolean updateMenuRole(MenuRole menuRole){
        int i = menuRoleMapper.updateByPrimaryKey(menuRole);
        if(i != 0){
            return true;
        }
        return false;
    }

    public boolean deleteMenuRole(MenuRoleExample example){
        int i = menuRoleMapper.deleteByExample(example);
        if(i != 0){
            return true;
        }
        return false;
    }

    public boolean insertMenuRole(MenuRole menuRole){
        int i = menuRoleMapper.insert(menuRole);
        if(i != 0){
            return true;
        }
        return false;
    }
}
