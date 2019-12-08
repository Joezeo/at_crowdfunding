package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.manager.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        userService = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
    }

    /**
     * 往t_user表中插入100条测试数据
     */
    @Test
    public void insertTestDatasToUser(){
        for (int i=0; i< 100; i++){
            User user = new User();
            user.setLoginacct("testAcct_0"+(i+1));
            user.setEmail("testAcct_0"+(i+1)+"@qq.com");
            user.setCreatetime("2019-12-08 19:21:00");
            user.setUserpswd("202cb962ac59075b964b07152d234b70");
            user.setUsername("testName_0"+(i+1));
            userService.insUser(user);
        }
    }
}
