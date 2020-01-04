package com.joezeo.atcrowdfunding.web.interceptor;

import com.joezeo.atcrowdfunding.common.constant.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 访问权限拦截器（用于用户登录后的权限拦截）
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();

        // 1.判断当前访问地址是否存在于数据库权限表中
        Set<String> allUris = (Set<String>) request.getSession().getServletContext().getAttribute(Const.ALL_URIS);
        if(allUris.contains(servletPath)){
            // 在用户登录时将该用户的可访问权限uri存入session中
            Set<String> userUris = (Set<String>) request.getSession().getAttribute(Const.USER_URIS);
            if(userUris.contains(servletPath)){
                return true;
            } else {
                // 直接返回主页面
                response.sendRedirect(request.getContextPath()+"/main.htm");
                return false;
            }
        } else {
            return true; // 访问的地址不在控制范围之内，放行
        }
    }
}
