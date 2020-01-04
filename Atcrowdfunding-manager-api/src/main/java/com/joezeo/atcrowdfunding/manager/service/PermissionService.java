package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.Permission;

import java.util.List;

public interface PermissionService {
    Permission queryAll();

    Permission queryPermission(Integer id);

    void addPermission(Permission permission);

    void removePermission(Integer id);

    void updatePermission(Permission permission);

    List<Permission> queryAllPermissions();
}
