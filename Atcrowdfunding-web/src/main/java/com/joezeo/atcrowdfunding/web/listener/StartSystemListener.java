package com.joezeo.atcrowdfunding.web.listener;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.manager.service.PermissionService;
import com.joezeo.atcrowdfunding.manager.service.impl.PermissionServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartSystemListener implements ServletContextListener {

    // 在服务器启动时，创建application对象时需要执行的方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 将项目上下文路径存入application中
        servletContext.setAttribute("APP_PATH", servletContext.getContextPath());
        System.out.println("项目上下文路径："+servletContext.getContextPath());

        // 将所有需控制的权限路径放至于application中
        ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        PermissionService permissionService = ioc.getBean("permissionServiceImpl", PermissionServiceImpl.class);

        List<Permission> list = permissionService.queryAllPermissions();
        Set<String> sets = new HashSet<>();
        for(Permission per : list){
            sets.add(per.getUrl());
        }
        servletContext.setAttribute(Const.ALL_URIS, sets);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
