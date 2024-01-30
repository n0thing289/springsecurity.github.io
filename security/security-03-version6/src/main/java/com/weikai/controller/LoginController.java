package com.weikai.controller;

import com.weikai.pojo.UserDetailsEntity;
import com.weikai.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@RestController
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;

    @RequestMapping("/api/login")
    public String doLogin(@RequestBody UserDetailsEntity user) {
        try {
            System.out.println("当前请求登录的user" + user);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            System.out.println("当前请求登录的auth" + auth);

            Authentication authentication = authenticationManager.authenticate(auth);
            if (authentication == null) {
                System.out.println("用户名或密码错误");
            }
            System.out.println("当前请求登录的authentication" + authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            //获取用户权限信息
            String authorityString = "root";


            //验证成功
            String token = JwtUtil.getJwt(userDetails.getUsername(), authorityString);
            return "{'code':verifyT, 'data':'" + token + "', 'msg':null}";
        } catch (Exception e) {
            System.out.println("LoginController 异常");
            throw new RuntimeException(e);
        }

    }
}
