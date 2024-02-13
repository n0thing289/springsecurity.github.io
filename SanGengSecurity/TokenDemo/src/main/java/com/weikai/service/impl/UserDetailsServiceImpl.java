package com.weikai.service.impl;

import com.weikai.mapper.UserMapper;
import com.weikai.pojo.LoginUser;
import com.weikai.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        User user = userMapper.selectByUsername(username);
        //如果没有抛出异常,注意这里过滤链抛出的异常在ExceptionTranslateFilter接
        if(user == null){
            throw new RuntimeException("数据库中查不到此对象:用户名或者密码错误");
        }
        //TODO 查询权限信息
        List<String> permissions = Arrays.asList("test", "admin");

        //封装成UserDetails, 新建一个pojo类LoginUser
        return new LoginUser(user, permissions);//数据库明文存111需要加{noop} => {noop}111
        //1. 实现UserDetailsService
        //2. 替换密码加密解码器
        //3. 自定义接口：调用ProviderManager， 认证成功传回jwt
        //  放行登录接口
        //  AuthenticationManager的authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())) principal主体在没有认证前, 只填入用户名(认证后就是你的UserDetails的实现的pojo类 LoginUser), credentials是密码
        //
        //
    }
}
