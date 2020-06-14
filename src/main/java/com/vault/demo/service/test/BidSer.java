package com.vault.demo.service.test;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;

import java.util.List;

public interface BidSer{
    public static final int TYPE_NEWCOMER = 1;//新手标
    public static final int TYPE_NORM = 2;//散标
    public static final int TRANSFER_YES = 1;//可自动转让
    public static final int TRANSFER_NO = 0;//不可自动转让
    public static final int STATUE_READY = 1;
    public static final int STATUE_EMPTY = 3;
    public static final int STATUE_CLOSE = 2;
    public static final int DEPOSIT_CURRENT =1;
    public static final int DEPOSIT_FIX=2;


    List<Bid> allList();
    void deleteById(int bid);
    void updateById(int bid);
    void insert(Bid bid);
    List<Bid> selectByType(int bType);
    List<PerBid> selectPerB();

}