package com.xymiao.cms.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("")
    public String home(){
        return "home api interface!";
    }

    @RequestMapping("userinfo")
    public String userInfo(Authentication auth){
        return "user: " + auth.getName();
    }
}