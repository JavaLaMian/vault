package com.vault.demo.service.user.impl;

import com.vault.demo.service.user.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getEmailMa(String shou,String type) throws EmailException {
        //type 发送邮件的类型 zc 注册
        HtmlEmail email = new HtmlEmail();
        String ma = "";
        for(int i =0;i < 6;i++){
            ma += ""+(int)(Math.random()*10);
        }
        System.out.println(ma);
        String text = "";
        if("zc".equals(type)){
            text = "欢迎注册小刘理财，您的邮箱验证码为 "+ma+",若非本人操作请忽略";
        }else if("dl".equals(type)){
            text = "用户您好，您的邮箱登陆验证码为 "+ma;
        }

        email.setHostName("smtp.163.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型 ALNFMBFNJZMHNLTA
        email.addTo(shou);//设置收件人wgmecaupoemqdhgj
        email.setFrom("wy2229077248@163.com","验证中心");//发送人的邮箱为自己的，用户名可以随便填
        email.setAuthentication("wy2229077248@163.com","ALNFMBFNJZMHNLTA");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setSubject("小刘理财");//设置发送主题
        email.setMsg(text);//设置发送内容
        email.send();//进行发送
        return ma;
    }
}
