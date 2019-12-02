package com.joezeo.atcrowdfunding.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispacherController {

    @RequestMapping("/index")
    public String htmIndex(){
        return "index";
    }
}
