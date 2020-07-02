package com.vault.demo.service.backstage.loan.Impl;

import com.vault.demo.bean.*;
import com.vault.demo.dao.backstage.BackLoanDao;
import com.vault.demo.service.backstage.loan.BackLoanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BackLoanServiceImpl implements BackLoanService{
    @Resource
    BackLoanDao backLoanDao;

    @Override
    public List<Map> selLoan() {
        return backLoanDao.selLoan();
    }

    @Override
    public Loan selLoanByUid(int uId) {
        return backLoanDao.selLoanByUid(uId);
    }

    @Override
    public Credit selCreditByUid(int uId) {
        return backLoanDao.selCreditByUid(uId);
    }

    @Override
    public House selHouseByHid(int hId) {
        return backLoanDao.selHouseByHid(hId);
    }

    @Override
    public Car selCarByCid(int cId) {
        return backLoanDao.selCarByCid(cId);
    }

    @Override
    public Userimf selUserByUid(int uId) {
        return backLoanDao.selUserByUid(uId);
    }

    @Override
    public void updLoan(Loan loan) {
        backLoanDao.updLoan(loan);
    }

    @Override
    public Loan selLoanByLid(int lId) {
        return backLoanDao.selLoanByLid(lId);
    }

    @Override
    public void addPerBid(PerBid perBid) {
        backLoanDao.addPerBid(perBid);
    }

    @Override
    public List<Map> selLoanHistoryAll() {
        return backLoanDao.selLoanHistoryAll();
    }
}
