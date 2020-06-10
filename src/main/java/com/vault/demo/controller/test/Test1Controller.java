package com.vault.demo.controller.test;

import com.vault.demo.bean.userimf;
import com.vault.demo.service.test.Test1Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/Test1")
public class Test1Controller {
    @Resource
    Test1Service test1Service;

    @RequestMapping("/toTest1Page")
    public ModelAndView toTest1Page(ModelAndView mv, userimf userimf){
        userimf.setUsername("kk");
        System.out.println(userimf.getUsername());
        mv.addObject("test1List",test1Service.allTest1());
        mv.setViewName("sss");

        return mv;
    }
}
