package com.joezeo.atcrowdfunding.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartSystemListener implements ServletContextListener {

    // 在服务器启动时，创建application对象时需要执行的方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 将项目上下文路径存入application中
        servletContext.setAttribute("APP_PATH", servletContext.getContextPath());
        System.out.println("项目上下文路径："+servletContext.getContextPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
