package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.exception.LoginServiceException;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.common.utils.MD5Util;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.manager.mapper.UserMapper;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryLogin(Map<String, Object> loginInfo) {
        if (loginInfo == null) {
            throw new LoginServiceException("ERROR：登录信息不可为空");
        }

        // 使用MD5加密密码
        String md5Pswd = MD5Util.digest((String) loginInfo.get("userpswd"));
        loginInfo.put("userpswd", md5Pswd);

        User user = userMapper.selectByLoginInfo(loginInfo);

        if (user == null) {
            throw new LoginServiceException("用户名或密码错误~");
        }

        return user;
    }

    public PageInfo queryUserByPage(Integer pageNum, Integer pageSize, String loginAcct) {
        if (pageNum == null || pageSize == null || pageNum <= 0 || pageSize <= 0) {
            throw new ServiceException("传入分页参数异常，pageNum=" + pageNum + ",pageSize=" + pageSize);
        }

        PageInfo pageInfo = new PageInfo(pageSize, pageNum);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageInfo", pageInfo);
        paramMap.put("loginAcct", loginAcct);

        List<User> list = userMapper.selectByPage(paramMap);
        if (list == null) {
            throw new ServiceException("获取分页数据异常，list=null");
        }
        pageInfo.setDatas(list);

        int count = userMapper.selectUserCount(loginAcct);
        if (count < 0) {
            throw new ServiceException("获取用户数量异常");
        }
        pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        return pageInfo;
    }

    public void insUser(User user) {
        if (user == null) {
            throw new ServiceException("插入的用户信息为空");
        }

        int index = userMapper.insert(user);

        if (index != 1) {
            throw new ServiceException("添加用户异常");
        }
    }
}
