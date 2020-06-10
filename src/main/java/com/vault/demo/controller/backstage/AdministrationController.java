package com.vault.demo.controller.backstage;

import com.vault.demo.bean.Admin;
import com.vault.demo.dao.backstage.AdminDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdministrationController {
    @Resource
    AdminDao adminDao;

    @ResponseBody
    @RequestMapping("/login")
    public String login(String account, String pwd, HttpSession session){
        Admin admin = adminDao.checkLogin(account,pwd);
        if(admin!=null){
            session.setAttribute("admin",admin);
            return "success";
        }
        return "shibai";
    }
}
