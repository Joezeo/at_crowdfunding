package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectAll();

    List<Integer> selectIdByRoleId(Integer roleId);

    List<Permission> selectAllByUserid(Integer userid);
}