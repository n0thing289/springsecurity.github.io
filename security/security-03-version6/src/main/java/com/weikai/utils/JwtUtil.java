package com.weikai.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String sign = "sign";
    private static final long time = 1000*60*60*24L;

    public static String getJwt(String username, String authorityString){
        String token = JWT.create()
                .withClaim("username", username)
                .withClaim("authorities", authorityString)
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .sign(Algorithm.HMAC256(sign));
        return token;
    }

    public void verifyJwt(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC256(sign)).build();
        build.verify(token);
        /*
        验证失败就会抛出异常
         */
    }
}
