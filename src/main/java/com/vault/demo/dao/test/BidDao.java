package com.vault.demo.dao.test;

import com.vault.demo.bean.Bid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BidDao{
    List<Bid> allList();
    void deleteById(int id);
    void updateById(int id);
    void insert(Bid bid);
    List<Bid> selectByType(int bType);
}