package com.vault.demo.dao.backstage;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Loan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AdxmnDao {
    int addBid(Bid bid);//新增投标
    List<Bid> Bidlist();//查询投标信息
    List<Bid> selectgetBid(Bid bid);//根据id查询出投标信息
    int updateBid(Bid bid);//修改投标信息
    int dateBid(Bid bid);//删除投标信息
    List<Credit> CreditList();//查询贷款用户提交的信用信息
    List selectgetCredit(Credit bid);//根据id查询出信用信息
}
