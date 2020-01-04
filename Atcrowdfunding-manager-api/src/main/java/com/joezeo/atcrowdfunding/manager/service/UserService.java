package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;

import java.util.Map;

public interface UserService {
    /**
     * 根据用户登录信息在数据库进行登录查询
     *
     * @param loginInfo loginacct：登录账号 userpswd：密码
     * @return 查询到的User对象 或者 null
     */
    User queryLogin(Map<String, Object> loginInfo);

    /**
     * 分页查询用户信息
     *
     * @param pageNum 当前页码
     * @param pageSize 每页显示数据条数
     * @return 分页信息对象PageInfo
     */
    PageInfo queryUserByPage(Integer pageNum, Integer pageSize, String loginAcct);

    /**
     * 插入新的user账户
     * tips：所有处理结果应当在service层处理，故没有返回值
     *
     * @param user
     */
    void insUser(User user);

    /**
     * 修改用户信息
     * @param user 需要修改的用户信息
     */
    void updUser(User user);

    /**
     * 根据主键id查询用户信息
     * @param id 主键id
     * @return 查询到的用户信息
     */
    User queryById(Integer id);

    /**
     * 根据主键id进行用户删除
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 批量删除用户
     * @param ids 需要删除的用户id
     */
    void deleteUserBatch(String ids);

    /**
     * 根据用户id查询该用户的角色分配信息
     * 分别将已经分配的角色信息和未分配的角色信息放入不同的list中
     * 然后保存在map集合中
     *
     * @param userId 用户id
     * @return
     */
    Map<String, Object> queryRolesByUsrid(Integer userId);

    /**
     * 根据用户id查询该用户的权限分配信息
     * 用于加载菜单
     * @param userid 用户id
     * @return 根权限对象
     */
    Permission getUserPermissions(Integer userid);
}