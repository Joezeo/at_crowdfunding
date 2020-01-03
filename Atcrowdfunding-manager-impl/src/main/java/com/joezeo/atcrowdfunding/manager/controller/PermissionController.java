package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.Permission;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;

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

    @RequestMapping("doGetOne")
    @ResponseBody
    public JsonResult doGetPermission(Integer id){
        JsonResult result = null;

        try{
            Permission permission = permissionService.queryPermission(id);
            result = new JsonResult(permission);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doAddPermission")
    @ResponseBody
    public JsonResult doAddPermission(Permission permission){
        JsonResult result = null;

        try{
            permissionService.addPermission(permission);
            result = new JsonResult(true);
        } catch (Exception e){
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doDeletePermission")
    @ResponseBody
    public JsonResult doDeletePermission(Integer id){
        JsonResult result = null;

        try{
            permissionService.removePermission(id);
            result = new JsonResult(true);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doUpdate")
    @ResponseBody
    public JsonResult doUpdatePermission(Permission permission){
        JsonResult result = null;

        try{
            permissionService.updatePermission(permission);
            result = new JsonResult(true);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }
}
