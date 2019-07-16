package com.sandalen.jwts.service;

import com.sandalen.jwts.entity.Role;
import com.sandalen.jwts.entity.User;
import com.sandalen.jwts.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceBySecurity implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username+"======================");
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        User user = userService.getRole(username);
        List<Role> roles = user.getRoles();
        if(!StringUtils.isEmpty(roles)){
            for(Role role : roles){
                list.add(new SimpleGrantedAuthority(role.getRoleName().trim()));
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),list);
    }
}
