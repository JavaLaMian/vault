package com.vault.demo.controller.main;


import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;
import com.vault.demo.bean.Tender;
import com.vault.demo.bean.Userimf;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        List<PerBid> perList = bidSer.selectPerB();
        System.out.println("=================="+perList.size());
        request.setAttribute("ncList",ncList);
        request.setAttribute("perList",perList);
        return "firstPage/first";
    }

    @RequestMapping("/prose")
    public String toProse(int t, int id, Model model, HttpSession session){
        //t == 1 普通标 2 散标
        System.out.println(t+"|"+id);
        Userimf userimf = null;
        if( session.getAttribute("user") != null){
            userimf = (Userimf) session.getAttribute("user");
            //判断该用户是否投过此标
            Tender tender = bidSer.getTenderId(userimf.getuId());
            model.addAttribute("to",tender);
        }
        if(t == 1){
            Bid bid = bidSer.selectByBid(id);
            model.addAttribute("bx",bid);
        }else {
            PerBid perBid = bidSer.selectByPid(id);
            model.addAttribute("bx",perBid);
        }
        return "firstPage/prose";
    }

    @RequestMapping("/perlist")
    public String toPerbid(){
        return "firstPage/perbidList";
    }
}
