package com.sandalen.jwts.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sandalen.jwts.customAnnotation.UserLoginToken;
import com.sandalen.jwts.dao.UserDao;
import com.sandalen.jwts.entity.Role;
import com.sandalen.jwts.entity.User;
import com.sandalen.jwts.entity.UserExample;
import com.sandalen.jwts.service.RoleService;
import com.sandalen.jwts.service.UserService;
import com.sandalen.jwts.utils.CookieUtils;
import com.sandalen.jwts.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/user/login")
//    @CrossOrigin(value = "http://localhost:8080",maxAge = 1800,allowedHeaders = "*")
    public Object login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Cookie cookie;

//        User user2 = userDao.getUser(user.getUsername());

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        User user2 = userService.getUsername(userExample);

        System.out.println(user2.getPassword());
        if(user2 == null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!user2.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
                }
            else {
                System.out.println("我执行了");
                String token = jwtUtils.getToken(user2);
                Cookie[] cookies = request.getCookies();

                cookie = CookieUtils.getCookie(cookies, "X-Token");

                if(cookie != null) {
                    cookie.setValue(token);
                }
                else
                {
                    cookie = new Cookie("X-Token",token);
                    cookie.setMaxAge(360000);
                    cookie.setPath("/");
//                    cookie.setDomain("http://localhost:8080");
                }
                response.addCookie(cookie);

                System.out.println(token);
                jsonObject.put("token",token);
                jsonObject.put("user",user2);
                System.out.println("?");
                return jsonObject;
            }
        }
    }

    @RequestMapping("/user/info")
    public User getUserInfoByToken(String token){
        if(token == null){
            return null;
        }
        String id = jwtUtils.getId(token);
        User user = userService.getUser(Integer.parseInt(id));
        return user;
    }

    @RequestMapping("/user/getRole")
    public Role getRoleByToken(String token){
        if(token == null){
            return null;
        }
        String id = jwtUtils.getId(token);
        Role role = roleService.getRole(Integer.parseInt(id));

        return role;
    }

    @RequestMapping("/login_p")
    public String login_p(){
        return "错误";
    }




    @UserLoginToken
    @GetMapping("/getMes")
    public String getMes(){
        return "已经通过验证";
    }

}
