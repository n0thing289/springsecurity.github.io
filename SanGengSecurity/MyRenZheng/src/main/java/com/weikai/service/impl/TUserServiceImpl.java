package com.weikai.service.impl;

import com.weikai.mapper.TUserMapper;
import com.weikai.pojo.TUser;
import com.weikai.service.TUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class TUserServiceImpl implements TUserService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //renzheng
        TUser tUser = tUserMapper.selectTUserByUsername(username);
        System.out.println(tUser);
        //souquan
        List<String> permissions = new ArrayList<>();
        tUser.setPermissions(permissions);
        return tUser;
    }

}
