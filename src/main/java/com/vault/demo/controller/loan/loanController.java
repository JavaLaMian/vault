package com.vault.demo.controller.loan;

import com.vault.demo.bean.Userimf;
import com.vault.demo.dao.UserimfDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequestMapping("/loan")
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
    public String login(String account , String password , HttpSession session){
        Userimf userimf = new Userimf();

        if (account.length() < 9){//字符总长小于9，是为邮箱登录
            userimf.setEmail(account);
        }else if (account.substring(0,9).equals("xiaomuniu")){//字符前9为‘xiaomuniu’，是为账号登录
            userimf.setAccount(account);
        }else {//大于9且字符前9不为‘xiaomuniu’，是为邮箱登录
            userimf.setEmail(account);
        }

        userimf.setLoginPsw(password);

        Userimf userimfEX = userimfDao.selectOneByLogin(userimf);

        session.setAttribute("user",userimfEX);

        return "redirect:/loan/main2";
    }

    @RequestMapping("/checkUserAccountAndPwd")
    @ResponseBody
    public String checkUserAccountAndPwd(String account,String password){
        System.out.println("账号"+account+"密码:"+password);

        Userimf userimf = new Userimf();

        if (account.length() < 9){//字符总长小于9，是为邮箱登录
            userimf.setEmail(account);
        }else if (account.substring(0,9).equals("xiaomuniu")){//字符前9为‘xiaomuniu’，是为账号登录
            userimf.setAccount(account);
        }else {//大于9且字符前9不为‘xiaomuniu’，是为邮箱登录
            userimf.setEmail(account);
        }

        userimf.setLoginPsw(password);

        Userimf userimfEX = userimfDao.selectOneByLogin(userimf);

        System.out.println(userimfEX);
        if (userimfEX == null){
            System.out.println("登录失败");

            return "NO";
        }else {
            return "YES";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");

        return "redirect:/loan/main";
    }

    //借贷中心
    @RequestMapping("/main2")
    public String main2(HttpSession session) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/loanJie";
    }
    @RequestMapping("/toloanJie")
    public String toloanJie(@Param( "step") Integer step, Model model) {
        System.out.println("step："+step);
        if(step==null){
            step = 1 ;
        }
         model.addAttribute("step",step);
        return "loan/loanJieApply";
    }
    @RequestMapping("/loanJie")
    public String loanJie(){
        return "";
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

    public Boolean checkSessionIsEmpty(HttpSession session){
        Userimf userimf = (Userimf)session.getAttribute("user");

        if (userimf == null){
            return true;
        }else {
            return false;
        }
    }
}
