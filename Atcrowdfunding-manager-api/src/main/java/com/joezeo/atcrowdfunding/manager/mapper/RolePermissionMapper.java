package com.joezeo.atcrowdfunding.manager.mapper;

import com.joezeo.atcrowdfunding.bean.RolePermission;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int insertByRoleid(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

    int deleteByRoleid(Integer roleId);
}