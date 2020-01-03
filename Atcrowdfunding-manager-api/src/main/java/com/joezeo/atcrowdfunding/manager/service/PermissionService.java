package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.Permission;

public interface PermissionService {
    Permission queryAll();

    Permission queryPermission(Integer id);

    void addPermission(Permission permission);

    void removePermission(Integer id);

    void updatePermission(Permission permission);
}
