package com.vault.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserControllerForCredit {
    @RequestMapping("/toCreditRegisterPage")
    public String toCreditRegisterPage(){
        return "user/creditRegister";
    }
}
