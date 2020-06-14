package com.vault.demo.dao.loan;

import com.vault.demo.bean.Loan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface LoanDao {
    List<Loan> allList();

    void insert(Loan loan);

    void deleteById(Loan loan);//根据id删除对象
}
