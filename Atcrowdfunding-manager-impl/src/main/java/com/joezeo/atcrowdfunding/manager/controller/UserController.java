package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.utils.JsonResult;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("doPageQuery")
    @ResponseBody
    public JsonResult doPageQuery(Integer pageNum, Integer pageSize, String loginAcct){
        JsonResult result = null;

        try{
            PageInfo pageInfo = userService.queryUserByPage(pageNum, pageSize, loginAcct);
            result = new JsonResult(pageInfo);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    @RequestMapping("doAddUser")
    @ResponseBody
    public JsonResult doAddUser(User user){
        JsonResult result = null;

        try{
            userService.insUser(user);
            result = new JsonResult("新增成功");
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }
}
