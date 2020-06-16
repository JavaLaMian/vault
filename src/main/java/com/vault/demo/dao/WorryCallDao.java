package com.vault.demo.dao;

import com.vault.demo.bean.Userimf;
import com.vault.demo.bean.WorryCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface WorryCallDao {
    void saveWorryCall(WorryCall worryCall);
    List<WorryCall> selectWorryByUId(Userimf userimf);//根据用户id查紧急联系人
}
