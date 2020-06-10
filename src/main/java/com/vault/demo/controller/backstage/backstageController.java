package com.vault.demo.controller.backstage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/XMN")
public class backstageController {
    @RequestMapping("/backstage")
    public ModelAndView backstage(ModelAndView mv){
        mv.setViewName("backstage/main");
        return mv;
    }
    @RequestMapping("/text")
    public ModelAndView text(ModelAndView mv){
        mv.setViewName("backstage/text");
        return mv;
    }

}