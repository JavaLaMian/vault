package com.vault.demo.dao;

import com.vault.demo.bean.Credit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CreditDao {
    int bindCredit(Credit credit);
    Credit getCredit(int uId);
}