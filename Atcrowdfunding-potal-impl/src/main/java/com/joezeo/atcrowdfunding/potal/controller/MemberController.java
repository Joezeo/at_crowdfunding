package com.joezeo.atcrowdfunding.potal.controller;

import com.joezeo.atcrowdfunding.bean.Cert;
import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.bean.MemberCert;
import com.joezeo.atcrowdfunding.bean.Ticket;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.common.vo.ParamModel;
import com.joezeo.atcrowdfunding.manager.service.CertService;
import com.joezeo.atcrowdfunding.potal.activiti.listener.PassListener;
import com.joezeo.atcrowdfunding.potal.activiti.listener.RefuseListener;
import com.joezeo.atcrowdfunding.potal.service.MemberCertService;
import com.joezeo.atcrowdfunding.potal.service.MemberService;
import com.joezeo.atcrowdfunding.potal.service.TicketService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CertService certService;

    @Autowired
    private MemberCertService memberCertService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("apply")
    public String doApply(HttpSession session) {
        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
        Ticket ticket = ticketService.queryByMemberid(member.getId());

        if (ticket == null) { // 之前会员并没有提交过申请流程，从头开始
            ticket = new Ticket();
            ticket.setMemberid(member.getId());
            ticket.setPstep("accttype");
            ticketService.addTicket(ticket);
        }

        String pstep = ticket.getPstep();
        if ("accttype".equals(pstep)) {
            return "member/accttype";
        } else if ("basicinfo".equals(pstep)) {
            return "member/apply_1";
        } else if ("uploadfile".equals(pstep)) {
            // 加载该会员需上传的资质信息
            List<Cert> list = certService.queryCertByAccttype(member.getAccttype());
            session.setAttribute("certs", list);

            return "member/apply_2";
        } else if ("inputmail".equals(pstep)) {
            return "member/apply_3";
        } else if("checkemail".equals(pstep)){
            return "member/apply_4";
        }

        return "";
    }

    @RequestMapping("doUpdateAccttype")
    @ResponseBody
    public JsonResult doUpdateAccttype(String accttype, HttpSession session) {
        JsonResult result = null;

        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
        member.setAccttype(accttype);

        try {
            memberService.updateMember(member);
            // 修改该用户的流程单的pstrep为basicinfo
            Ticket ticket = ticketService.queryByMemberid(member.getId());
            ticket.setPstep("basicinfo");
            ticketService.updateTicket(ticket);

            result = new JsonResult("更新acctype成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doSaveBasicInfo")
    @ResponseBody
    public JsonResult doSaveBasicInfo(String realname, String cardnum, String phone, HttpSession session) {
        JsonResult result = null;

        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
        member.setRealname(realname);
        member.setCardnum(cardnum);
        member.setPhone(phone);

        try {
            memberService.updateMember(member);
            // 修改该用户的流程单的pstrep为basicinfo
            Ticket ticket = ticketService.queryByMemberid(member.getId());
            ticket.setPstep("uploadfile");
            ticketService.updateTicket(ticket);

            result = new JsonResult("更新acctype成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }

    @RequestMapping("doSaveCert")
    @ResponseBody
    public JsonResult doSaveBasicInfo(HttpSession session, ParamModel param) {
        JsonResult result = null;

        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

        String realpath = session.getServletContext().getRealPath("/pics/cert");
        try {
            for (MemberCert memberCert : param.getCertimgs()) {
                MultipartFile imgFile = memberCert.getImgFile();
                String oldname = imgFile.getOriginalFilename();
                String tmpname = UUID.randomUUID().toString() + oldname.substring(oldname.lastIndexOf("."));
                String newname = realpath + "/" + tmpname;

                // 将上传的资质图片存储到硬盘中
                imgFile.transferTo(new File(newname));

                // 封装数据
                memberCert.setIconpath(tmpname);
                memberCert.setMemberid(member.getId());
            }

            memberCertService.addMemberCertList(param.getCertimgs());

            // 修改该用户的流程单的pstrep为inputmail
            Ticket ticket = ticketService.queryByMemberid(member.getId());
            ticket.setPstep("inputmail");
            ticketService.updateTicket(ticket);

            result = new JsonResult("保存会员资质成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }


    @RequestMapping("doStartProcess")
    @ResponseBody
    public JsonResult doStartProcess(HttpSession session, String email) {
        JsonResult result = null;

        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

        try {
            Ticket ticket = ticketService.queryByMemberid(member.getId());

            // 验证输入的邮箱与注册时输入的邮箱是否相同，如不同更改用户的注册邮箱
            if(!member.getEmail().equals(email)){
                member.setEmail(email);
                memberService.updateMember(member);
            }

            // 随机生成验证码
            String authcode = "" + (new Random().nextInt(9000) + 1000);

            // 存储启动流程实例所需的变量
            Map<String, Object> varablies = new HashMap<String, Object>();
            varablies.put("targetMail", email);
            varablies.put("authcode", authcode);
            varablies.put("loginacct", member.getLoginacct());
            varablies.put("passListener", new PassListener());
            varablies.put("refuseListener", new RefuseListener());

            ProcessInstance verifyProcess = runtimeService.startProcessInstanceByKey("verifyProcess", varablies);

            // 修改该用户的流程单的pstrep为checkemail,更新ppid,authcode
            ticket.setPstep("checkemail");
            ticket.setPiid(verifyProcess.getId());
            ticket.setAuthcode(authcode);
            ticketService.updateTicket(ticket);

            result = new JsonResult("发送验证码成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            result.setMessage("发送验证码失败，请检查邮箱后再试");
        } finally {
            return result;
        }
    }



    @RequestMapping("doVerifyAuthcode")
    @ResponseBody
    public JsonResult doVerifyAuthcode(HttpSession session, String authcode) {
        JsonResult result = null;

        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

        try {
            Ticket ticket = ticketService.queryByMemberid(member.getId());

            // 验证用户输入的验证码是否于之前发送的验证码相同
            if(ticket.getAuthcode().equals(authcode)){
                Task task = taskService.createTaskQuery().processInstanceId(ticket.getPiid()).taskAssignee(member.getLoginacct()).singleResult();
                taskService.complete(task.getId());

                // 修改该用户的流程单的pstrep为finish
                ticket.setPstep("finish");
                ticketService.updateTicket(ticket);

                // 修改用户的authstatus为1
                member.setAuthstatus("1");
                memberService.updateMember(member);

                result = new JsonResult("发送验证码成功");
            } else {
                result = new JsonResult();
                result.setSuccess(false);
                result.setMessage("验证码不正确！请检查后重新输入，或重新获取验证码");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(e);
            result.setMessage("验证码不正确！请检查后重新输入，或重新获取验证码");
        } finally {
            return result;
        }
    }
}
