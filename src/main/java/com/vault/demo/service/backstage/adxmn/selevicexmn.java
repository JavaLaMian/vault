package com.vault.demo.service.backstage.adxmn;

import com.vault.demo.bean.Bid;

import java.util.List;


public interface selevicexmn {
    int addBid(Bid bid);//新增投标
    List<Bid> Bidlist();//查询投标信息
    List<Bid> selectgetBid(Bid bid);//根据id查询出投标信息
}
