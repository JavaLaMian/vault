package com.vault.demo.dao.loan;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Userimf;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CreditDao {
    List<Credit> allList();
    void insert(Credit credit);
    void deleteById(Credit credit);
    Credit selectCreditByUserId(Userimf userimf);//根据用户ID查询用户高级信用记录
}
