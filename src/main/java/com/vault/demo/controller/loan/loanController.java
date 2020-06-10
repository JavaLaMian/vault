package com.vault.demo.controller.loan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("loan")
@Controller
public class loanController {

    @RequestMapping("/main")
    public String loanmain(){
        return "loan/loanMain";
    }
    //去登录
    @RequestMapping("/tologin")
    public String toLogin(){
        return "loan/loanLogin";
    }
    @RequestMapping("/tologin2")
    public String toLogin2(){
        return "loan/loanLogin2";
    }

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public String login(String account ,String password){
        System.out.println("账号"+account+password);

        return "账号"+account+password;
    }

}
