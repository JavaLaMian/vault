package com.vault.demo.dao.backstage;

import com.vault.demo.bean.Car;
import com.vault.demo.bean.Credit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface BackCarDao {
    List<Map> selCarAll();
    Car selCarById(int cId);
    Credit selCreditById(int uId);
}
