package com.vault.demo.service.test.impl;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;
import com.vault.demo.dao.test.BidDao;
import com.vault.demo.service.test.BidSer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BidSerImpl implements BidSer{

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
    public List<PerBid> selectPerB(int perBid) {
        return bidDao.selectPerB(perBid);
    }

    @Override
    public List<PerBid> pagePerB(int startT, int tSize, float inRate, float enquiry) {
        return bidDao.pagePerB(startT,tSize,inRate,enquiry);
    }

    @Override
    public int countPerList() {
        return bidDao.countPerList();
    }

    @Override
    public int countPerPage (float inRate, float enquiry) {
        return bidDao.countPerPage( inRate,  enquiry);
    }

    @Override
    public Bid selectByBid(int bid) {
        List<Bid> bids = bidDao.allList(bid);
        return bids.get(0);
    }



    @Override
    public PerBid selectByPid(int perBid) {
        List<PerBid> pids = bidDao.selectPerB(perBid);
        return pids.get(0);
    }
}
