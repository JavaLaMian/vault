package com.vault.demo.dao;

import com.vault.demo.bean.UserBank;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BankDao {
    int bindBank(UserBank userBank);
}