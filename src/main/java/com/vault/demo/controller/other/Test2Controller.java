package com.vault.demo.controller.other;

import com.vault.demo.bean.Bid;
import com.vault.demo.dao.test.BidDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/Test2")
public class Test2Controller {
    @Resource
    BidDao bidDao;

    @RequestMapping("/toTestBidDao")
    @ResponseBody
    public String toTestBidDao(){

//        Bid bid = new Bid();
//        bid.setAnswer("sadsa");
//        bid.setBidName("sasdddddsa");
//        bid.setBidType(1);
//
//        bidDao.insert(bid);
//
//        List bidList = bidDao.allList();
//        System.out.println(bidList);
//
        Bid bid1 = new Bid();
//        for (Object o : bidList){
//            bid1 = (Bid) o;
//        }

        bid1.setbId(1);
        bid1.setBidName("wwwwwwwww");





        return "sss";
    }
}
