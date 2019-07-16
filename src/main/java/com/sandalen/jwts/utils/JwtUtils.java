package com.sandalen.jwts.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sandalen.jwts.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUtils {
    public String getToken(User user){
        String token = "";
        token = JWT.create().withAudience(user.getId().toString()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }


    public String getId(String token){
        DecodedJWT jwt = JWT.decode(token);
        List<String> audience = jwt.getAudience();


        return audience.get(0);
    }
}
