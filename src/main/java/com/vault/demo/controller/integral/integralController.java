package com.vault.demo.controller.integral;

import com.vault.demo.bean.*;
import com.vault.demo.common.Pager;
import com.vault.demo.service.integral.integralService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.expression.Lists;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        MyIntegral myTotal = service.selectMyIntegral2(user.getuId());
        session.setAttribute("total",myTotal.getTotal());

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
    public String conversion(String IntName,String type, MyIntegral myIntegral, HttpSession session, Model model, Bounty bounty){
        Userimf user = (Userimf) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/tologin";
        }
        if ("慧财".equals(type)){
            model.addAttribute("type",2);
            bounty.setuId(user.getuId());
            bounty.setBoType(4);

            String IntName2 = IntName.substring(0,2);
            System.out.println(IntName2);
            bounty.setBoMoney(Integer.valueOf(IntName.substring(0,2)));
            bounty.setBoTime(new Date());

            //兑换理财红包加入卡卷
            service.bountyAdd(bounty);
        }
        if(type != null){
            int total = (int) session.getAttribute("total");
            myIntegral.setuId(user.getuId());
            myIntegral.setChangeType("兑换商品");
            myIntegral.setTotal(total);

            //兑换时间
            Date date = new Date();
            //        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            //        String conversionTime = df.format(date);
            myIntegral.setConversionTime(new Date());
            int total2 = total - myIntegral.getChange();
            myIntegral.setTotal(total2);

            int i = service.conversionAdd(myIntegral);
            if(i == 1){
                //库存
                Integral integral = service.selectById(myIntegral.getiId());
                int inventory = integral.getInventory() -1;
                int x =service.integralInventory(inventory,myIntegral.getiId());

                //实时获取剩余积分
                MyIntegral myTotal = service.selectMyIntegral2(user.getuId());
                session.setAttribute("total",myTotal.getTotal());
                if(!"慧财".equals(type)){
                    model.addAttribute("type",1);
                }
                return "integral/conversionYes";
            }
        }
        return "redirect:integral/list?spId=0&currPage=1&sort=0";
    }

    @RequestMapping("/conversion2")
    public String conversion2(String phe,String place,int hId,HttpSession session){
        System.out.println("place:"+place);
        session.setAttribute("place",place);
        return "redirect:/integral/detail?id="+hId;
    }

    //积分商城主页面
    @RequestMapping("/main2")
    public String integralMain2(HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/tologin";
        }
        return "integral/integralMain2";
    }


    //积分规则
    @RequestMapping("/Rule")
    public String integralRule( HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/tologin";
        }
        return "integral/integralRule";
    }

    //积分问答
    @RequestMapping("/issue")
    public String integralIssue(HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        if(user == null){
            return "redirect:/user/tologin";
        }
        return "integral/integralIssue";
    }
}
