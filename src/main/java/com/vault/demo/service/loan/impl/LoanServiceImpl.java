package com.vault.demo.service.loan.impl;

import com.vault.demo.dao.loan.LoanDao;
import com.vault.demo.service.loan.LoanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoanServiceImpl implements LoanService {
    @Resource
    LoanDao loanDao;
}
