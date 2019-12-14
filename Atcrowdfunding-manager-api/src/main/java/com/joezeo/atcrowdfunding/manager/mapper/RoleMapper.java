package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAllRoles();

    List<Role> selectByPage(Map<String, Object> map);

    int selectCount(@Param("name") String name);

    int deleteByIds(@Param("ids") List<Integer> ids);
}