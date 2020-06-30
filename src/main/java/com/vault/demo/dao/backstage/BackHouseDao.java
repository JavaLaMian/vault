package com.vault.demo.dao.backstage;

import com.vault.demo.bean.House;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface BackHouseDao {
    List<Map> selHouseAll();
    House selHouseById(int hId);
    void updHouseStatus(House house);
}
