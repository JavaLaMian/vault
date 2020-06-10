package com.vault.demo.controller.user;

//import com.vault.demo.bean.user.userimf;
import com.vault.demo.service.user.UserService;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService service;

//    @RequestMapping("/add")
//    public String addUser(userimf user){
//        System.out.println(user.toString());
//        return "";
//    }

    @RequestMapping("/getMa")
    @ResponseBody
    public String getMa(String email,String type) throws EmailException {
        String ma = service.getEmailMa(email,type);
        return ma;
    }
}
