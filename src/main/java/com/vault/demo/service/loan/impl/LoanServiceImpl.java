package com.vault.demo.service.loan.impl;

import com.vault.demo.bean.*;
import com.vault.demo.dao.*;
import com.vault.demo.dao.loan.CreditDao;
import com.vault.demo.dao.loan.ActionDao;
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

    @Resource
    CarDao carDao;

    @Resource
    ActionDao actionDao;

    @Resource
    BankDao bankDao;

    @Resource
    LoanBankHistoryDao loanBankHistoryDao;

    @Resource
    WarrantDao warrantDao;

    @Resource
    UserimfDao userimfDao;

    @Resource
    RepaymenDao repaymenDao;



    @Override
    public Userimf selectUserimfByUId(Userimf userimf) {
        return userimfDao.selectUserimfByUId(userimf);
    }

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
    public List<Loan> allLoanByUId(Userimf userimf) {
        return loanDao.allLoanByUId(userimf);
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
        return carDao.selectCarByUId(userimf);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public void insertHouse(House house) {
        houseDao.insertHouse(house);
    }

    @Override
    public void insertJob(Credit credit) {
        creditDao.insertJob(credit);
    }

    @Override
    public Action selectActionByLId(Loan loan) {
        return actionDao.selectActionByLId(loan);
    }

    @Override
    public void insertAction(Action action) {
        actionDao.insertAction(action);
    }

    @Override
    public void updateAction(Action action) {
        actionDao.updateAction(action);
    }

    @Override
    public void updateLoanStatus(Loan loan) {
        loanDao.updateLoanStatus(loan);
    }

    @Override
    public LoanBankHistory selectByLId(Loan loan) {
        return loanBankHistoryDao.selectByLId(loan);
    }

    @Override
    public UserBank selectByUId(Userimf user) {
        return bankDao.getBC(user.getuId());
    }

    @Override
    public LoanBankHistory insertLoanBankHistory(Loan loan, UserBank userBank,Action action) {
        LoanBankHistory loanBankHistory = new LoanBankHistory();
        loanBankHistory.setAcMoney(action.getAcMoney());
        loanBankHistory.setBankId(userBank.getBankId());
        loanBankHistory.setlId(loan.getlId());
        loanBankHistory.setStatus(0);

        loanBankHistoryDao.insertLBH(loanBankHistory);

        return loanBankHistory;
    }

    @Override
    public Warrant selectWarrantByUId(Userimf userimf) {
        return warrantDao.selectWarrantByUId(userimf);
    }

    @Override
    public void insertWarrant(Warrant warrant) {
        warrantDao.insertWarrant(warrant);
    }

    @Override
    public void updateHouseByUId(House house) {
        houseDao.updateHouseStatus(house);
    }

    @Override
    public void updateCarByUId(Car car) {
        carDao.updateCarStatus(car);
    }

    @Override
    public void updateWarrantStatusByWId(Warrant warrant) {
        warrantDao.updateWarrantStatusByWId(warrant);
    }

    @Override
    public void updateAvanBanlanceByUId(Userimf userimf) {
        userimfDao.updateAvaBalanceByUId(userimf);
    }

    @Override
    public void updateActionStatusByAId(Action action) {
        actionDao.updateStatusByAId(action);
    }

    @Override
    public void insertRepaymen(Repaymen repaymen) {
        repaymenDao.insertRepaymen(repaymen);
    }


}
