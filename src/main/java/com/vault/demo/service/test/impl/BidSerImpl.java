package com.vault.demo.service.test.impl;

import com.vault.demo.bean.Bid;
import com.vault.demo.dao.test.BidDao;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BidSerImpl implements BidSer{

    @Resource
    BidDao bidDao;
    @Override
    public List<Bid> allList() {
        return bidDao.allList();
    }

    @Override
    public void deleteById(int id) {
      bidDao.deleteById(id);
    }

    @Override
    public void updateById(int id) {
            bidDao.updateById(id);
    }

    @Override
    public void insert(Bid bid) {
        bidDao.insert(bid);
    }

    @Override
    public List<Bid> selectByType(int bType){
        return bidDao.selectByType(bType);
    }
}
