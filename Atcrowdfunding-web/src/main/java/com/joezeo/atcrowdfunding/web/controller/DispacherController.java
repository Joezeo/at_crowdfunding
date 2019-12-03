package com.joezeo.atcrowdfunding.web.controller;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DispacherController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String htmIndex(){
        return "index";
    }

    @RequestMapping("/login")
    public String htmLogin(){
        return "login";
    }

    @RequestMapping("/reg")
    public String htmReg(){
        return "reg";
    }

    @RequestMapping("/main")
    public String htmMain(){
        return "main";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd, String type, HttpSession session){
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("loginacct", loginacct);
        loginInfo.put("userpswd", userpswd);
        loginInfo.put("type", type);

        User user = userService.queryLogin(loginInfo);
        session.setAttribute(Const.LOGIN_USER, user);

        return "redirect:main.htm";
    }
}
