package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.Cert;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;

public interface CertService {
    PageInfo queryCertByPage(Integer pageSize, Integer pageNum, String name);

    void addCert(Cert cert);

    void deleteCertBatch(Integer[] ids);

    void deleteCert(Integer id);

    Cert queryCert(Integer id);
}
