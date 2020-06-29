package com.vault.demo.service.backstage.house.Impl;

import com.vault.demo.dao.backstage.BackHouseDao;
import com.vault.demo.service.backstage.house.BackHouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackHouseServiceImpl implements BackHouseService {
    @Resource
    BackHouseDao bhd;
}
