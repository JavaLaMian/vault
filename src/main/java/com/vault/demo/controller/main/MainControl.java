package com.vault.demo.controller.main;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("main")
public class MainControl{
    @RequestMapping("first")
    public String toMain(HttpServletRequest request){
        return "firstPage/first";
    }
}
