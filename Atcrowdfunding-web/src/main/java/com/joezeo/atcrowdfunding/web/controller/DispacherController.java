package com.joezeo.atcrowdfunding.web.controller;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.utils.JsonResult;
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
    public String htmMain() {
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

    @RequestMapping("/add")
    public String htmAdd() {
        return "common/add";
    }

    @RequestMapping("/edit")
    public String htmEdit() {
        return "common/edit";
    }

    @RequestMapping("/role")
    public String hrmRole() {
        return "role/role";
    }

    @RequestMapping("/assignRole")
    public String htmAssignRole(){
        return "user/assignRole";
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
