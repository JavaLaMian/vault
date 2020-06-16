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
    public String list(@Param("spId")Integer spId,@Param("sort")Integer sort,Pager pager, Model model){
        pager.pageSize = 10;
        //查询总行数
        pager.page(service.integral());
        String integralType ;
//        System.out.println("spId:"+spId);
//
//        System.out.println("当前页1:"+pager.currPage);
//        System.out.println("末页1:"+pager.totalPage);


        if(sort == 1){
            //分类
            if(spId ==1){
                System.out.println("进入s1");
                integralType = "生活" ;
                pager.data = service.selectByTypeSort(pager,integralType);
            }else if(spId == 2){
                System.out.println("进入s2");
                integralType = "品质" ;
                pager.data = service.selectByTypeSort(pager,integralType);
            }else if(spId == 3){
                System.out.println("进入s3");
                integralType = "慧财" ;
                pager.data = service.selectByTypeSort(pager,integralType);
            }
            else {
                System.out.println("进入sall");
                pager.data=service.plistpageSort(pager);
            }
        }
        else{
            //分类
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
                integralType = "慧财" ;
                pager.data = service.selectByType(pager,integralType);
            }
            else {
                System.out.println("进入all");
                pager.data=service.plistpage(pager);
            }
        }

        model.addAttribute("pager",pager);
        model.addAttribute("spId",spId);
        model.addAttribute("sort",sort);
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
