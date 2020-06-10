package com.vault.demo.controller.loan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(String account ,String password){
        System.out.println("账号"+account+"密码:"+password);

        return "redirect:/loan/main2";
    }

    @RequestMapping("/main2")
    public String main2() {
        return "loan/loanHead";
    }

    @RequestMapping("/toUselog")
    public String toUserLogin(){
        return "loan/login";
    }
}
