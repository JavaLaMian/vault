package com.vault.demo.service.backstage.credit.Impl;

import com.vault.demo.bean.Credit;
import com.vault.demo.dao.backstage.BackCreditDao;
import com.vault.demo.service.backstage.credit.BackCreditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BackCreditServiceImpl implements BackCreditService {
    @Resource
    BackCreditDao bcd;

    @Override
    public List<Map> getCreditAll() {
        return bcd.getCreditAll();
    }
}
