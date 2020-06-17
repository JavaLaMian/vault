package com.vault.demo.controller.user;

import com.vault.demo.bean.Userimf;
import com.vault.demo.service.loan.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserControllerForCredit {
    @Resource
    LoanService loanService;

    @RequestMapping("/toCreditRegisterPage")
    public String toCreditRegisterPage(HttpSession session, Model model){
        if (((Userimf)session.getAttribute("user")) == null){//判断用户是否登录，
            return "redirect:/user/tologin";
        }

        model.addAttribute("user",(Userimf)session.getAttribute("user"));
        model.addAttribute("credit",loanService.LoanNow((Userimf)session.getAttribute("user")));
        model.addAttribute("house",loanService.selectHouseByUId((Userimf)session.getAttribute("user")));
        model.addAttribute("car",loanService.selectCarByUId((Userimf)session.getAttribute("user")));

        return "user/creditRegister";
    }
}
