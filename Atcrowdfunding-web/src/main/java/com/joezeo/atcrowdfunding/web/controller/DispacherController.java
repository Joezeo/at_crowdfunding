package com.joezeo.atcrowdfunding.web.controller;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DispacherController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String htmIndex() {
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
    public String htmAssignRole(){
        return "user/assignRole";
    }

    @RequestMapping("/role")
    public String htmRole() {
        return "role/role";
    }

    @RequestMapping("/role/add")
    public String htmRoleAdd(){
        return "role/add";
    }

    @RequestMapping("/role/edit")
    public String htmRoleEdit(){
        return "role/edit";
    }

    @RequestMapping("/permission")
    public String htmPermisson(){
        return "permission/permission";
    }

    @RequestMapping("/permission/add")
    public String htmAddPermission(){
        return "permission/add";
    }

    @RequestMapping("/permission/update")
    public String htmUpdatePermission(){
        return "permission/update";
    }

    @RequestMapping("/role/assignPermission")
    public String htmAssignPermission(){
        return "role/assignPermission";
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
    public JsonResult doLogin(String loginacct, String userpswd, String type, HttpSession session) {
        JsonResult result = null;
        try {
            Map<String, Object> loginInfo = new HashMap<>();
            loginInfo.put("loginacct", loginacct);
            loginInfo.put("userpswd", userpswd);
            loginInfo.put("type", type);

            User user = userService.queryLogin(loginInfo);
            session.setAttribute(Const.LOGIN_USER, user);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        result = new JsonResult("Login Ok~");
        return result;
    }

    @RequestMapping("/doLogout")
    public String doLogout(HttpSession session) {
        session.removeAttribute(Const.LOGIN_USER);
        return "redirect:index.htm";
    }
}
