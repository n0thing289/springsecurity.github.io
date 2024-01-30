package com.weikai.service;

import com.weikai.pojo.User;
import com.weikai.utils.Result;

public interface LoginService {
    Result login(User user);
}
