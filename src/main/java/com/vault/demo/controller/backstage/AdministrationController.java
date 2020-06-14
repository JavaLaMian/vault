package com.vault.demo.controller.backstage;

import com.vault.demo.bean.Admin;
import com.vault.demo.dao.backstage.AdminDao;
import com.vault.demo.service.backstage.admin.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdministrationController {
    @Resource
    AdminService adminService;

    @ResponseBody
    @RequestMapping("/login")
    public String login(String account, String pwd,HttpSession session){
        System.out.println(account+" "+pwd);
        Admin admin = adminService.checkLogin(account,pwd);
        System.out.println(admin.toString());
        if(admin!=null){
            session.setAttribute("admin",admin);
            return "success";
        }else{
            return "shibai";
        }
    }
    @ResponseBody
    @RequestMapping("/repwd")
    public String rePwd(int aid,String newpwd){
        adminService.setPwd(aid,newpwd);
        return "success";
    }
    @ResponseBody
    @RequestMapping("/checkold")
    public String checkOldPwd(String oldpwd,int aid){
        Admin admin = adminService.getAdminById(aid);
        if(oldpwd==admin.getPwd()){
            return "true";
        }else{
            return "false";
        }
    }

}
