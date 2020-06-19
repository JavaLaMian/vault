package com.vault.demo.controller.user;

import com.vault.demo.bean.Userimf;
import com.vault.demo.service.user.UserService;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService service;

    @RequestMapping("/tologin")
    public String toUserLogin(String zc){
        if("zc".equals(zc)) return "loan/login?zc";
        else return "loan/login";
    }

    @RequestMapping("/tozhao")
    public String toZhaoPwd(String zc){
        return "user/zhaohui";
    }

    @RequestMapping("/add")
    public String addUser(Userimf user,HttpSession session){
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
            session.setAttribute("msg","注册成功！");
        }else {
            session.setAttribute("msg","注册失败，请稍后再试");
        }
        return "redirect:tologin";
    }

    @RequestMapping("/login")
    public String loginUser(String email, String pwd, String account, String logtype, HttpSession session){
        //logtype 1 邮箱登陆  0 密码登陆
        Userimf user;
        if("1".equals(logtype)){
            user = service.pandEmail(email);
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
                return "redirect:toAO";
            }else {
                session.setAttribute("msg","账号或密码错误");
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
    public String toZhiJinPage(HttpSession session) {
        Userimf user = (Userimf) session.getAttribute("user");
        if(user != null){
            return "user/zhiJin";
        }else {
            return "redirect:tologin";
        }
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

    @RequestMapping("/getMa")
    @ResponseBody
    public String getMa(String email,String type) throws EmailException {
        String ma = service.getEmailMa(email,type);
        return ma;
    }

    @RequestMapping("/padEmail")
    @ResponseBody
    public boolean padEmail(String email){
        if(service.pandEmail(email) != null){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/delmsg")
    @ResponseBody
    public String DelMsg(HttpSession session){
        session.removeAttribute("msg");
        return "";
    }

    @RequestMapping("/updpwd")
    @ResponseBody
    public int updPwd(String email,String pwd){
        return service.updetaPwd(email,pwd);
    }

}
