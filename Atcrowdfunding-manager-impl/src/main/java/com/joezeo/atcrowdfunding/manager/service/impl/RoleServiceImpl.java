package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.bean.Role;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.manager.mapper.PermissionMapper;
import com.joezeo.atcrowdfunding.manager.mapper.RoleMapper;
import com.joezeo.atcrowdfunding.manager.mapper.RolePermissionMapper;
import com.joezeo.atcrowdfunding.manager.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    public Role queryRoleById(Integer id) {
        if(id == null || id<=0 ){
            throw new ServiceException("查询角色信息：传入id异常");
        }
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role == null){
            throw new ServiceException("查询角色信息失败");
        }

        return role;
    }

    public PageInfo queryRoleByPage(String name, Integer pageSize, Integer pageNum) {
        if(pageSize == null || pageSize<=0 || pageNum==null || pageNum<=0){
            throw new ServiceException("传入分页相关参数错误，查询失败");
        }

        PageInfo pageInfo = new PageInfo(pageSize, pageNum);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageInfo", pageInfo);
        map.put("name", name);

        List<Role> list = roleMapper.selectByPage(map);
        if (list == null) {
            throw new ServiceException("分页查询角色信息失败");
        }
        pageInfo.setDatas(list);

        int count = roleMapper.selectCount(name);
        if (count < 0) {
            throw new ServiceException("分页查询角色信息失败，count<0");
        }
        pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        return pageInfo;
    }

    public void addRole(String name) {
        if(name == null || "".equals(name)){
            throw new ServiceException("输入的角色名称异常，name="+name);
        }

        Role role = new Role();
        role.setName(name);

        int index = roleMapper.insertSelective(role);
        if(index != 1){
            throw new ServiceException("新增角色异常，请稍后重试");
        }
    }

    public void updateRole(Role role) {
        if(role == null){
            throw new ServiceException("UpdateRole：传入参数不可为空");
        }

        int index = roleMapper.updateByPrimaryKey(role);

        if(index != 1){
            throw new ServiceException("修改角色信息失败，请稍后重试");
        }
    }

    public void removeByIds(List<Integer> ids) {
        if(ids == null){
            throw new ServiceException("删除角色：传入的参数不可为空");
        }

        int index = roleMapper.deleteByIds(ids);
        if(index <= 0){
            throw new ServiceException("删除角色失败");
        }
    }

    public Permission queryPermissions(Integer id) {
        if(id == null || id <=0 ){
            throw new ServiceException("传入的id值异常");
        }
        Permission root = null;
        // 获取所有权限
        List<Permission> all = permissionMapper.selectAll();
        if (all == null) {
            throw new ServiceException("获取许可失败");
        }
        // 获取该角色已分配的权限
        List<Integer> list = permissionMapper.selectIdByRoleId(id);
        if(list == null){
            throw new ServiceException("获取该角色已分配的权限异常");
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

            if (list.contains(each.getId())){
                each.setChecked(true);
            }
        }
        return root;
    }

    public void assignPermission(Integer id, Integer[] ids) {
        if(id == null || id <=0 ){
            throw new ServiceException("传入的角色id参数异常");
        }
        if(ids == null){
            throw new ServiceException("传入的需分配的权限id异常");
        }
        // 首先删除旧的信息
        int count = rolePermissionMapper.deleteByRoleid(id);
        if(count < 0){
            throw new ServiceException("删除旧的权限信息异常");
        }

        for(Integer perId : ids){
            count = rolePermissionMapper.insertByRoleid(id, perId);
            if(count != 1){
                throw new ServiceException("添加权限信息异常");
            }
        }
    }
}
