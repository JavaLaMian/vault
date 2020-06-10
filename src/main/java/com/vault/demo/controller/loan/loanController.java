package com.vault.demo.controller.loan;

import com.vault.demo.bean.Userimf;
import com.vault.demo.dao.UserimfDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("loan")
@Controller
public class loanController {

    @Resource
    UserimfDao userimfDao;

    @RequestMapping("/main")
    public String loanmain(){
        return "loan/loanMain";
    }
    //去登录
    @RequestMapping("/tologin")
    public String toLogin(){
        return "loan/loanLogin";
    }
    @RequestMapping("/tologin2")
    public String toLogin2(){
        return "loan/loanLogin2";
    }

    //登录
    @RequestMapping("/login")
    public String login(String account ,String password){
        System.out.println("账号"+account+"密码:"+password);

        Userimf userimf = new Userimf();
        userimf.setAccount(account);
        userimf.setLoginPsw(password);

        Userimf userimfEX = userimfDao.selectOneByLogin(userimf);

        System.out.println(userimfEX);
        if ("".equals(userimfEX.getUName()) || userimfEX.getUName() == null){
            System.out.println("登录失败");
        }

        return "redirect:/loan/main2";
    }

    //借贷中心
    @RequestMapping("/main2")
    public String main2() {
        return "loan/loanJie";
    }
    @RequestMapping("/toloanHuan")
    public String toloanHuan() {
        return "loan/loanHuan";
    }
    @RequestMapping("/toloanRecord")
    public String toloanRecord() {
        return "loan/loanRecord";
    }
    @RequestMapping("/toloanPersonage")
    public String toloanPersonage() {
        return "loan/loanPersonage";
    }

    @RequestMapping("/toUselog")
    public String toUserLogin(){
        return "loan/login";
    }
}
