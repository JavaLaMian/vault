package com.vault.demo.service.backstage.loan.Impl;

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
}
