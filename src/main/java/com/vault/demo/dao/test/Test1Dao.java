package com.vault.demo.dao.test;

import com.vault.demo.bean.test.Test1;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface Test1Dao {
    List<Test1> allTest1();
}
