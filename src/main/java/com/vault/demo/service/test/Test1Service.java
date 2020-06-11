package com.vault.demo.service.test;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.test.Test1;

import java.util.List;

public interface Test1Service {
    List<Test1> allTest1();
    void saveBid(Bid bid);
    List<Bid> allBid();


}
