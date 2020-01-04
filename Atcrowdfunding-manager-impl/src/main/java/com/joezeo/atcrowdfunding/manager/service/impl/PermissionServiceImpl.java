package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.manager.mapper.PermissionMapper;
import com.joezeo.atcrowdfunding.manager.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    public Permission queryAll() {
        Permission root = null;
        List<Permission> all = permissionMapper.selectAll();
        if (all == null) {
            throw new ServiceException("获取许可失败");
        }

        Map<Integer, Permission> map = new HashMap<Integer, Permission>();
        for (Permission each : all) {
            if (each.getPid() == null) {
                root = each;
            }
            map.put(each.getId(), each);
        }

        for(Permission each : all){
            if(each.getPid() != null){
                Permission parent = map.get(each.getPid());
                parent.getChildren().add(each);
            }
        }
        return root;
    }

    public Permission queryPermission(Integer id) {
        if(id == null || id<= 0){
            throw new ServiceException("传入的id错误");
        }

        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(permission == null){
            throw new ServiceException("修改权限信息数据回显错误");
        }
        return permission;
    }

    public void addPermission(Permission permission) {
        int count = permissionMapper.insert(permission);
        if(count != 1){
            throw new ServiceException("新增权限失败");
        }
    }

    public void removePermission(Integer id) {
        int count = permissionMapper.deleteByPrimaryKey(id);
        if(count != 1){
            throw new ServiceException("删除权限失败");
        }
    }

    public void updatePermission(Permission permission) {
        if(permission == null){
            throw new ServiceException("传入的参数不可为null");
        }

        int count = permissionMapper.updateByPrimaryKey(permission);
        if(count != 1){
            throw new ServiceException("修改权限信息失败");
        }
    }

    public List<Permission> queryAllPermissions() {
        List<Permission> list = permissionMapper.selectAll();
        if(list == null){
            throw new ServiceException("获取所有权限失败");
        }
        return list;
    }
}
