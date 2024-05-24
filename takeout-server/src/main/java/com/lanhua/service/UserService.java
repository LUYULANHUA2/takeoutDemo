package com.lanhua.service;

import com.lanhua.dto.UserLoginDTO;
import com.lanhua.entity.User;

public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}