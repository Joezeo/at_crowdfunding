package com.joezeo.atcrowdfunding.web.controller;

import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.PermissionService;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import com.joezeo.atcrowdfunding.potal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DispacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @RequestMapping("/index")
    public String htmIndex(HttpServletRequest request, HttpSession session) {// 在进入主页时需检查是否有自动登录cookie
        //判断是否需要自动登录
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
        if(user != null || member!=null){
            return "index";
        }

        //如果之前登录过，cookie中存放了用户信息，需要获取cookie中的信息，并进行数据库的验证
        String logintype = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) { //如果客户端禁用了Cookie，那么无法获取Cookie信息
            for (Cookie cookie : cookies) {
                if ("logincode".equals(cookie.getName())) {
                    String logincode = cookie.getValue();
                    System.out.println("获取到Cookie中的键值对" + cookie.getName() + "===== " + logincode);
                    String[] split = logincode.split("&");

                    if (split.length == 3) {
                        String loginacct = split[0].split("=")[1];
                        String userpwd = split[1].split("=")[1];
                        logintype = split[2].split("=")[1];

                        if ("user".equals(logintype)) {
                            Map<String, Object> loginInfo = new HashMap<>();
                            loginInfo.put("loginacct", loginacct);
                            loginInfo.put("userpswd", userpwd);
                            User dbLogin = userService.queryLogin(loginInfo);

                            if (dbLogin != null) {
                                session.setAttribute(Const.LOGIN_USER, dbLogin);
                            }

                        } else if ("member".equals(logintype)) {
                            Map<String, Object> loginInfo = new HashMap<>();
                            loginInfo.put("loginacct", loginacct);
                            loginInfo.put("userpswd", userpwd);
                            Member dbLogin = memberService.login(loginInfo);

                            if (dbLogin != null) {
                                session.setAttribute(Const.LOGIN_MEMBER, dbLogin);
                            }
                        }
                    }
                }
            }
        }

        return "index";
}

    @RequestMapping("/login")
    public String htmLogin() {
        return "login";
    }

    @RequestMapping("/reg")
    public String htmReg() {
        return "reg";
    }

    @RequestMapping("/main")
    public String htmMain(HttpSession session) {
        // 在加载主页面时，需要获取当前用户的权限分配信息
        User user = (User) session.getAttribute(Const.LOGIN_USER);

        Permission root = userService.getUserPermissions(user.getId());

        session.setAttribute(Const.ROOT_PERMISSION, root);
        return "main";
    }

    @RequestMapping("/user")
    public String htmUser() {
        return "user/user";
    }

    @RequestMapping("/panel")
    public String htmPanel() {
        return "common/panel";
    }

    @RequestMapping("/user/add")
    public String htmUserAdd() {
        return "user/add";
    }

    @RequestMapping("/user/edit")
    public String htmUserEdit() {
        return "user/edit";
    }

    @RequestMapping("/user/assignRole")
    public String htmAssignRole() {
        return "user/assignRole";
    }

    @RequestMapping("/role")
    public String htmRole() {
        return "role/role";
    }

    @RequestMapping("/role/add")
    public String htmRoleAdd() {
        return "role/add";
    }

    @RequestMapping("/role/edit")
    public String htmRoleEdit() {
        return "role/edit";
    }

    @RequestMapping("/permission")
    public String htmPermisson() {
        return "permission/permission";
    }

    @RequestMapping("/permission/add")
    public String htmAddPermission() {
        return "permission/add";
    }

    @RequestMapping("/permission/update")
    public String htmUpdatePermission() {
        return "permission/update";
    }

    @RequestMapping("/role/assignPermission")
    public String htmAssignPermission() {
        return "role/assignPermission";
    }

    @RequestMapping("/advert")
    public String htmAdvert() {
        return "advert/advert";
    }

    @RequestMapping("/advert/add")
    public String htmAdvertAdd() {
        return "advert/add";
    }

    @RequestMapping("/cert")
    public String htmCert() {
        return "cert/cert";
    }

    @RequestMapping("/cert/add")
    public String htmCertAdd() {
        return "cert/add";
    }

    @RequestMapping("/cert/edit")
    public String htmCertEdit() {
        return "cert/edit";
    }

    @RequestMapping("/process")
    public String htmProcess() {
        return "process/process";
    }

    @RequestMapping("/member")
    public String htmMember() {
        return "member/member";
    }

    @RequestMapping("/member/home")
    public String htmMemberHome(){
        return "member/home";
    }

    // 同步请求
    /*@RequestMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd, String type, HttpSession session){
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("loginacct", loginacct);
        loginInfo.put("userpswd", userpswd);
        loginInfo.put("type", type);

        User user = userService.queryLogin(loginInfo);
        session.setAttribute(Const.LOGIN_USER, user);

        return "redirect:main.htm";
    }*/

    // 异步请求
    @RequestMapping("/doLogin")
    @ResponseBody
    public JsonResult
    doLogin(String loginacct, String userpswd, String type, String rememberMe, HttpSession session, HttpServletResponse response) {
        JsonResult result = null;
        try {
            Map<String, Object> loginInfo = new HashMap<>();
            loginInfo.put("loginacct", loginacct);
            loginInfo.put("userpswd", userpswd);
            loginInfo.put("type", type);

            if ("user".equals(type)) { // 以后台管理用户身份登录
                User user = userService.queryLogin(loginInfo);
                session.setAttribute(Const.LOGIN_USER, user);

                List<Permission> all = userService.getPermissionsByUserid(user.getId());
                Set<String> sets = new HashSet<>();
                for (Permission per : all) {
                    sets.add(per.getUrl());
                }
                session.setAttribute(Const.USER_URIS, sets);
            } else if ("member".equals(type)) { // 以前台会员身份登录
                Member member = memberService.login(loginInfo);
                session.setAttribute(Const.LOGIN_MEMBER, member);
            }

            if ("1".equals(rememberMe)) { // 选择记住我，创建cookie保存用户登录信息，需在登录成功后执行
                String logincode = "loginacct=" + loginacct + "&userpwd=" + userpswd + "&logintype=" + type;
                System.out.println("用户-存放到Cookie中的键值对：logincode::::::::::::" + logincode);
                Cookie c = new Cookie(Const.LOGIN_CODE, logincode);
                c.setMaxAge(60 * 60 * 24 * 14); //2周时间Cookie过期     单位秒
                c.setPath("/"); //表示任何请求路径都可以访问Cookie
                response.addCookie(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        result = new JsonResult("Login Ok~");
        return result;
    }

    @RequestMapping("/doLogout")
    public String doLogout(HttpSession session, HttpServletResponse response) {
        // 移除session中的用户信息
        session.removeAttribute(Const.LOGIN_USER);
        session.removeAttribute(Const.LOGIN_MEMBER);

        // 移除cookie保存登录信息，取消自动登录
        Cookie cookie = new Cookie(Const.LOGIN_CODE, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:index.htm";
    }
}
