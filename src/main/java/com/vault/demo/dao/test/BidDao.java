package com.vault.demo.dao.test;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.PerBid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BidDao{
    List<Bid> allList(@Param("bId")int bId);
    void deleteById(int id);
    void updateById(int id);
    void insert(Bid bid);
    List<Bid> selectByType(int bType);
    List<PerBid> selectPerB(@Param("perBid")int perBid);
    int countPerList();
    List<PerBid> pagePerB(@Param("startT") int startT, @Param("tSize") int tSize,@Param("inRate") float inRate,@Param("enquiry") float enquiry);
    int countPerPage(@Param("inRate") float inRate,@Param("enquiry") float enquiry);
}