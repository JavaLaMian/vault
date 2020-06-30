package com.vault.demo.service.backstage.car;

import com.vault.demo.bean.Car;
import com.vault.demo.bean.Credit;

import java.util.List;
import java.util.Map;

public interface BackCarService {
    List<Map> selCarAll();
    Car selCarById(int cId);
    Credit selCreditById(int uId);
    void updCarStatus(Car car);
}
