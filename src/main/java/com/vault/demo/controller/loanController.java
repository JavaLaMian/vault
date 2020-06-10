package com.vault.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("loan")
@Controller
public class loanController {

    @RequestMapping("/main")
    public String loanmain(){
        return "loanMain";
    }
    @RequestMapping("/login")
    public String login(){
        return "loanLogin";
    }
    @RequestMapping("/login2")
    public String login2(){
        return "loanLogin2";
    }

}
