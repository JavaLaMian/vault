package com.vault.demo.service.backstage.house.Impl;

import com.vault.demo.bean.House;
import com.vault.demo.dao.backstage.BackHouseDao;
import com.vault.demo.service.backstage.house.BackHouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BackHouseServiceImpl implements BackHouseService {
    @Resource
    BackHouseDao bhd;

    @Override
    public List<Map> selHosueAll() {
        return bhd.selHouseAll();
    }

    @Override
    public House selHouseById(int hId) {
        return bhd.selHouseById(hId);
    }

    @Override
    public void updHouseStatus(House house) {
        bhd.updHouseStatus(house);
    }
}
