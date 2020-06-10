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
    void deletById(@Param("b") Bid bid);
    void updateById(@Param("b") Bid bid);
    void insert(@Param("b") Bid bid);

}