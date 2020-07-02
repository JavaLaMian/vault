package com.vault.demo.service.loan;

import com.vault.demo.bean.*;

import java.util.List;

public interface LoanService {
    public static final int CHECK = 0;      //申请中
    public static final int IN = 1;         //放款中
    public static final int FORGIVE = 2;    //待偿还
    public static final int CLOSE = 3;      //已偿还
    public static final int FILED = 4;      //申请失败
    public static final int OK = 5;         //申请通过等待确认

    Userimf selectUserimfByUId(Userimf userimf);
    void TestInsertLoan();//测试loan的插入
    List<Loan> TestAllLoan();
    Loan LoanNow( Userimf userimf);
    Credit selectCredit(Userimf userimf);
    void insertLoan(Loan loan);//提交贷款申请
    House selectHouseByUId(Userimf userimf);
    Car selectCarByUId(Userimf userimf);
    void insertCar(Car car);
    void insertHouse(House house);
    Action selectActionByLId(Loan loan);
    void insertAction(Action action);//存储用户贷款记录，一般在用户提交贷款申请时就初始新增
    void updateAction(Action action);
    void updateLoanStatus(Loan loan);
    LoanBankHistory selectByLId(Loan loan);
    UserBank selectByUId(Userimf user);
    LoanBankHistory insertLoanBankHistory(Loan loan,UserBank userBank,Action action);
    Warrant selectWarrantByUId(Userimf userimf);
    void insertWarrant(Warrant warrant);
}
