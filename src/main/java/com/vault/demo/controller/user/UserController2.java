package com.vault.demo.controller.user;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.UserBank;
import com.vault.demo.bean.Userimf;
import com.vault.demo.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
    @RequestMapping("/toApply")
    public String toApply(){
        return "user/apply";
    }

    @RequestMapping("/smrz")
    @ResponseBody
    public String smrz(String uName, UserBank userBank, Credit credit, HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        user.setUName(uName);
        userBank.setuId(user.getuId());
        credit.setuId(user.getuId());
        System.out.println(user.toString());
        System.out.println(userBank.toString());
        System.out.println(credit.toString());
        service.bindBank(userBank);
        service.bindCredit(credit);
        return "";
    }
}
