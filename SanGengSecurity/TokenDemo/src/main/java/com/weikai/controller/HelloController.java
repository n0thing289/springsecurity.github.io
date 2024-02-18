package com.weikai.controller;

import com.weikai.mapper.UserMapper;
import com.weikai.pojo.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("/hello2")
    @PreAuthorize("hasAnyAuthority('system:all:all')")
    public String doHello(){
//        User user = userMapper.selectByUsername("root");
//        System.out.println(user);
        return "hello token demo";
    }

}
