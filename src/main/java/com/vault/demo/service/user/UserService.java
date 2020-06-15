package com.vault.demo.service.user;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.UserBank;
import com.vault.demo.bean.Userimf;
import org.apache.commons.mail.EmailException;

import java.util.List;

public interface UserService {
    int addUserImf(Userimf user);
    String getEmailMa(String shou,String type) throws EmailException;
    Userimf pandEmail(String email);
    Userimf logPadUser(Userimf userimf);
    int updetaPwd(String email,String pwd);

    void bindCredit(Credit credit);
    void bindBank(UserBank userBank);
    UserBank getBC(int uId);
    Credit getCredit(int uId);
}
