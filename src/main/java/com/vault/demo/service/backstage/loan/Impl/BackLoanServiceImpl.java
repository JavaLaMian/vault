package com.vault.demo.service.backstage.loan.Impl;

import com.vault.demo.dao.backstage.BackLoanDao;
import com.vault.demo.service.backstage.loan.BackLoanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackLoanServiceImpl implements BackLoanService{
    @Resource
    BackLoanDao backLoanDao;
}
