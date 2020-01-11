package com.joezeo.atcrowdfunding.potal.service;

import com.joezeo.atcrowdfunding.bean.Member;

import java.util.Map;

public interface MemberService {
    Member login(Map<String, Object> loginInfo);
}
