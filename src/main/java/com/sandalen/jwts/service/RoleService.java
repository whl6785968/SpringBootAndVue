package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.RoleMapper;
import com.sandalen.jwts.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public Role getRole(int id){
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }
}
