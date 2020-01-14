package com.joezeo.atcrowdfunding.potal.service.impl;

import com.joezeo.atcrowdfunding.bean.Ticket;
import com.joezeo.atcrowdfunding.common.exception.ServiceException;
import com.joezeo.atcrowdfunding.potal.mapper.TicketMapper;
import com.joezeo.atcrowdfunding.potal.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    private TicketMapper ticketMapper;

    public Ticket queryByMemberid(Integer memberid) {
        if(memberid==null || memberid<=0){
            throw new ServiceException("传入的会员id值异常");
        }

        Ticket ticket = ticketMapper.selectByMemberid(memberid);
        // 不需要判断ticket是否为null
        return ticket;
    }

    public void addTicket(Ticket ticket) {
        if(ticket == null){
            throw new ServiceException("传入的ticket值不可为null");
        }

        int count = ticketMapper.insertSelective(ticket);
        if(count != 1){
            throw new ServiceException("新增流程单失败");
        }
    }

    public void updateTicket(Ticket ticket) {
        if(ticket == null){
            throw new ServiceException("传入的ticket值不可为null");
        }

        int count = ticketMapper.updateByPrimaryKeySelective(ticket);
        if(count != 1){
            throw new ServiceException("更新流程单失败");
        }
    }

    public void updateTicketByMemberid(Ticket ticket) {
        if(ticket == null){
            throw new ServiceException("传入的ticket值不可为null");
        }

        int count = ticketMapper.updateByMemberidSelective(ticket);
        if(count != 1){
            throw new ServiceException("更新流程单失败");
        }
    }
}
