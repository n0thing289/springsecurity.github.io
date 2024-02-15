package com.weikai.controller;

import com.weikai.pojo.User;
import com.weikai.service.LoginService;
import com.weikai.utils.Code;
import com.weikai.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        System.out.println(user);

        return loginService.login(user);
    }

    @GetMapping("/logout")
    public Result logout(){
        //因为前端默认发token， 会先经过我们的jwt过滤器存authed，认证成功后才到这里
        Authentication authed = SecurityContextHolder.getContext().getAuthentication();
        String userid = (String) authed.getPrincipal();//我在jwt里存principal的是userid

        // 从redis里删除用户信息; 做完服务器的登出操作后,告诉前端可以删除token了
        return new Result(Code.GET_OK, true,"可以删除token");
    }




}
