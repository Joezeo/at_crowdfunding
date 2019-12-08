package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
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

    /**
     * 分页查询t_user表用户信息
     *
     * @param params 参数map，包括：pageInfo，loginAcct账号
     * @return List用户集合
     */
    List<User> selectByPage(Map<String, Object> params);

    /**
     * 查询所有用户数据的条数
     *
     * @return 所有用户数据的条数
     */
    int selectUserCount(@Param("loginAcct") String loginAcct);
}