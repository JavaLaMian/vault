package com.vault.demo.controller.user;

import com.vault.demo.bean.*;
import com.vault.demo.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController2 {
    @Resource
    private UserService service;

    @RequestMapping("/toAO")
    public String toAO(HttpSession session,Model model){
        Userimf userimf = (Userimf) session.getAttribute("user");
        Map map = service.daiShou(userimf);
        userimf.setEmail(null);
        MyIntegral myIntegral = service.selectMyIntegral2(userimf.getuId());
        Userimf user = service.logPadUser(userimf);
        UserBank userBank = service.getBC(user.getuId());
        Credit credit = service.getCredit(user.getuId());
        Map ren = new HashMap();
        ren.put("bank",userBank);
        ren.put("cred",credit);
        session.setAttribute("ren",ren); //将用户认证信息存入session以判断
        session.setAttribute("user",user);
        session.setAttribute("sig",myIntegral);
        model.addAttribute("map",map);
        model.addAttribute("bank",userBank);

        return "user/AccountOverview";
    }

    @RequestMapping("/toAS")
    public String toAS(HttpSession session,Model model){
        Userimf user = (Userimf) session.getAttribute("user");
        try {
            Credit credit = service.getCredit(user.getuId());
            UserBank userBank = service.getBC(user.getuId());
            model.addAttribute("credit",credit);
            model.addAttribute("userBank",userBank);
            WorryCall worryCall = service.getWorryCall(user);
            model.addAttribute("worryCall",worryCall);
        }catch (Exception e){
            model.addAttribute("credit",null);
            model.addAttribute("userBank",null);
            model.addAttribute("worryCall",null);
        }
        return "user/AccountSafe";
    }
    @RequestMapping("/toApply")
    public String toApply(Model model){
        model.addAttribute("applyType","apply");
        return "user/apply";
    }

    @RequestMapping("/viewApply")
    public String viewApply(HttpSession session, Model model){
        Userimf user = (Userimf) session.getAttribute("user");
        Credit credit = service.getCredit(user.getuId());
        UserBank userBank = service.getBC(user.getuId());
        if(credit == null && userBank == null){
            model.addAttribute("applyType","apply");
        }else {
            model.addAttribute("applyType","view");
        }
        model.addAttribute("credit",credit);
        model.addAttribute("userBank",userBank);
        return "user/apply";
    }
    @RequestMapping("/updateApply")
    public String updateApply(Model model){
        model.addAttribute("applyType","update");
        return "user/apply";
    }
    @RequestMapping("/upcard")
    @ResponseBody
    public Boolean upcard(UserBank userBank, HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        userBank.setuId(user.getuId());
        userBank.setBcUserName(user.getuName());
//        System.out.println(user.toString());
//        System.out.println(userBank.toString());
        service.upbindBank(userBank);
        session.setAttribute("user",user);

        return false;
    }

    @RequestMapping("/checkold")
    @ResponseBody
    public boolean checkold(String dp,HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        if (user.getLoginPsw().equals(dp)){
            return true;
        }
        return false;
    }
    @RequestMapping("/smrz")
    @ResponseBody
    public Map smrz(String uName, UserBank userBank, Credit credit, HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        user.setuName(uName);
        userBank.setuId(user.getuId());
        userBank.setBcUserName(uName);
        credit.setuId(user.getuId());
        credit.setName(uName);
//        System.out.println(user.toString());
//        System.out.println(userBank.toString());
//        System.out.println(credit.toString());
        service.upUser(user);
        service.bindBank(userBank);
        service.bindCredit(credit);
        session.setAttribute("user",user);

        Map map = new HashMap();
        map.put("msg","cg");

        return map;
    }
    @RequestMapping("/zfmm")
    @ResponseBody
    public String zfmm(String dealPwd,HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        userimf.setDealPsw(dealPwd);
        System.out.println(userimf);
        session.setAttribute("user",userimf);
        service.upUser(userimf);
        return "";
    }
    @RequestMapping("/dlmm")
    @ResponseBody
    public Boolean dlmm(String loginPsw,HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        userimf.setLoginPsw(loginPsw);
        System.out.println(userimf);
        service.upUser(userimf);
        session.setAttribute("user",userimf);
        return true;
    }
    @RequestMapping("/czTx")
    @ResponseBody
    public Map ChongZhi(String type,String money,String pwd,HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        String uPwd = userimf.getDealPsw();
        Map map = new HashMap();
        if(uPwd.equals(pwd)){
            int pd = service.userChongTi(type,money,userimf);
            if(pd == 1){
                Userimf user = service.pandEmail(userimf.getEmail(),"e");
                session.setAttribute("user",user);
                map.put("msg","cg");
            }else if(pd == 2){
                map.put("msg","余额不足");
            }else {
                map.put("msg","操作失败 请重试");
            }
        }else {
            map.put("msg","mmcw");
        }
        return map;
    }
    @RequestMapping("/unbindCard")
    @ResponseBody
    public Boolean unbindCard(UserBank bank,HttpSession session){
        Userimf refereer = new Userimf();
        return false;
    }
    @RequestMapping("/emailUp")
    @ResponseBody
    public Boolean emailUp(String newEmail,HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        userimf.setEmail(newEmail);
        service.upUser(userimf);
        session.setAttribute("user",userimf);
        return true;
    }
    @RequestMapping("/bindWorryCall")
    @ResponseBody
    public Boolean bindWorryCall(WorryCall worryCall, HttpSession session){
        Userimf userimf = (Userimf) session.getAttribute("user");
        worryCall.setuId(userimf.getuId());
        service.bindWorryCall(worryCall);
        return true;
    }
    @RequestMapping("/WorryCallUp")
    @ResponseBody
    public Boolean upWorryCall(WorryCall worryCall,HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        worryCall.setuId(user.getuId());
        service.upWorryCall(worryCall);

        return true;
    }
    @RequestMapping("/bindReferee")
    @ResponseBody
    public Boolean bindReferee(String referee,HttpSession session){
        if (null==referee || "".equals(referee)){
            return false;
        }
        Userimf refereer = new Userimf();
        refereer.setAccount(referee);
        Userimf user = (Userimf) session.getAttribute("user");
        user.setRefereer(referee);
        if (service.bindReferee(refereer,user)){
            session.setAttribute("user",user);
            return true;
        }else {
            return false;
        }
    }
}
