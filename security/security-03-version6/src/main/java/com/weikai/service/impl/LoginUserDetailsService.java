package com.weikai.service.impl;

//import com.weikai.mapper.AuthorityMapper;
import com.weikai.mapper.UserDetailsMapper;
//import com.weikai.pojo.AuthorityEntity;
import com.weikai.pojo.UserDetailsEntity;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LoginUserDetailsService implements UserDetailsService {

//    @Resource
//    private UserDetailsMapper userDetailsMapper;

//    @Resource
//    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里完成用户登录的业务

//        UserDetailsEntity userEntity = userDetailsMapper.selectUserByUsername(username);
//        List<AuthorityEntity> authorityEntities = authorityMapper.selectAuthorityByUsername(username);


        //假设数据中查询到这些信息$2a$10$X587eQNSRu1BFM6yKCh8le2sLMPkqpOElVIYNhDi/KELCzZgT0G3a
        return new UserDetailsEntity("root", "$2a$10$X587eQNSRu1BFM6yKCh8le2sLMPkqpOElVIYNhDi/KELCzZgT0G3a", null);
    }
}
