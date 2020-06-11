package com.vault.demo.controller.user;

import com.vault.demo.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController2 {
    @Resource
    private UserService service;

    @RequestMapping("/toAO")
    public String toAO(){
        return "user/AccountOverview";
    }
    @RequestMapping("/toAS")
    public String toAS(){
        return "user/AccountSafe";
    }
}
