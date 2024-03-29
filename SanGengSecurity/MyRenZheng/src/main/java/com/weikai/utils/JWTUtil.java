package com.weikai.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.List;

public class JWTUtil {
    private static final String sign = "sign";//签名
    private static final long time = 1000 * 60 * 60 * 24L;//持续时间

    public static String generateJwtByUserIdAndAuthorities(Long userId, List<String > permissions) {
        //生成token
       return JWT.create()
                .withClaim("userid", userId)
                .withClaim("perms", permissions)
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .sign(Algorithm.HMAC256(sign));
    }


    public static String generateJwtByUserId(Integer userId) {
        //生成token
        return JWT.create()
                .withClaim("userid", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .sign(Algorithm.HMAC256(sign));
    }

    public static String generateJwtByUsernameAndPassword(String username, String password) {
        //生成token
        String token = JWT.create()
                .withClaim("username", username)
                .withClaim("password", password)
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                .sign(Algorithm.HMAC256(sign));
        return token;
    }

    public static void verify(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
        jwtVerifier.verify(token);
    }
}
