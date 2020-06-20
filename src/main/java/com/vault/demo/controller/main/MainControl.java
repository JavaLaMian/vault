package com.vault.demo.controller.main;


import com.vault.demo.bean.*;
import com.alibaba.fastjson.JSONObject;
import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;
import com.vault.demo.bean.Tender;
import com.vault.demo.bean.Userimf;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    @RequestMapping("/perList")
    public String perList(PerBid perBid, Pager pager, HttpServletRequest request) {
        pager.page(bidSer.countPerPage(perBid.getRate(), perBid.getEnquiry()));
        List<PerBid> list = bidSer.pagePerB((pager.thisPage - 1) * pager.titleSize, pager.titleSize, perBid.getRate(), perBid.getEnquiry());
        request.setAttribute("pager", pager);
        request.setAttribute("list", list);
        request.setAttribute("p", perBid);
        return "firstPage/proseList";
    }

    @RequestMapping("/prose")
    public String toProse(int t, int id, Model model, HttpSession session){
        //t == 1 新手标 2   3散标
        if(t == 1 || t == 2){
            Bid bid = bidSer.selectByBid(id);
            model.addAttribute("bx",bid);
        }else {
            PerBid perBid = bidSer.selectByPid(id);
            model.addAttribute("bx",perBid);
        }
        if(session.getAttribute("user") != null){
            Map max = bidSer.padTouBiao(session,id,t);//标id 标种类 t
            model.addAttribute("to",max.get("to"));//最后一次投标
            model.addAttribute("kai",max.get("kai"));
            model.addAttribute("ketou",max.get("ketou")); //没人投此标
        }
        return "firstPage/prose";

    }

    @RequestMapping("/perImf")
    public String perImf(int id, HttpServletRequest request){
        PerBid p = bidSer.selectByPid(id);
        List<Map> userimf = bidSer.selectUser(p.getBorrower());
        Map u = userimf.get(0);
        List<Map> tenders = bidSer.selectTandU(id,  3);
        int countU = bidSer.countTenByBid(id, 3);
        Date lastTime = bidSer.lastTenTime(id);
        request.setAttribute("p", p);
        request.setAttribute("u", u);
        request.setAttribute("t", tenders);
        request.setAttribute("countU", countU);
        request.setAttribute("lastTime",lastTime);
        return "firstPage/perBidImf";
    }

    @RequestMapping("/getbid")
    public String touZhi(Tender tender,String daoqi,String pwd,HttpSession session) throws ParseException { //用户购买标
        Userimf userimf = (Userimf)session.getAttribute("user");
        String userMon = userimf.getAvaBalance()+"";
        if(pwd.equals(userimf.getDealPsw())){
            BigDecimal zhichu = new BigDecimal(tender.getTenMoney()+"");
            BigDecimal wan = new BigDecimal("10000");

            BigDecimal zcMoney = zhichu.multiply(wan);
            BigDecimal useMoney = new BigDecimal(userMon);

            if(useMoney.compareTo(zcMoney) == 1) {
               //余额充足
                BigDecimal cha = useMoney.subtract(zcMoney);
                float jieguo = cha.floatValue();
                System.out.println("差"+jieguo);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date utilDate = sdf.parse(daoqi+" 00:00:00");
                tender.setTenTime(new Date());
                tender.setTenCicle(utilDate);

                if(bidSer.setTender(tender) == 1) System.out.println("购买成功");
                if(bidSer.gouMai(jieguo,userimf.getuId()) == 1) System.out.println("支付成功");

                return "redirect:first";
            }else {
                System.out.println("余额不足");
                String url = "firstPage/prose?t="+tender.getbType()+"&id="+userimf.getuId();
                return url;
            }
        }else {
            System.out.println("密码错误");
            String url = "firstPage/prose?t="+tender.getbType()+"&id="+userimf.getuId();
            return url;
        }
    }

    @RequestMapping("/blist")
    @ResponseBody
    public Map getBList(){
        Map map = new HashMap();
        List<Bid> blist = bidSer.allList();
        BigDecimal zon = new BigDecimal("0");
        for(int i = 0;i < blist.size(); i++){
            Bid bid = blist.get(i);
            bid.setClockLine(Integer.parseInt(bid.getClockLine()) * 30 +"");//将月转换成天
            BigDecimal add = new BigDecimal(""+bid.getSumLimit());
            zon = zon.add(add);
            blist.set(i,bid);
        }
        map.put("list",blist);
        map.put("moneyMax",zon+"");
        map.put("size",blist.size()+"");

        return map;
    }

    @RequestMapping("/clist")
    @ResponseBody
    public Map getCommList(HttpSession session){
        Map map = new HashMap();
        Userimf userimf = (Userimf) session.getAttribute("user");
        if(userimf != null){
            List<Map> blist = bidSer.getComList(userimf.getuId());
            map.put("list",blist);
            map.put("size",blist.size());
            map.put("pad",1);
            return map;
        }else {
            map.put("pad",0);
            return map;
        }
    }
}
