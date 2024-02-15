package com.weikai.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.weikai.utils.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从头部获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //验证token
        try{
            JWTUtil.verify(token);
        }catch(JWTVerificationException e){
            //验证失败抛出异常
            throw new RuntimeException("token验证失败");
        }

        //验证成功存入SecurityContextHolder
        DecodedJWT decode = JWT.decode(token);
        String userid = decode.getClaim("userid").toString();
        //权限信息要存入
        //TODO 查询权限信息权限信息要存入

        UsernamePasswordAuthenticationToken authed = new UsernamePasswordAuthenticationToken(userid, null,
                Collections.singletonList(new SimpleGrantedAuthority("test")));//细节三个参数,会调用setAuthenticated(true)
        SecurityContextHolder.getContext().setAuthentication(authed);//LocalThread技术, 线程绑定数据
        //配置这个jwt过滤器, 把它放在UsernamePasswordAuthenticationFilter过滤器之前
        System.out.println("token认证成功");
        filterChain.doFilter(request,response);//成功放行
    }
}
