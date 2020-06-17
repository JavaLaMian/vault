package com.vault.demo.service.test.impl;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;
import com.vault.demo.bean.Tender;
import com.vault.demo.dao.test.BidDao;
import com.vault.demo.service.test.BidSer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BidSerImpl implements BidSer {

    @Resource
    BidDao bidDao;
    @Override
    public List<Bid> allList() {
        return bidDao.allList(0);
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

    @Override
    public List<PerBid> selectPerB() {
        return bidDao.selectPerB(0);
    }

    @Override
    public Bid selectByBid(int bid) {
        List<Bid> bids = bidDao.allList(bid);
        return bids.get(0);
    }

    @Override
    public PerBid selectByPid(int pid) {
        List<PerBid> pids = bidDao.selectPerB(pid);
        return pids.get(0);
    }

    @Override
    public List<Tender> getTenderId(int tid,int id,int t) {
        return bidDao.selectTouId(tid,id,t);
    }

    @Override
    public int setTender(Tender tender) {
        return bidDao.addTender(tender);
    }

    @Override
    public int gouMai(float money, int uid) {
        return bidDao.moneyUserId(money,uid);
    }
}
