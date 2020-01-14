package com.joezeo.atcrowdfunding.potal.service;

import com.joezeo.atcrowdfunding.bean.Ticket;

public interface TicketService {
    Ticket queryByMemberid(Integer memberid);

    void addTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void updateTicketByMemberid(Ticket ticket);
}
