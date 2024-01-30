package com.weikai.mapper;

import com.weikai.pojo.MyUser;

public interface MyUserMapper {
    /**
     * 根据用户名查询user数据
     * @param username
     * @return
     */
    MyUser selectByUsername(String username);
}
