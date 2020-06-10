package com.vault.demo.controller.backstage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdministrationController {
    @RequestMapping("/login")
    public String login(){

        return "";
    }
}
