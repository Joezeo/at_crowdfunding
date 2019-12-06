package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;

import java.util.Map;

public interface UserService {
    /**
     * 根据用户登录信息在数据库进行登录查询
     *
     * @param loginInfo loginacct：登录账号 userpswd：密码
     * @return 查询到的User对象 或者 null
     */
    User queryLogin(Map<String, Object> loginInfo);

    /**
     * 分页查询用户信息
     *
     * @param pageNum 当前页码
     * @param pageSize 每页显示数据条数
     * @return 分页信息对象PageInfo
     */
    PageInfo queryUserByPage(Integer pageNum, Integer pageSize);
}
