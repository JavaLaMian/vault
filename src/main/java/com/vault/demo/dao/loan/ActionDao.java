package com.vault.demo.dao.loan;

import com.vault.demo.bean.Action;
import com.vault.demo.bean.Loan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ActionDao {
    Action selectActionByLId(Loan loan);         //根据loan表的LId查action对象
    void insertAction(Action action);   //新增action对象
    void updateAction(Action action);
}
