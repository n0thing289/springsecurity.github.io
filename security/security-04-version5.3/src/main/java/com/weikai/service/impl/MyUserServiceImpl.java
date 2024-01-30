package com.weikai.service.impl;

import com.weikai.mapper.MyUserMapper;
import com.weikai.pojo.MyUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserServiceImpl implements UserDetailsService {
    @Resource
    private MyUserMapper myUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用dao掉方法去查找
        MyUser myUser = myUserMapper.selectByUsername(username);
        return myUser;
    }
}
