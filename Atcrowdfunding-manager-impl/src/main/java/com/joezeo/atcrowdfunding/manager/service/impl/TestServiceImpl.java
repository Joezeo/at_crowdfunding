package com.joezeo.atcrowdfunding.manager.service.impl;

import com.joezeo.atcrowdfunding.manager.dao.TestDao;
import com.joezeo.atcrowdfunding.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    public void saveData() {
        String name = "Joe Zane";
        int res = testDao.insertData(name);
    }
}
