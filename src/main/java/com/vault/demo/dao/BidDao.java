package com.vault.demo.dao;

import com.vault.demo.bean.Bid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BidDao {
    List<Bid> allList();
    void deleteById(Bid bid);
    void updateById(Bid bid);
    void insert(Bid bid);
}