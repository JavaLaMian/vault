package com.vault.demo.controller.main;


import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        List<PerBid> perList = bidSer.selectPerB();
        System.out.println("=================="+perList.size());
        request.setAttribute("ncList",ncList);
        request.setAttribute("perList",perList);
        return "firstPage/first";
    }

    @RequestMapping("/prose")
    public String toProse(int t, int id, Model model){
        //t == 1 散标
        System.out.println(t+"|"+id);
        if(t == 0){
            Bid bid = bidSer.selectByBid(id);
            model.addAttribute("biao",bid);
        }else {
            PerBid perBid = bidSer.selectByPid(id);
            model.addAttribute("biao",perBid);
        }
        return "firstPage/prose";
    }

    @RequestMapping("/perlist")
    public String toPerbid(){
        return "firstPage/perbidList";
    }
}
