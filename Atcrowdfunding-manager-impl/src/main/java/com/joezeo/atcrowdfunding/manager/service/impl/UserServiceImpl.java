package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.bean.Role;
import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.exception.LoginServiceException;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.common.utils.MD5Util;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.manager.mapper.PermissionMapper;
import com.joezeo.atcrowdfunding.manager.mapper.RoleMapper;
import com.joezeo.atcrowdfunding.manager.mapper.UserMapper;
import com.joezeo.atcrowdfunding.manager.mapper.UserRoleMapper;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    public User queryLogin(Map<String, Object> loginInfo) {
        if (loginInfo == null) {
            throw new LoginServiceException("ERROR：登录信息不可为空");
        }

        // 使用MD5加密密码
        String md5Pswd = MD5Util.digest((String) loginInfo.get("userpswd"));
        loginInfo.put("userpswd", md5Pswd);

        User user = userMapper.selectByLoginInfo(loginInfo);

        if (user == null) {
            throw new LoginServiceException("用户名或密码错误~");
        }

        return user;
    }

    public PageInfo queryUserByPage(Integer pageNum, Integer pageSize, String loginAcct) {
        if (pageNum == null || pageSize == null || pageNum <= 0 || pageSize <= 0) {
            throw new ServiceException("传入分页参数异常，pageNum=" + pageNum + ",pageSize=" + pageSize);
        }

        //因为如果loginAcct传来的是%
        //如果不对其进行转译那么他就会把所有数据都查询出来 不安全
        if("%".equals(loginAcct)){
            // java代码中/本身也需要转译 两个/代表一个/
            // 又由于mysql数据库中/也需要转译
            // 所以理论上需要传入//%
            // 加上转译就是 ////%
            loginAcct = "////%";
        }

        PageInfo pageInfo = new PageInfo(pageSize, pageNum);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageInfo", pageInfo);
        paramMap.put("loginAcct", loginAcct);

        List<User> list = userMapper.selectByPage(paramMap);
        if (list == null) {
            throw new ServiceException("获取分页数据异常，list=null");
        }
        pageInfo.setDatas(list);

        int count = userMapper.selectUserCount(loginAcct);
        if (count < 0) {
            throw new ServiceException("获取用户数量异常");
        }
        pageInfo.setPageTotal(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

        return pageInfo;
    }

    public void insUser(User user) {
        // 前台传来的用户数据有 loginAcct、username、email
        if (user == null) {
            throw new ServiceException("插入的用户信息为空");
        }

        String md5Pswd = MD5Util.digest(Const.DEFAULT_INIT_PASSWORD);
        user.setUserpswd(md5Pswd);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(new Date());
        user.setCreatetime(date);

        int index = userMapper.insert(user);

        if (index != 1) {
            throw new ServiceException("添加用户异常");
        }
    }

    public void updUser(User user) {
        if(user == null){
            throw new ServiceException("修改：传入的用户信息为空");
        }

        int index = userMapper.updateByPrimaryKey(user);

        if(index != 1){
            throw new ServiceException("修改用户信息错误");
        }
    }

    public User queryById(Integer id) {
        if(id == null || id <= 0){
            throw new ServiceException("查询用户信息的id错误，id="+id);
        }

        User user = userMapper.selectByPrimaryKey(id);

        if(user == null){
            throw new ServiceException("查询用户信息异常，请重试");
        }
        return user;
    }

    public void deleteById(Integer id) {
        if(id == null || id <=0 ){
            throw new ServiceException("待删除用户id错误，id="+id);
        }

        int index = userMapper.deleteByPrimaryKey(id);

        if(index != 1){
            throw new ServiceException("删除用户失败~");
        }
    }

    public void deleteUserBatch(String ids) {
        if(ids == null || ids == ""){
            throw new ServiceException("传入的id数组不可为空！！");
        }
        List<Integer> idList = new ArrayList<Integer>();

        String[] arr = ids.split("&");
        for(String idKV : arr){
            String[] tmp = idKV.split("=");
            idList.add(Integer.parseInt(tmp[1]));
        }

        for(Integer id : idList){
            deleteById(id);
        }
    }

    public Map<String, Object> queryRolesByUsrid(Integer userId) {
        if(userId == null || userId<=0){
            throw new ServiceException("传入的用户id异常，id="+userId);
        }

        List<Role> allRoles = roleMapper.selectAllRoles();
        List<Integer> assignedRoleIds = userRoleMapper.selectRoleIdsByUserId(userId);

        if(allRoles == null){
            throw new ServiceException("获取所有角色信息异常~");
        }
        if(assignedRoleIds == null){
            throw new ServiceException("获取已分配角色id异常~");
        }

        List<Role> leftRoleList = new ArrayList<Role>(); // 左边，未分配的角色列表
        List<Role> rightRoleList = new ArrayList<Role>(); // 右边，已分配的角色列表

        for(Role role : allRoles){
            if(assignedRoleIds.contains(role.getId())){
                // 该角色已被分配
                rightRoleList.add(role);
            } else {
                leftRoleList.add(role);
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("leftRoleList", leftRoleList);
        map.put("rightRoleList",rightRoleList);

        return map;
    }

    public Permission getUserPermissions(Integer userid) {
        if(userid == null || userid <= 0){
            throw new ServiceException("传入的用户id值异常");
        }
        // 首先获取该用户所有权限信息
        List<Permission> all = permissionMapper.selectAllByUserid(userid);
        if(all == null){
            throw new ServiceException("获取该用户的权限失败");
        }

        Permission root = null;
        Map<Integer, Permission> map = new HashMap<Integer, Permission>();

        for(Permission per : all){
            if(per.getPid() == null){
                root = per;
            }

            map.put(per.getId(), per);
        }

        for(Permission per : all){
            if(per.getPid() != null){
                map.get(per.getPid()).getChildren().add(per);
            }
        }

        return root;
    }

    public List<Permission> getPermissionsByUserid(Integer userid) {
        List<Permission> all = permissionMapper.selectAllByUserid(userid);
        if(all == null){
            throw new ServiceException("获取该用户的权限失败");
        }
        return all;
    }

}
