package com.vault.demo.dao;

import com.vault.demo.bean.Loan;
import com.vault.demo.bean.PerBid;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PerBidDao {
    PerBid selectPerBidByPerBidId(Loan loan);
    void updatePerBidStatus(PerBid perBid);
}
