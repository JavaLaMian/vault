package com.vault.demo.controller.main;


import com.vault.demo.bean.*;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println("=================="+perList.size());
        request.setAttribute("ncList",ncList);
        request.setAttribute("perList",perList);
        return "firstPage/first";
    }


    @RequestMapping("/perList")
    public String perList(PerBid perBid ,Pager pager,HttpServletRequest request){
        pager.page(bidSer.countPerPage(perBid.getRate(),perBid.getEnquiry()));
        List<PerBid> list = bidSer.pagePerB((pager.thisPage-1)*pager.titleSize,pager.titleSize,perBid.getRate(),perBid.getEnquiry());
        request.setAttribute("pager",pager);
        request.setAttribute("list",list);
        request.setAttribute("p",perBid);
        return "firstPage/proseList";
    }

    @RequestMapping("/perImf")
    public String perImf(int id,HttpServletRequest request){
            PerBid p =  bidSer.selectByPid(id);
        System.out.println(p.getBorrower()+"-------------------------------------");
            List  userimf = bidSer.selectUser(p.getBorrower());
            List<Tender> tenders = bidSer.getTenderId(id,3,0);
            int countU = bidSer.countTenByBid(id,3);
            request.setAttribute("p",p);
            request.setAttribute("u",userimf);
            request.setAttribute("t",tenders);
            request.setAttribute("countU",countU);
            return "firstPage/perBidImf";
    }
}
