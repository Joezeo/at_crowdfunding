package com.joezeo.atcrowdfunding.manager.controller;

import com.joezeo.atcrowdfunding.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    public String doTest(){
        testService.saveData();
        return "test";
    }

}
