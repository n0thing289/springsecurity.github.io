package com.weikai.mapper;

import com.weikai.pojo.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TUserMapper{

    TUser selectTUserByUsername(String userName);

    List<String> selectPermsByUserId(Long id);
}
