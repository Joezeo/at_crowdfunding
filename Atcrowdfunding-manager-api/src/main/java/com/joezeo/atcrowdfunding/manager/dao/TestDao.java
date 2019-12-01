package com.joezeo.atcrowdfunding.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {

    /**
     * 向测试表t_test中插入测试数据
     *
     * @param name 测试数据
     * @return 影响行数
     */
    int insertData(@Param("name") String name);

}
