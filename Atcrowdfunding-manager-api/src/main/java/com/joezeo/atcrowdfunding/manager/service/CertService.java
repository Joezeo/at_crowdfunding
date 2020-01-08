package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.common.utils.PageInfo;

public interface CertService {
    PageInfo queryCertByPage(Integer pageSize, Integer pageNum, String name);
}
