package com.vault.demo.controller.test;

import com.vault.demo.bean.Userimf;
import com.vault.demo.service.test.Test1Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/Test1")
public class Test1Controller {

    @RequestMapping("/toTest1Page")
    public ModelAndView toTest1Page(ModelAndView mv, Userimf userimf){
        userimf.setuName("kk");
        System.out.println(userimf.getuName());
        mv.setViewName("sss");

        return mv;
    }
}
