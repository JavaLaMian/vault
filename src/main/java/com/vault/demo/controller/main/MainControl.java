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
import java.math.BigDecimal;
import java.text.ParseException;
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
        List<PerBid> perList = bidSer.selectPerB();
        System.out.println("=================="+perList.size());
        request.setAttribute("ncList",ncList);
        request.setAttribute("perList",perList);
        return "firstPage/first";
    }

    @RequestMapping("/prose")
    public String toProse(int t, int id, Model model, HttpSession session){
        //t == 1 新手标 2   3散标
        Userimf userimf = null;
        Bid bid = null;
        System.out.println("t"+t);
        if(t == 1 || t == 2){
            bid = bidSer.selectByBid(id);
            model.addAttribute("bx",bid);
        }else {
            PerBid perBid = bidSer.selectByPid(id);
            model.addAttribute("bx",perBid);
        }
        if(session.getAttribute("user") != null){
            userimf = (Userimf) session.getAttribute("user");
            //判断该用户是否投过此标 用户id 标id 标类
            List<Tender> tenders = bidSer.getTenderId(userimf.getuId(),id,t);

            BigDecimal zon = new BigDecimal("0"); //当前用户投此标总额
            BigDecimal userMax = new BigDecimal(bid.getPersonLimit()+"");//个人累计限额
            if(tenders.size() != 0){ //投过此标
                for(int i = 0;i < tenders.size(); i++){
                    BigDecimal tou = new BigDecimal(tenders.get(i).getTenMoney()+"");
                    zon = zon.add(tou);
                }
                model.addAttribute("to",tenders.get(tenders.size()-1));//最后一次投标
                model.addAttribute("kai",bid.getAddLimit());
            }else {
                model.addAttribute("kai",bid.getStartLimit());//起标额
            }
            //标限额
            BigDecimal allMax = new BigDecimal("0"); //所有用户投此标金额
            BigDecimal tMax = new BigDecimal(bid.getSumLimit()+"");   //总体累计限额
            List<Tender> zonTend = bidSer.getTenderId(0,id,t);
            if(zonTend.size() != 0){
                for(int i = 0;i < zonTend.size(); i++){
                    BigDecimal tou = new BigDecimal(zonTend.get(i).getTenMoney()+"");
                    allMax = allMax.add(tou);
                }
            }
            if(zon.compareTo(userMax)== -1 && allMax.compareTo(tMax) == -1){
                BigDecimal aa = new BigDecimal("0");
                if(zon.compareTo(allMax)==0 && aa.compareTo(allMax)==0){
                    System.out.println(zon +"没人投"+allMax);
                    model.addAttribute("ketou",userMax); //没人投此标
                }else{
                    zon = userMax.subtract(zon); //计算可投金额
                    allMax = tMax.subtract(allMax);
                    BigDecimal xiao = (zon.compareTo(allMax)== -1) ? zon : allMax;//取小的
                    System.out.println(zon +"投过xiao"+xiao);
                    model.addAttribute("ketou",xiao);
                }
            }else {
                model.addAttribute("ketou",0);
            }
        }
        return "firstPage/prose";
    }

    @RequestMapping("/perlist")
    public String toPerbid(){
        return "firstPage/perbidList";
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

            if(zcMoney.compareTo(useMoney) == -1) {
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
}
