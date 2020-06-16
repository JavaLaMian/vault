package com.vault.demo.controller.main;


import com.vault.demo.bean.Bid;
import com.vault.demo.bean.Pager;
import com.vault.demo.bean.PerBid;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainControl{
    @Resource
    BidSer bidSer;
    @RequestMapping("/first")
    public String toMain(HttpServletRequest request){
        List<Bid> nList =  bidSer.allList();
        List ncList = nList.subList(0,3);
        List<PerBid> perList = bidSer.selectPerB(0);
        request.setAttribute("ncList",ncList);
        request.setAttribute("perList",perList);
        return "firstPage/first";
    }

    @RequestMapping("/prose")
    public String toProse(){
        return "firstPage/prose";
    }


    @RequestMapping("/perList")
    public String perList(HttpServletRequest request, Pager pager,PerBid per){
        System.out.println(per.toString());
        pager.page(bidSer.countPerPage(per.getRate(),per.getEnquiry()));
        List<PerBid> list = bidSer.pagePerB((pager.thisPage-1)*pager.titleSize,pager.titleSize,per.getRate(),per.getEnquiry());
        request.setAttribute("list",list);
        request.setAttribute("pager",pager);
        request.setAttribute("p",per);
        System.out.println(list.toString());
        return "firstPage/proseList";
    }


    @RequestMapping("/per")
    public String perImf(HttpServletRequest request){
//        List<PerBid> per = bidSer. selectPerB(id);
//        request.setAttribute("per",per);
        return "/firstPage/perBidImf";
    }
}
