package com.vault.demo.service.user;

import org.apache.commons.mail.EmailException;

public interface UserService {
    String getEmailMa(String shou,String type) throws EmailException;
}
