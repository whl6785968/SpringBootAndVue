package com.sandalen.jwts.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static Cookie getCookie(Cookie[] cookies,String cname){
        if(cookies != null){
            for (Cookie c : cookies){
                if(c.getName().equals(cname))
                {
                    return c;
                }
            }
        }
        return null;

    }
}
