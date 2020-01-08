package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.common.utils.PageInfo;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cert/")
public class CertController {

    @Autowired
    private CertService certService;

    @RequestMapping("doQueryPage")
    @ResponseBody
    public JsonResult doQueryPage(String name, Integer pageSize, Integer pageNum){
        JsonResult result = null;

        try{
            PageInfo pageInfo = certService.queryCertByPage(pageSize, pageNum, name);
            result = new JsonResult(pageInfo);
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }
}
