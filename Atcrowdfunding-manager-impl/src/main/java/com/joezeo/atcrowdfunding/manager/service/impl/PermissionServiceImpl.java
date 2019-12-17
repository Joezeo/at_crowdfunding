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
}
