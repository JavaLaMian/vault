package com.vault.demo.dao;

import com.vault.demo.bean.Loan;
import com.vault.demo.bean.LoanBankHistory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LoanBankHistoryDao {
    LoanBankHistory selectByLId(Loan loan);//根据loan表的lId查
    void insertLBH(LoanBankHistory loanBankHistory);
    void updateLBH(LoanBankHistory loanBankHistory);
}
