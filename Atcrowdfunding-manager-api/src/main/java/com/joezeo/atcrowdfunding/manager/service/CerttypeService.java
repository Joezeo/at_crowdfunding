package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.AccountTypeCert;

import java.util.List;

public interface CerttypeService {
    List<AccountTypeCert> queryAll();

    void addCerttype(AccountTypeCert certtype);

    void removeCerttype(AccountTypeCert certtype);
}
