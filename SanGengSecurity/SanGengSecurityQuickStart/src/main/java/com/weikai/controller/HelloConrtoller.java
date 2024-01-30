package com.weikai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConrtoller {
    @RequestMapping("/hello")
    public String doHello(){
        return "hello";
    }
}
