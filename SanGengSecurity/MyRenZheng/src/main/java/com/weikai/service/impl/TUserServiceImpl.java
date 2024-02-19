package com.weikai.service.impl;

import com.weikai.mapper.TUserMapper;
import com.weikai.pojo.TUser;
import com.weikai.service.TUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class TUserServiceImpl implements TUserService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //renzheng
        TUser tUser = tUserMapper.selectTUserByUsername(username);
        if(Objects.isNull(tUser)){
            return null;
        }
        System.out.println("TUserServiceImpl: " + tUser);
        //souquan
        List<String> permissions = tUserMapper.selectPermsByUserId(tUser.getId());
        if(Objects.isNull(permissions)){
            return null;
        }
        tUser.setPermissions(permissions);
        return tUser;
    }

}
