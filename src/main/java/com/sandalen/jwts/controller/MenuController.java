package com.sandalen.jwts.controller;

import com.sandalen.jwts.beans.RespBean;
import com.sandalen.jwts.entity.*;
import com.sandalen.jwts.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/tstMenu")
    public List<Menu> getMenuByUserId(String userId){
        System.out.println(userId);
        int userid = Integer.parseInt(userId);
        List<Menu> menuByUserId = menuService.getMenuByUserId(userid);
        return menuByUserId;
    }

    @RequestMapping("/menu/getRoleList")
    public List<Role> getRoleList(){
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = menuService.getRoleList(roleExample);
        return roleList;
    }

    @RequestMapping(value = "/menu/getAllMenu/{rid}")
    public Map<String,Object> getAllMenu(@PathVariable int rid){
        Map<String,Object> map = new HashMap<>();
        MenuRoleExample example = new MenuRoleExample();
        example.createCriteria().andRoleIdEqualTo(rid);
        List<Integer> menuByRid = menuService.getMenuByRid(example);
        map.put("mids",menuByRid);
        List<Menu> allMenu = menuService.getAllMenu();
        map.put("menus",allMenu);
        return map;
    }

    @RequestMapping(value = "/menu/updateMenuByRid")
    public RespBean updateMenuByRid(int id,Integer[] mids){
        MenuRoleExample menuRoleExample = new MenuRoleExample();
        menuRoleExample.createCriteria().andRoleIdEqualTo(id);
        menuService.deleteMenuRole(menuRoleExample);

//        Integer[] newMids = new Integer[mids.length];
//        for(int i=0;i<mids.length;i++){
//            newMids[i] = Integer.parseInt(mids[i]);
//        }

        for(Integer mid : mids){
            MenuRole menuRole = new MenuRole();
            menuRole.setRoleId(id);
            menuRole.setMenuId(mid);
            menuService.insertMenuRole(menuRole);
        }

        return RespBean.ok("修改成功");
    }

    @RequestMapping("/menu/addNewRole")
    public RespBean addNewRole(String roleName){
        int maxRoleId = menuService.getMaxRoleId();
        Role role = new Role();
        role.setId(maxRoleId+1);
        role.setRoleName("ROLE_"+roleName.toUpperCase());
        boolean b = menuService.addNewRole(role);
        if(b == true){
            return RespBean.ok("添加成功");
        }

        return RespBean.error("添加失败");
    }
}
