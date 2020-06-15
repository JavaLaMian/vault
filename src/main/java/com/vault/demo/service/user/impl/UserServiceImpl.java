package com.vault.demo.service.user.impl;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.UserBank;
import com.vault.demo.bean.Userimf;
import com.vault.demo.dao.BankDao;
import com.vault.demo.dao.UserimfDao;
import com.vault.demo.dao.CreditDao;
import com.vault.demo.service.user.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserimfDao dao;
    @Resource
    private BankDao bdao;
    @Resource
    private CreditDao cdao;

    @Override
    public int addUserImf(Userimf user) {
        int lie = dao.addUser(user);
        dao.updateUserAccount(user.getuId(),"xiaomuniu"+user.getuId());
        return lie;
    }

    @Override
    public String getEmailMa(String shou,String type) throws EmailException {
        if("".equals(shou)){
            return "";
        }
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
        }else if("zh".equals(type)){
            text = "您的正在修改该邮箱绑定账号的密码，验证码为 "+ma+",若非本人操作请忽略";
        }else if("smrz".equals(type)){
            text = "您的正在进行实名认证，验证码为 "+ma+",若非本人操作请忽略";
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

    @Override
    public Userimf pandEmail(String email) {
        Userimf user = new Userimf();
        user.setEmail(email);
        return dao.selectByUserimf(user);
    }

    @Override
    public Userimf logPadUser(Userimf userimf) {
        return dao.selectOneByLogin(userimf);
    }

    @Override
    public int updetaPwd(String email, String pwd) {
        return dao.updateUserPwd(pwd,email);
    }

    @Override
    public void bindCredit(Credit credit) {
        cdao.bindCredit(credit);
    }

    @Override
    public void bindBank(UserBank userBank) {
        bdao.bindBank(userBank);
    }
}
