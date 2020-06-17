package com.vault.demo.service.backstage.tender.Impl;

import com.vault.demo.dao.backstage.BackTenderDao;
import com.vault.demo.service.backstage.tender.BackTenderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BackTenderServiceImpl implements BackTenderService {
    @Resource
    BackTenderDao backTenderDao;

    @Override
    public List<Map> selTender() {
        return backTenderDao.selTender();
    }
}
