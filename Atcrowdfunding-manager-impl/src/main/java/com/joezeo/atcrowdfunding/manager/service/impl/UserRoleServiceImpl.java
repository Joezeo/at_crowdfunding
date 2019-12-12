package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.manager.mapper.UserRoleMapper;
import com.joezeo.atcrowdfunding.manager.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    public void addRelationship(Integer userid, List<Integer> roleids) {
        if(userid == null || userid <= 0){
            throw new ServiceException("传入的用户id异常，id="+userid);
        }

        if(roleids == null){
            throw new ServiceException("角色id集合不可为空");
        }

        int index = userRoleMapper.insertRelationship(userid, roleids) ;

        if(index <= 0){
            throw new ServiceException("添加用户-角色映射失败");
        }
    }

    public void removeRelationship(Integer userid, List<Integer> roleids) {
        if(userid == null || userid <= 0){
            throw new ServiceException("传入的用户id异常，id="+userid);
        }

        if(roleids == null){
            throw new ServiceException("角色id集合不可为空");
        }

        int index = userRoleMapper.deleteRelationship(userid, roleids) ;

        if(index <= 0){
            throw new ServiceException("删除用户-角色映射失败");
        }
    }
}
