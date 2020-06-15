package com.vault.demo.service.loan;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Loan;
import com.vault.demo.bean.Userimf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanService {
    void TestInsertLoan();//测试loan的插入
    List<Loan> TestAllLoan();
    Loan LoanNow( Userimf userimf);
    Credit selectCredit(Userimf userimf);
}
