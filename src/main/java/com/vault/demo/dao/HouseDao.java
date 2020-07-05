package com.vault.demo.dao;

import com.vault.demo.bean.House;
import com.vault.demo.bean.Userimf;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HouseDao {
    void insertHouse(House house);
    House selectHouseByUId(Userimf userimf);//根据用户id查用户绑定的房产
    void updateHouseStatus(House house);
}
