package com.joezeo.atcrowdfunding.manager.service;

import java.util.List;

public interface UserRoleService {
    /**
     * 在t_user_role表中添加关系映射
     * @param userid 用户id
     * @param roleids 角色id集合
     */
    void addRelationship(Integer userid, List<Integer> roleids);

    /**
     * 在t_user_role表中删除关系映射
     * @param userid 用户id
     * @param roleids 角色id集合
     */
    void removeRelationship(Integer userid, List<Integer> roleids);
}
