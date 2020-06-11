package com.vault.demo.service.user;

import com.vault.demo.bean.Userimf;
import org.apache.commons.mail.EmailException;

public interface UserService {
    int addUserImf(Userimf user);
    String getEmailMa(String shou,String type) throws EmailException;
    Userimf pandEmail(String email);
    Userimf logPadUser(Userimf userimf);
}
