package com.joezeo.atcrowdfunding.potal.controller;

import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.bean.Ticket;
import com.joezeo.atcrowdfunding.common.constant.Const;
import com.joezeo.atcrowdfunding.common.vo.JsonResult;
import com.joezeo.atcrowdfunding.potal.service.MemberService;
import com.joezeo.atcrowdfunding.potal.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TicketService ticketService;

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
        } else if("uploadfile".equals(pstep)){
            return "member/apply_2";
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
    public JsonResult doSaveBasicInfo(String realname, String cardnum, String phone, HttpSession session){
        JsonResult result = null;

        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
        member.setRealname(realname);
        member.setCardnum(cardnum);
        member.setPhone(phone);

        try{
            memberService.updateMember(member);
            // 修改该用户的流程单的pstrep为basicinfo
            Ticket ticket = ticketService.queryByMemberid(member.getId());
            ticket.setPstep("uploadfile");
            ticketService.updateTicket(ticket);

            result = new JsonResult("更新acctype成功");
        } catch (Exception e){
            e.printStackTrace();
            result = new JsonResult(e);
        } finally {
            return result;
        }
    }
}
