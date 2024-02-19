package com.weikai.controller;

import com.weikai.pojo.TUser;
import com.weikai.utils.JWTUtil;
import com.weikai.utils.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    AuthenticationManager manager;

    @PostMapping("/login")
    Result login(@RequestBody TUser user) {
        System.out.println("LoginController: " + user);
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (!auth.isAuthenticated()) {
            throw new RuntimeException("登录验证失败");
        }
        TUser u = (TUser) auth.getPrincipal();
        String token = JWTUtil.generateJwtByUserIdAndAuthorities(u.getId(), u.getPermissions());

        return new Result("200", token, null);
        /**
         * System.out.println("getPrincipal:" + auth.getPrincipal());
         * System.out.println("getAuthorities:" + auth.getAuthorities());
         * System.out.println("getCredentials:" + auth.getCredentials());
         * System.out.println("getDetails:" + auth.getDetails());
         * System.out.println("getName:" + auth.getName());
         *
         * System.out.println(token);
         */
    }
}
