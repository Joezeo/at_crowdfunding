package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.exception.LoginServiceException;
import com.joezeo.atcrowdfunding.common.utils.MD5Util;
import com.joezeo.atcrowdfunding.manager.mapper.UserMapper;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryLogin(Map<String, Object> loginInfo) {
        if(loginInfo == null){
            throw new LoginServiceException("ERROR：登录信息不可为空");
        }

        // 使用MD5加密密码
        String md5Pswd = MD5Util.digest((String) loginInfo.get("userpswd"));
        loginInfo.put("userpswd", md5Pswd);

        User user = userMapper.selectByLoginInfo(loginInfo);

        if(user == null){
            throw new LoginServiceException("用户名或密码错误~");
        }

        return user;
    }
}
