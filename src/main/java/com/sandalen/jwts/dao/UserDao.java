package com.sandalen.jwts.dao;

import com.sandalen.jwts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUser(String username){
        System.out.println(username);
        String sql = "select * from user where username = ?";
        Object[] objects = {username};
        User user = jdbcTemplate.queryForObject(sql, objects, new UserMapper());
        return user;

    }

    public User getUserById(String id){
        String sql = "select * from user where id = ?";
        Object[] objects = {id};
        User user = jdbcTemplate.queryForObject(sql, objects, new UserMapper());
        return user;
    }


}
