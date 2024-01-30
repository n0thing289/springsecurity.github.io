package com.weikai.controller;

import com.weikai.pojo.MyUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Controller
public class LoginController {

    @Resource
    private AuthenticationProvider authenticationProvider;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public String doLogin(@RequestBody MyUser loginUser){
        System.out.println("loginUser="+loginUser);

        //根据前端传过来的loginuser (只有用户名和密码),封装成待认证的认证对象
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword());

        //调用认证Bean去认证, 进到认证Bean的认证逻辑中

        Authentication authed = authenticationProvider.authenticate(auth);

        return authed.toString();
    }
}
