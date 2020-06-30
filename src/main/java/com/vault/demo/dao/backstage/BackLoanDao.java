package com.vault.demo.dao.backstage;

import com.vault.demo.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface BackLoanDao {
    List<Map> selLoan();
    Loan selLoanByUid(int uId);
    Credit selCreditByUid(int uId);
    House selHouseByHid(int hId);
    Car selCarByCid(int cId);
    Userimf selUserByUid(int uId);
    void updLoan(@Param("loan") Loan loan);
    Loan selLoanByLid(int lId);
    void addPerBid(@Param("perBid") PerBid perBid);
    void updPerBidStatus(@Param("loan")Loan loan,@Param("action") Action action);
    List<Map> selLoanHistoryAll();
}
