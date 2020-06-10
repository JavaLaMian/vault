package com.vault.demo.controller.user;

import com.vault.demo.bean.Userimf;
import com.vault.demo.service.user.UserService;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService service;

    @RequestMapping("/tologin")
    public String toUserLogin(){
        return "loan/login";
    }

    @RequestMapping("/add")
    public String addUser(Userimf user){
        System.out.println(user.toString());
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dates = format1.format(date);

        System.out.println(dates+"|"+user.toString());
        return "redirect:tologin";
    }

    @RequestMapping("/getMa")
    @ResponseBody
    public String getMa(String email,String type) throws EmailException {
        String ma = service.getEmailMa(email,type);
        return ma;
    }
}
