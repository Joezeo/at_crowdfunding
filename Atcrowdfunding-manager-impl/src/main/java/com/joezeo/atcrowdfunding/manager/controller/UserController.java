package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.User;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.common.vo.ParamModel;
import com.joezeo.atcrowdfunding.manager.service.UserRoleService;
import com.joezeo.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 执行分页查询
     *
     * @param pageNum   当前页码
     * @param pageSize  每页显示条数
     * @param loginAcct 模糊查询时的查询条件，可为空，为空时插叙所有数据
     * @return JsonResult
     */
    @RequestMapping("doPageQuery")
    @ResponseBody
    public JsonResult doPageQuery(Integer pageNum, Integer pageSize, String loginAcct) {
        JsonResult result = null;

        try {
            PageInfo pageInfo = userService.queryUserByPage(pageNum, pageSize, loginAcct);
            result = new JsonResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    /**
     * 添加新用户
     *
     * @param user 需要添加的用户信息
     * @return
     */
    @RequestMapping("doAddUser")
    @ResponseBody
    public JsonResult doAddUser(User user) {
        JsonResult result = null;

        try {
            userService.insUser(user);
            result = new JsonResult("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    /**
     * 修改用户信息
     *
     * @param user 需要修改的用户信息
     * @return JsonResult
     */
    @RequestMapping("doModifyUser")
    @ResponseBody
    public JsonResult doModifyUser(User user) {
        JsonResult result = null;

        try {
            userService.updUser(user);
            result = new JsonResult("修改成功");
        } catch (Exception e) {
            result = new JsonResult(e);
            e.printStackTrace();
            return result;
        }

        return result;
    }

    @RequestMapping("/doQueryUser")
    @ResponseBody
    public JsonResult doQueryUser(Integer id) {
        JsonResult result = null;

        try {
            User user = userService.queryById(id);
            result = new JsonResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    @RequestMapping("doDeleteUser")
    @ResponseBody
    public JsonResult doDeleteUser(Integer id) {
        JsonResult result = null;

        try {
            userService.deleteById(id);
            result = new JsonResult("删除用户成功~");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    @RequestMapping("doDeleteUserBatch")
    @ResponseBody
    public JsonResult doDeleteUserBatch(String ids) {
        JsonResult result = null;

        try {
            userService.deleteUserBatch(ids);
            result = new JsonResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    @RequestMapping("doGetUserRoles")
    @ResponseBody
    public JsonResult doGetUserRoles(Integer userId) {
        JsonResult result = null;

        try {
            Map<String, Object> map = userService.queryRolesByUsrid(userId);
            result = new JsonResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    @RequestMapping("doAddUserRoleMapper")
    @ResponseBody
    public JsonResult doAddUserRoleMapper(Integer userid, @RequestParam("idArr[]") Integer[] idArr) {
        JsonResult result = null;

        try {
            userRoleService.addRelationship(userid, Arrays.asList(idArr));
            result = new JsonResult("添加用户-角色映射成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }

    @RequestMapping("doRemoveUserRoleMapper")
    @ResponseBody
    public JsonResult doRemoveUserRoleMapper(Integer userid, @RequestParam("idArr[]") Integer[] idArr) {
        JsonResult result = null;

        try {
            userRoleService.removeRelationship(userid, Arrays.asList(idArr));
            result = new JsonResult("删除用户-角色映射成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            return result;
        }

        return result;
    }
}
