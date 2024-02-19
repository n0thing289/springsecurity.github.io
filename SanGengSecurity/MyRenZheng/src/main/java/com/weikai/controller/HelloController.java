package com.weikai.controller;

import com.weikai.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello2")
public class HelloController {

    @GetMapping({"","/"})
    @PreAuthorize("hasAuthority('test1')")
    Result hello(){
        return new Result("200", "这条信息需要test权限， 如果你看到这条信息，说明你就是test权限", null);
    }
}
