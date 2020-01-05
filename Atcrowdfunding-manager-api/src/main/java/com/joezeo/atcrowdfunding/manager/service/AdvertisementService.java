package com.joezeo.atcrowdfunding.manager.service;

import com.joezeo.atcrowdfunding.bean.Advertisement;
import com.joezeo.atcrowdfunding.common.utils.PageInfo;

public interface AdvertisementService {
    PageInfo queryAdvertByPage(String name, Integer pageSize, Integer pageNum);

    void addAdvert(Advertisement advert);
}
