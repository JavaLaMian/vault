package com.vault.demo.service.loan.impl;

import com.vault.demo.bean.*;
import com.vault.demo.dao.HouseDao;
import com.vault.demo.dao.loan.CreditDao;
import com.vault.demo.dao.loan.LoanDao;
import com.vault.demo.service.loan.LoanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Resource
    LoanDao loanDao;

    @Resource
    CreditDao creditDao;

    @Resource
    HouseDao houseDao;

    @Override
    public void TestInsertLoan() {
        Loan loan = new Loan();
        loan.setLoanName("杨小馨的借款申请");
        loan.setuId(1);
        float wantMoney = (float) 10000.99;
        loan.setLoanWantMoney(wantMoney);
        loan.setApplicationTime(new Date());
        loan.setLoanPurpose("吃饭用的");

        loanDao.insert(loan);
    }

    @Override
    public List<Loan> TestAllLoan() {
        return loanDao.allList();
    }

    @Override
    public Loan LoanNow(Userimf userimf) {
        return loanDao.loanNow(userimf);
    }

    @Override
    public Credit selectCredit(Userimf userimf) {
        return creditDao.selectCreditByUserId(userimf);
    }

    @Override
    public void insertLoan(Loan loan) {
        loanDao.insert(loan);
    }

    @Override
    public House selectHouseByUId(Userimf userimf) {
        return houseDao.selectHouseByUId(userimf);
    }

    @Override
    public Car selectCarByUId(Userimf userimf) {
        return null;
    }

}
