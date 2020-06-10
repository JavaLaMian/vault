package com.vault.demo.controller.backstage;

import com.vault.demo.bean.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/XMN")
public class backstageController {
    //首页
    @RequestMapping("/backstage")
    public ModelAndView backstage(ModelAndView mv, HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            mv.setViewName("backstage/admin_login");
        }else{
            mv.setViewName("backstage/Bindex");
        }
        return mv;
    }
    @RequestMapping("/text")
    public ModelAndView text(ModelAndView mv){
        mv.setViewName("backstage/text");
        return mv;
    }
}
