package com.vault.demo.controller.main;


import com.vault.demo.bean.Bid;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("main")
public class MainControl{
    @Resource
    BidSer bidSer;
    @RequestMapping("first")
    public String toMain(HttpServletRequest request){
        List<Bid> ncList =  bidSer.selectByType(BidSer.TYPE_NEWCOMER);
        List<Bid> nrList = bidSer.selectByType(BidSer.TYPE_NORM);
        request.setAttribute("ncList",ncList);
        request.setAttribute("nrList",nrList);
        return "firstPage/first";
    }
}
