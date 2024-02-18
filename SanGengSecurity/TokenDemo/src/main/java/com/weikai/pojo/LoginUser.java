package com.weikai.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginUser implements UserDetails {

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;//不需要存进redis

    private List<String> permissions;

    public List<String> getPermissions() {
        return permissions;
    }

    public LoginUser(User user, List<String> permissions) {
        this.permissions = permissions;
        this.user = user;
    }

    private User user;

    public LoginUser() {
    }

    public LoginUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            //如果authorities没有创建一次
            authorities = new ArrayList<>();
            //遍历permissions,取出authority
            if (permissions.isEmpty()){
                authorities.add(new SimpleGrantedAuthority("null"));
            }else{
                permissions.forEach(permission -> {
                    authorities.add(new SimpleGrantedAuthority(permission));
                });
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
