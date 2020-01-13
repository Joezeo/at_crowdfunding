package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.bean.AccountTypeCert;
import com.joezeo.atcrowdfunding.bean.Cert;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.manager.service.CertService;
import com.joezeo.atcrowdfunding.manager.service.CerttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/certtype/")
public class CerttypeController {

    @Autowired
    private CertService certService;

    @Autowired
    private CerttypeService certtypeService;

    @RequestMapping("/certtype")
    public String htmCerttype(HttpSession session){
        List<Cert> list = certService.queryAll();
        session.setAttribute("certs", list);
        return "certtype/certtype";
    }

    @RequestMapping("doLoadData")
    @ResponseBody
    public JsonResult doLoadData(){
        JsonResult jsonResult = null;

        try {
            List<AccountTypeCert> list = certtypeService.queryAll();
            jsonResult = new JsonResult(list);
        } catch (Exception e){
            e.printStackTrace();
            jsonResult = new JsonResult(e);
        } finally {
            return jsonResult;
        }
    }

    @RequestMapping("doAddCerttype")
    @ResponseBody
    public JsonResult doAddCerttype(String accttype, Integer certid){
        JsonResult jsonResult = null;

        try {
            AccountTypeCert certtype = new AccountTypeCert();
            certtype.setAccttype(accttype);
            certtype.setCertid(certid);
            certtypeService.addCerttype(certtype);
            jsonResult = new JsonResult("新增会员资质OK");
        } catch (Exception e){
            e.printStackTrace();
            jsonResult = new JsonResult(e);
        } finally {
            return jsonResult;
        }
    }

    @RequestMapping("doRemoveCerttype")
    @ResponseBody
    public JsonResult doRemoveCerttype(String accttype, Integer certid){
        JsonResult jsonResult = null;

        try {
            AccountTypeCert certtype = new AccountTypeCert();
            certtype.setAccttype(accttype);
            certtype.setCertid(certid);
            certtypeService.removeCerttype(certtype);
            jsonResult = new JsonResult("删除会员资质OK");
        } catch (Exception e){
            e.printStackTrace();
            jsonResult = new JsonResult(e);
        } finally {
            return jsonResult;
        }
    }
}
