package com.vault.demo.dao;

import com.vault.demo.bean.Car;
import com.vault.demo.bean.Userimf;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CarDao {
    public static final int CHECK = 4;  //审核中
    public static final int USED = 2;   //已抵押
    public static final int CAN = 1;    //可用
    public static final int NO = 0;     //不可用

    void insertCar(Car car);
    Car selectCarByUId(Userimf userimf);//根据用户uId查car
    void updateCarStatus(Car car);
}
