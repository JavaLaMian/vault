package com.vault.demo.dao.backstage;

import com.vault.demo.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface AdxmnDao {
    int addBid(Bid bid);//新增投标
    List<Bid> Bidlist();//查询投标信息
    List<Bid> Bidlistpage(int thisPage,int titleSize);//查询投标信息 加上分页查询代码
    List<Bid> selectgetBid(Bid bid);//根据id查询出投标信息
    int updateBid(Bid bid);//修改投标信息
    int dateBid(Bid bid);//删除投标信息
    List<Credit> CreditList();//查询贷款用户提交的信用信息
    List<Map> selectgetCredit(Credit bid);//根据id查询出信用信息
    int updateCredit(Credit credit);//根据id修改信用信息
    List<Map> integralCoin();//查询出积分兑换信息
    List<Map> integralCoin2(String email);//查询出积分兑换信息
    List<Integral> integralList();//查出商品列表
    void updategetbiBid(@Param("bidStatus") int bidStatus,@Param("id") int id);//根据id修改投标状态
    float selectBidmoney(int bidType);//根据标种查询出标的总金额
    List<String> selectgetByid();//查询出用户投了哪些标
    List<String> selectgetBytenid(@Param("id") String id);//根据标id查询出哪些用户
    List<Tender> slecttendermoney(@Param("bid") String bid,@Param("uid") String uid);//根据标和用户id查询金额
    Float seleUsermoney(int id);//根据id查询出用户余额
    void updateuserMoney(@Param("money") double money,@Param("id") int id);//根据用户id相加余额
    void addusermoney(Recharge recharge);//新增用户余额增加记录
    Float selecttendertenMoney(@Param("uid") int uid,@Param("bid") int bid);//根据标id和用户id确认代收本金
    void updatetenderMoney(@Param("money") Float money,@Param("uid") int uid,@Param("bid") int bid);//根据标id和用户id修改代收本金
    List<Tender> tenderlist();//查询出用户投资的全部信息
    void updateTender(@Param("tid") int tid,@Param("tentype") int tentype);//修改订单状态
    int slectBidtotalTitle();//查询出标的总条数
    List<Map> Bidlistall();//查询出标的所有信息加上投的金额
}
