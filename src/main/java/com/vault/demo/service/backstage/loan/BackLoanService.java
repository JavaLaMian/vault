package com.vault.demo.service.backstage.loan;

import com.vault.demo.bean.*;

import java.util.List;
import java.util.Map;

public interface BackLoanService {
    List<Map> selLoan();
    Loan selLoanByUid(int uId);
    Credit selCreditByUid(int uId);
    House selHouseByHid(int hId);
    Car selCarByCid(int cId);
    Userimf selUserByUid(int uId);
    //void updLoan(Loan loan);
    Loan selLoanByLid(int lId);
}
