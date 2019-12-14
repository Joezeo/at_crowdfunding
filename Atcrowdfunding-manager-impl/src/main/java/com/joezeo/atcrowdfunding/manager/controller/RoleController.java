package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.Role;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role/")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("doPageQuery")
    @ResponseBody
    public JsonResult doPageQuery(String name, Integer pageSize, Integer pageNum) {
        JsonResult result = null;

        try {
            PageInfo pageInfo = roleService.queryRoleByPage(name, pageSize, pageNum);
            result = new JsonResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doAddRole")
    @ResponseBody
    public JsonResult doAddRole(String name) {
        JsonResult result = null;

        try {
            roleService.addRole(name);
            result = new JsonResult("新增角色成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doModifyRole")
    @ResponseBody
    public JsonResult doModifyRole(Role role){
        JsonResult result = null;

        try{
            roleService.updateRole(role);
            result = new JsonResult("修改角色成功");
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doQueryRole")
    @ResponseBody
    public JsonResult doQueryRole(Integer id){
        JsonResult result = null;

        try{
            Role role = roleService.queryRoleById(id);
            result = new JsonResult(role);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(result);
        } finally {
            return result;
        }
    }

    @RequestMapping("doRemoveRoles")
    @ResponseBody
    public JsonResult doRemoveRoles(@RequestParam("ids[]") Integer[] ids){
        JsonResult result = null;

        try{
            roleService.removeByIds(Arrays.asList(ids));
            result = new JsonResult("删除成功~");
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }
}
