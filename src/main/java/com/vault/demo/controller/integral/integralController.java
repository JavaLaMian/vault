package com.vault.demo.controller.integral;

import com.vault.demo.common.Pager;
import com.vault.demo.service.integral.integralService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/integral")
public class integralController {

    @Resource
    private integralService  service;
    //积分商城主页面
    @RequestMapping("/main")
    public String integralMain(){
        return "integral/integralMain";
    }

    //商品列表
    @RequestMapping("/list")
    public String list(@Param("spId")Integer spId, Pager pager, Model model){
        pager.pageSize = 5;
        //查询总行数
        pager.page(service.integral());
        String integralType ;
        System.out.println("spId:"+spId);
        if(spId ==1){
            System.out.println("进入1");
            integralType = "生活" ;
            pager.data = service.selectByType(pager,integralType);
        }else if(spId == 2){
            System.out.println("进入2");
            integralType = "品质" ;
            pager.data = service.selectByType(pager,integralType);
        }else if(spId == 3){
            System.out.println("进入3");
            integralType = "品质" ;
            pager.data = service.selectByType(pager,integralType);
        }
        else {
            System.out.println("进入all");
            pager.data=service.plistpage(pager);
        }
        model.addAttribute("pager",pager);
        model.addAttribute("spId",spId);
        return "integral/shopingList";
    }

    //积分商城主页面
    @RequestMapping("/main2")
    public String integralMain2(){
        return "integral/integralMain2";
    }


    //积分规则
    @RequestMapping("/Rule")
    public String integralRule(){
        return "integral/integralRule";
    }

    //积分问答
    @RequestMapping("/issue")
    public String integralIssue(){
        return "integral/integralIssue";
    }
}
