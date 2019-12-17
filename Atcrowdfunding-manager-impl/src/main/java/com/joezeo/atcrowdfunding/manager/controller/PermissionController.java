package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("doGetAll")
    @ResponseBody
    public JsonResult doGetPermissions(){
        JsonResult result = null;

        try{
            Permission root = permissionService.queryAll();
            result = new JsonResult(root);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }
}
