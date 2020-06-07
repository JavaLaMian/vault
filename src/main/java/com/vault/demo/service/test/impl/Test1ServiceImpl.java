package com.vault.demo.service.test.impl;

import com.vault.demo.bean.test.Test1;
import com.vault.demo.dao.test.Test1Dao;
import com.vault.demo.service.test.Test1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Test1ServiceImpl implements Test1Service {

    @Resource
    Test1Dao test1Dao;

    @Override
    public List<Test1> allTest1() {
        return test1Dao.allTest1();
    }
}
