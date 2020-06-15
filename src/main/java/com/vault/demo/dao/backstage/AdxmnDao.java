package com.vault.demo.dao.backstage;

import com.vault.demo.bean.Bid;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AdxmnDao {
    int addBid(Bid bid);//新增投标
    List<Bid> Bidlist();//查询投标信息
    List<Bid> selectgetBid(Bid bid);//根据id查询出投标信息
}
