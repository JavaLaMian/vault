package com.vault.demo.service.test.impl;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;
import com.vault.demo.bean.Tender;
import com.vault.demo.bean.Userimf;
import com.vault.demo.dao.test.BidDao;
import com.vault.demo.service.test.BidSer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PerBid selectByPid(int perBid){
        List<PerBid> pids = bidDao.selectPerB(perBid);
        return pids.get(0);
    }

    @Override
    public List<Tender> getTenderId(int uId ,int bId,int bType){
        return bidDao.selectTouId(uId,bId,bType);
    }

    @Override
    public List<Map> selectTandU(int bid, int bType) {
        return bidDao.selectTandU(bid,bType);
    }

    @Override
    public int setTender(Tender tender) {
        return bidDao.addTender(tender);
    }

    @Override
    public int gouMai(float money, int uid) {
        return bidDao.moneyUserId(money,uid);
    }

    @Override
    public List<Map> getComList(int uId) {
        List<Map> mapList = bidDao.comUserList(uId);
//        for(int i = 0;i < mapList.size(); i++){
//            Map map = mapList.get(i);
//            System.out.println(map.get("clockLine"));
//            Integer yue = (Integer) map.get("clockLine");
//            map.put("clockLine",yue*30);
//        }
        return mapList;
    }

    @Override
    public Map padTouBiao(HttpSession session,int id,int t) {
        Map map = new HashMap();
        Bid bid = bidDao.allList(id).get(0);
        Userimf userimf = (Userimf) session.getAttribute("user");
        //判断该用户是否投过此标 用户id 标id 标类
        List<Tender> tenders = bidDao.selectTouId(userimf.getuId(), id, t);

        BigDecimal zon = new BigDecimal("0"); //当前用户投此标总额
        BigDecimal userMax = new BigDecimal(bid.getPersonLimit() + "");//个人累计限额
        if (tenders.size() != 0) { //投过此标
            for (int i = 0; i < tenders.size(); i++) {
                BigDecimal tou = new BigDecimal(tenders.get(i).getTenMoney() + "");
                zon = zon.add(tou);
            }
            map.put("to", tenders.get(tenders.size() - 1));
            map.put("kai", bid.getAddLimit());
        } else {
            map.put("to", null);
            map.put("kai", bid.getStartLimit());//起标额
        }
        //标限额
        BigDecimal allMax = new BigDecimal("0"); //所有用户投此标金额
        BigDecimal tMax = new BigDecimal(bid.getSumLimit() + "");   //总体累计限额
        List<Tender> zonTend = bidDao.selectTouId(0, id, t);
        if (zonTend.size() != 0) {
            for (int i = 0; i < zonTend.size(); i++) {
                BigDecimal tou = new BigDecimal(zonTend.get(i).getTenMoney() + "");
                allMax = allMax.add(tou);
            }
        }
        if (zon.compareTo(userMax) == -1 && allMax.compareTo(tMax) == -1) {
            BigDecimal aa = new BigDecimal("0");
            if (zon.compareTo(allMax) == 0 && aa.compareTo(allMax) == 0) {
                map.put("ketou", userMax); //没人投此标
            } else {
                zon = userMax.subtract(zon); //计算可投金额
                allMax = tMax.subtract(allMax);
                BigDecimal xiao = (zon.compareTo(allMax) == -1) ? zon : allMax;//取小的
                map.put("ketou", xiao);
            }
        } else {
            map.put("ketou", 0);
        }
        return map;
    }

    @Override
    public Date lastTenTime(int bid) {
        return bidDao.lastTenTime(bid);
    }


    @Override
    public List selectUser(int uId){
        return bidDao.selectUser(uId);
    }

    @Override
    public int countTenByBid(int bid, int bType) {
        return bidDao.countTenByBid(bid,bType);
    }
}
