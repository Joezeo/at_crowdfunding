package com.joezeo.atcrowdfunding.potal.activiti.listener;

import com.joezeo.atcrowdfunding.bean.Member;
import com.joezeo.atcrowdfunding.bean.Ticket;
import com.joezeo.atcrowdfunding.common.utils.ApplicationContextUtil;
import com.joezeo.atcrowdfunding.potal.service.MemberService;
import com.joezeo.atcrowdfunding.potal.service.TicketService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

public class RefuseListener implements ExecutionListener {
    public void notify(DelegateExecution execution) throws Exception {
        // 审核拒绝
        // 获取TicketService 和 MemberService
        ApplicationContext applicationContext = ApplicationContextUtil.applicationContext;
        TicketService ticketService = applicationContext.getBean(TicketService.class);
        MemberService memberService = applicationContext.getBean(MemberService.class);

        // 获取memberid
        Integer memberid = Integer.parseInt((String) execution.getVariable("memberid"));

        // 将t_ticket 的status字段设置为1
        Ticket ticket = new Ticket();
        ticket.setMemberid(memberid);
        ticket.setStatus("1");
        ticketService.updateTicketByMemberid(ticket);

        // 将t_member 的authstatus字段设置为0
        Member member = new Member();
        member.setId(memberid);
        member.setAuthstatus("0");
        memberService.updateMember(member);
    }
}
