package com.vault.demo.service.backstage.car.Impl;

import com.vault.demo.bean.Car;
import com.vault.demo.bean.Credit;
import com.vault.demo.dao.backstage.BackCarDao;
import com.vault.demo.service.backstage.car.BackCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BackCarServiceImpl implements BackCarService {
    @Resource
    BackCarDao bcd;

    @Override
    public List<Map> selCarAll() {
        return bcd.selCarAll();
    }

    @Override
    public Car selCarById(int cId) {
        return bcd.selCarById(cId);
    }

    @Override
    public Credit selCreditById(int uId) {
        return bcd.selCreditById(uId);
    }
}
