package com.vault.demo.service.user;

import com.vault.demo.bean.*;
import org.apache.commons.mail.EmailException;

import java.util.List;
import java.util.Map;

public interface UserService {
    int addUserImf(Userimf user);
    void upUser(Userimf userimf);
    String getEmailMa(String shou,String type) throws EmailException;
    Userimf pandEmail(String email);
    Userimf logPadUser(Userimf userimf);
    int updetaPwd(String email,String pwd);
    void bindCredit(Credit credit);
    void bindBank(UserBank userBank);
    UserBank getBC(int uId);
    Credit getCredit(int uId);
    Map daiShou(Userimf user);//查询用户代收本金

    void bindWorryCall(WorryCall worryCall);
    void upWorryCall(WorryCall worryCall);
    WorryCall getWorryCall(Userimf userimf);


    int userChongTi(String type,String money,Userimf userimf);
    List<Map> useZhiJinList(int uId);//用户资金记录
    List<Bounty> yhList(int uId);


    Boolean bindReferee(Userimf refereerUser,Userimf curr);
    Map getChuJie(Userimf user);//出借图
}
