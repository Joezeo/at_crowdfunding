package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.User;

import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户登录信息在数据库进行登录查询
     *
     * @param loginInfo loginacct：登录账号 userpswd：密码
     * @return 查询到的User对象 或者 null
     */
    User selectByLoginInfo(Map<String,Object> loginInfo);
}