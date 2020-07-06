package com.vault.demo.dao;

import com.vault.demo.bean.Loan;
import com.vault.demo.bean.PerBid;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface PerBidDao {
    List<Map> selectPerBidByPerBidId(@Param("loan") Loan loan);
    void updatePerBidStatus(PerBid perBid);
}
