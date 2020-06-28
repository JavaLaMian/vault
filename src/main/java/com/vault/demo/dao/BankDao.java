package com.vault.demo.dao;

import com.vault.demo.bean.UserBank;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BankDao {
    int bindBank(UserBank userBank);
    UserBank getBC(int uId);
    void unBank(UserBank userBank);
    void upBank(UserBank userBank);

}