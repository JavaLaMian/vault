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

    //借贷中心
    @RequestMapping("/main2")
    public String main2() {
        return "loan/loanJie";
    }
    @RequestMapping("/toloanHuan")
    public String toloanHuan() {
        return "loan/loanHuan";
    }
    @RequestMapping("/toloanRecord")
    public String toloanRecord() {
        return "loan/loanRecord";
    }
    @RequestMapping("/toloanPersonage")
    public String toloanPersonage() {
        return "loan/loanPersonage";
    }

    @RequestMapping("/toUselog")
    public String toUserLogin(){
        return "loan/login";
    }
}
