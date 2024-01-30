package com.weikai.service.impl;

import com.weikai.pojo.LoginUser;
import com.weikai.pojo.User;
import com.weikai.service.LoginService;
import com.weikai.utils.Code;
import com.weikai.utils.JWTUtil;
import com.weikai.utils.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public Result login(User user) {
        //调用ProviderManager的authenticate方法验证
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authed = authenticationManager.authenticate(auth);
        if(Objects.isNull(authed)){//authed.isAuthenticated()
            //认证失败
            throw new RuntimeException("认证失败, 用户名或者密码错误");
        }
        //认证成功, 使用userid生成token
        LoginUser loginUser = (LoginUser) authed.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String token = JWTUtil.getJwtByUserId(userid);
        //返回响应
        return new Result(Code.LOGIN_VERIFY_OK, token, "null");
    }
}
