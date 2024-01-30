package com.weikai.mapper;

import com.weikai.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from sys_user where username = #{username}")
    User selectByUsername(String username);
}
