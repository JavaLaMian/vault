package com.vault.demo.controller.integral;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.MyIntegral;
import com.vault.demo.bean.Userimf;
import com.vault.demo.bean.Integral;
import com.vault.demo.common.Pager;
import com.vault.demo.service.integral.integralService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.expression.Lists;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/integral")
public class integralController {
    @Resource
    private integralService  service;
    //积分商城主页面
    @RequestMapping("/main")
    public String integralMain(HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/tologin";
        }
        MyIntegral myIntegral = service.selectMyIntegral2(user.getuId());
        session.setAttribute("total",myIntegral.getTotal());
        session.setAttribute("user",user);
        return "integral/integralMain";
    }

    //我的积分
    @RequestMapping("/myIntegral")
    public String  myIntegral(Integer uid,Model model){
        if(uid == null){
            return "redirect:/user/tologin";
        }

        List<Map> integralList = service.selectMyIntegral(uid);

        model.addAttribute("integralList",integralList);
        return "integral/myIntegral";
    }

    //商品列表
    @RequestMapping("/list")
    public String list(@Param("spId")Integer spId,@Param("sort")Integer sort,Pager pager, Model model,HttpSession session){
        pager.pageSize = 10;
        //查询总行数
        Userimf user = (Userimf) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/tologin";
        }
        session.setAttribute("user",user);
        pager.page(service.integral());
        String integralType ;


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
        model.addAttribute("currPage",pager.currPage);
        return "integral/shopingList";
    }

    //物品兑换页
    @RequestMapping("/detail")
    public String detail(Integer id, Model model, HttpSession session){
        System.out.println("id:"+id);
        Integral list  = service.selectById(id);

        Userimf user = (Userimf) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/tologin";
        }
        MyIntegral myIntegral = service.selectMyIntegral2(user.getuId());
        session.setAttribute("total",myIntegral.getTotal());
        Credit credit = service.selectCredit(user.getuId());

        session.setAttribute("user",user);
        model.addAttribute("credit",credit);
        model.addAttribute("list",list);

        return "integral/integralDetail";
    }

    //兑换
    @RequestMapping("/conversion")
    public String conversion(String place){
        return "integral/conversion";
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
