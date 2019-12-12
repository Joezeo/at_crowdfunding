package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<Integer> selectRoleIdsByUserId(Integer userId);

    // mybatis 不能重载方法

    int insertRelationship(@Param("userid") Integer userid, @Param("roleids") List<Integer> roleids);

    int deleteRelationship(@Param("userid") Integer userid, @Param("roleids") List<Integer> roleids);
}