package com.joezeo.atcrowdfunding.potal.service;

import com.joezeo.atcrowdfunding.bean.MemberCert;

import java.util.List;
import java.util.Map;

public interface MemberCertService {
    void addMemberCertList(List<MemberCert> certimgs);

    List<Map<String, Object>> queryCertsByMemberid(Integer memberid);
}
