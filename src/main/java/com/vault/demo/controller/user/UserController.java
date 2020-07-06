package com.vault.demo.controller.user;

import com.mysql.cj.xdevapi.JsonArray;
import com.vault.demo.bean.Bid;
import com.vault.demo.bean.Bounty;
import com.vault.demo.bean.PerBid;
import com.vault.demo.bean.Userimf;
import com.vault.demo.service.test.BidSer;
import com.vault.demo.service.user.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

//
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService service;

    @RequestMapping("/tologin")
    public String toUserLogin(String zc, HttpServletRequest request, Model model){
        String msg = request.getParameter("msg");//获取上个方法添加的参数
        model.addAttribute("msg",msg);//设置到requset中页面提醒用户

        if("zc".equals(zc)) return "loan/login?zc";
        else return "loan/login";
    }

    @RequestMapping("/tozhao")
    public String toZhaoPwd(){
        return "user/zhaohui";
    }

    @RequestMapping("/add")
    public String addUser(Userimf user,RedirectAttributes m){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dates = df.format(new Date());// new Date()为获取当前系统时间
        try {
            Date date = df.parse(dates);
            user.setRegTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        if(service.addUserImf(user)==1){
            m.addAttribute("msg","注册成功！");
        }else {
            m.addAttribute("msg","注册失败，请稍后再试");
        }
        return "redirect:tologin";
    }

    @RequestMapping("/login")
    public String loginUser(String email, String pwd, String account, String logtype, HttpSession session,RedirectAttributes m){
        //logtype 1 邮箱登陆  0 密码登陆
        Userimf user;
        if("1".equals(logtype)){
            user = service.pandEmail(email,"e");
            session.setAttribute("user",user);
            //System.out.println("邮箱登陆"+user+toString());
            return "redirect:tologin";
        }else {
            Userimf userimf = new Userimf();
            userimf.setLoginPsw(pwd);
            if (account.length() < 9){//字符总长小于9，是为邮箱登录
                userimf.setEmail(account);
            }else if (account.substring(0,9).equals("xiaomuniu")){//字符前9为‘xiaomuniu’，是为账号登录
                userimf.setAccount(account);
            }else {//大于9且字符前9不为‘xiaomuniu’，是为邮箱登录
                userimf.setEmail(account);
            }

            user = service.logPadUser(userimf);
            if(user != null){
                //System.out.println("账号密码正确");
                session.setAttribute("user",user);
                return "redirect:/main/first";
            }else {
                m.addAttribute("msg","账号或密码错误");
                return "redirect:tologin";
            }
        }
    }
    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:tologin";
    }
    @RequestMapping("/tozj")
    public String toZhiJinPage() {
        return "user/zhiJin";
    }
    @RequestMapping("/toYq")
    public String toYaoQing() {
        return "user/yaoQing";
    }

    @RequestMapping("/zhijin")
    @ResponseBody
    public Map getUseZhiJIn(HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        Map map = new HashMap();
        List<Map> mlist = service.useZhiJinList(user.getuId());
        map.put("list",mlist);
        map.put("size",mlist.size());
        return map;
    }


    @RequestMapping("fList")
    @ResponseBody
    public Map getFList(HttpSession session){
        Userimf u = (Userimf) session.getAttribute("user");
        List<Userimf> list = service.friendList(u.getuId(),u.getAccount());
        Map m = new HashMap();
        m.put("list",list);
        m.put("size",list.size());
        return m;
    }

    @RequestMapping("/getMa")
    @ResponseBody
    public String getMa(String email,String type) throws EmailException {
        String ma = service.getEmailMa(email,type);
        return ma;
    }

    @RequestMapping("/padEmail")
    @ResponseBody
    public boolean padEmail(String email){
        if(service.pandEmail(email,"e") != null){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/updpwd")
    @ResponseBody
    public int updPwd(String email,String pwd){
        return service.updetaPwd(email,pwd);
    }

    @RequestMapping("/toYuhui")
    public String toYouHui(){
        return "user/youHui";
    }

    @RequestMapping("/yuhui")
    @ResponseBody
    public Map getUseYuhui(HttpSession session){
        Userimf user = (Userimf) session.getAttribute("user");
        Map map = new HashMap();
        List<Bounty> mlist = service.yhList(user.getuId());
        map.put("list",mlist);
        map.put("size",mlist.size());
        return map;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/main/first";
    }

    @RequestMapping("/zhexian")
    @ResponseBody
    public Map getShu(HttpSession session){
        Userimf userimf = (Userimf)session.getAttribute("user");
        Map max = service.getChuJie(userimf);
        return max;
    }

    @RequestMapping("/huikuan")
    @ResponseBody
    public Map huiKuan(int yue,int year,HttpSession session){
        Userimf user = (Userimf)session.getAttribute("user");
        Map max = service.getRiLi(user,yue,year);
        return max;
    }

    @RequestMapping("/padAct")
    @ResponseBody
    public boolean padAccount(String act){
        if(service.pandEmail(act,"a") != null){
            return true;
        }else {
            return false;
        }
    }
}
