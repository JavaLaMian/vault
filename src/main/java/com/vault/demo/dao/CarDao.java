package com.vault.demo.dao;

import com.vault.demo.bean.Car;
import com.vault.demo.bean.Userimf;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CarDao {
    void insertCar(Car car);
    Car selectCarByUId(Userimf userimf);//根据用户uId查car
}
