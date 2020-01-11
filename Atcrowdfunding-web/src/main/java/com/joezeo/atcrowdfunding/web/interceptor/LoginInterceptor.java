package com.joezeo.atcrowdfunding.web.interceptor;

import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Set<String> uris;

    static {
        uris = new HashSet<>();
        uris.add("/index.htm");
        uris.add("/reg.htm");
//        uris.add("/login.htm");
        uris.add("/doReg.do");
        uris.add("/doLogin.do");
        uris.add("/doLogout.do");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if (uris.contains(servletPath)) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

        if (user == null && member == null) {
            if (!"/login.htm".equals(servletPath)) {
                response.sendRedirect(request.getContextPath() + "/login.htm");
                return false;
            } else {
                return true;
            }
        } else {
            // 如果已经登录了再次访问登录页面则直接返回主页
            if ("/login.htm".equals(servletPath) || "/doLogin.do".equals(servletPath)) {
                response.sendRedirect(request.getContextPath() + "/index.htm");
                return false;
            } else {
                return true;
            }
        }
    }
}
