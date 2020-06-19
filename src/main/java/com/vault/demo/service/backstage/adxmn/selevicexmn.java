package com.vault.demo.service.backstage.adxmn;

import com.vault.demo.bean.Bid;
import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Loan;

import java.util.List;
import java.util.Map;


public interface selevicexmn {
    int addBid(Bid bid);//新增投标
    List<Bid> Bidlist();//查询投标信息
    List<Bid> selectgetBid(Bid bid);//根据id查询出投标信息
    int updateBid(Bid bid);//修改投标信息
    int dateBid(Bid bid);//删除投标信息
    List<Credit> CreditList();//查询贷款用户提交的信用信息
    List<Map> selectgetCredit(Credit bid);//根据id查询出信用信息
    int updateCredit(Credit credit);//根据id修改信用信息
    List<Map> integralList();//查询出积分兑换信息
}
