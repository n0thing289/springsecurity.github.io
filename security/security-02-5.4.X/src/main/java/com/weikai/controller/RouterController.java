package com.weikai.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController//@Controller + @ResponseBody
public class RouterController {

    @RequestMapping({"/","/index"})
    public ModelAndView index(){
        Map<String, String> map = new HashMap<>();
        map.put("notion", "未成年人禁止入站!!!18🈲网站!!!");

        return new ModelAndView("/index", map);
    }

    @RequestMapping("/login")
    public ModelAndView toLogin(){
        return new ModelAndView("/views/login");
    }

    @RequestMapping("/guest/{id}")
    public ModelAndView toGuest(@PathVariable Integer id){
        return new ModelAndView("/views/guest/" + id);
    }

    @RequestMapping("/vip/{id}")
    public ModelAndView toVip(@PathVariable Integer id){
        return new ModelAndView("/views/vip/" + id);
    }
}
