package com.vault.demo.dao;

import com.vault.demo.bean.Repaymen;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RepaymenDao {
    void insertRepaymen(Repaymen repaymen);
}
