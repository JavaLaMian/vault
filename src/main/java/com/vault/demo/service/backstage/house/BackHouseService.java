package com.vault.demo.service.backstage.house;

import com.vault.demo.bean.House;

import java.util.List;
import java.util.Map;

public interface BackHouseService {
    List<Map> selHosueAll();
    House selHouseById(int hId);
    void updHouseStatus(House house);
}
