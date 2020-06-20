package com.vault.demo.service.backstage.adxmn.adxmnImpl;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Loan;
import com.vault.demo.dao.backstage.AdxmnDao;
import com.vault.demo.service.backstage.adxmn.selevicexmn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class selevicexmnImpl implements selevicexmn {
    @Resource
    AdxmnDao dao;
    @Override
    public int addBid(Bid bid) {
        int ok = dao.addBid(bid);
       return ok;
    }

    @Override
    public List<Bid> Bidlist() {
        return dao.Bidlist();
    }

    @Override
    public List<Bid> selectgetBid(Bid bid) {
        return dao.selectgetBid(bid);
    }

    @Override
    public int updateBid(Bid bid) {
        int ok = dao.updateBid(bid);
        return ok;
    }

    @Override
    public int dateBid(Bid bid) {
        int ok = dao.dateBid(bid);
        return ok;
    }

    @Override
    public List<Credit> CreditList() {
        return dao.CreditList();
    }

    @Override
    public List<Map> selectgetCredit(Credit bid) {
        return dao.selectgetCredit(bid);
    }

    @Override
    public int updateCredit(Credit credit) {
        int ok = dao.updateCredit(credit);
        return ok;
    }

    @Override
    public List<Map> integralList() {
        return dao.integralList();
    }

    @Override
    public void updategetbiBid(int bidStatus, int id) {
        dao.updategetbiBid(bidStatus,id);
    }

    @Override
    public float selectBidmoney(int bidType) {
        return dao.selectBidmoney(bidType);
    }

    @Override
    public int selectgetByid() {
        return dao.selectgetByid();
    }

}
