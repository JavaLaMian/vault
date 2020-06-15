package com.vault.demo.controller.integral;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/integral")
public class integralController {

    //积分商城主页面
    @RequestMapping("/main")
    public String integralMain(){
        return "/integral/integralMain";
    }
}
