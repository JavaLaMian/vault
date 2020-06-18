package com.vault.demo.service.loan;

import com.vault.demo.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanService {
    public static final int CHECK = 0;      //申请中
    public static final int IN = 1;         //放款中
    public static final int FORGIVE = 2;    //待偿还
    public static final int CLOSE = 3;      //已偿还
    public static final int FILED = 4;      //申请失败
    public static final int OK = 5;         //申请通过等待确认

    void TestInsertLoan();//测试loan的插入
    List<Loan> TestAllLoan();
    Loan LoanNow( Userimf userimf);
    Credit selectCredit(Userimf userimf);
    void insertLoan(Loan loan);//提交贷款申请
    House selectHouseByUId(Userimf userimf);
    Car selectCarByUId(Userimf userimf);
}
