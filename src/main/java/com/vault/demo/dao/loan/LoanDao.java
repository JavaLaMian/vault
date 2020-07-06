package com.vault.demo.dao.loan;

import com.vault.demo.bean.Loan;
import com.vault.demo.bean.Userimf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface LoanDao {
    List<Loan> allLoanByUId(Userimf userimf);

    List<Loan> allList();

    void insert(Loan loan);

    void deleteById(Loan loan);//根据id删除对象

    Loan loanNow(@Param("u") Userimf userimf);

    void updateLoanStatus(Loan loan);

    List<Loan> listForStatusEq1();

    List<Loan> listForStatusEq2();
}
