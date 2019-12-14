package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.Role;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;

import java.util.List;

public interface RoleService {
    Role queryRoleById(Integer id);

    PageInfo queryRoleByPage(String name, Integer pageSize, Integer pageNum);

    void addRole(String name);

    void updateRole(Role role);

    void removeByIds(List<Integer> ids);
}
