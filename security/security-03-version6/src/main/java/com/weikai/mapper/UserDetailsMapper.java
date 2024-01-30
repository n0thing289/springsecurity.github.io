package com.weikai.mapper;

import com.weikai.pojo.UserDetailsEntity;

public interface UserDetailsMapper{
    /**
     * 根据用户名查询,返回数据库中user数据并封装
     * @param username
     * @return
     */
    UserDetailsEntity selectUserByUsername(String username);
}
