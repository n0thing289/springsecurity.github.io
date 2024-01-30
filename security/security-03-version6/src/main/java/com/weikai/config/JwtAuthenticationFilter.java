package com.weikai.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.weikai.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            String token = request.getHeader("token");
            if(token == null){
                chain.doFilter(request,response);
                return;
            }

            DecodedJWT decode = JWT.decode(token);
            String username = decode.getClaim("username").toString();
            String authorities = decode.getClaim("authorities").toString();

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(authorities)));
            //将用户信息放到SecurityContext上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request,response);
        }catch(Exception e){

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            String value = "{'msg':'用户未登录'";
            response.getWriter().write(value);

        }
    }
}
