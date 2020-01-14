package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.Member;

public interface AuthcertService {
    Member queryMemberByPiid(String processInstanceId);
}
