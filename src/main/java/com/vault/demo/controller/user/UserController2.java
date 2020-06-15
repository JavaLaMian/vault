package com.vault.demo.controller.user;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.UserBank;
import com.vault.demo.bean.Userimf;
import com.vault.demo.service.user.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController2 {
    @Resource
    private UserService service;

    @RequestMapping("/toAO")
    public String toAO(){
        return "user/AccountOverview";
    }

    @RequestMapping("/toAS")
    public String toAS(HttpSession session) throws NullPointerException{
        Userimf user = (Userimf) session.getAttribute("user");
        Credit credit = service.getCredit(user.getuId());
        UserBank userBank = service.getBC(user.getuId());
        session.setAttribute("credit",credit);
        session.setAttribute("userBank",userBank);
        return "user/AccountSafe";
    }
    @RequestMapping("/toApply")
    public String toApply(HttpSession session){
        session.setAttribute("applyType","apply");
        return "user/apply";
    }

    @RequestMapping("/viewApply")
    public String viewApply(HttpSession session){
        session.setAttribute("applyType","view");
        Userimf user = (Userimf) session.getAttribute("user");
        Credit credit = (Credit) session.getAttribute("credit");
        UserBank userBank = (UserBank) session.getAttribute("userBank");
        return "user/apply";
    }

    @RequestMapping("/smrz")
    @ResponseBody
    public String smrz(String uName, UserBank userBank, Credit credit, HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        user.setUName(uName);
        userBank.setuId(user.getuId());
        credit.setuId(user.getuId());
        System.out.println(user.toString());
        System.out.println(userBank.toString());
        System.out.println(credit.toString());
        service.bindBank(userBank);
        service.bindCredit(credit);
        return "";
    }
}
