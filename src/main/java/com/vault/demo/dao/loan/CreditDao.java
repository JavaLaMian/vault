package com.vault.demo.dao.loan;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Recharge;
import com.vault.demo.bean.Userimf;
import com.vault.demo.bean.Withdraw;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface CreditDao {
    List<Credit> allList();
    void insert(Credit credit);
    void deleteById(Credit credit);
    Credit selectCreditByUserId(Userimf userimf);//根据用户ID查询用户高级信用记录

    int addRecharge(Recharge rechargeh);
    int addWithdraw(Withdraw withdraw);

    List<Map> getRechargeMax(@Param("uId") int uId);
    List<Map> getWithdrawMax(@Param("uId") int uId);
}
