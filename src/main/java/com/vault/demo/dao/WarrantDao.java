package com.vault.demo.dao;

import com.vault.demo.bean.Userimf;
import com.vault.demo.bean.Warrant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface WarrantDao {
    Warrant selectWarrantByUId(Userimf userimf);
    void insertWarrant(Warrant warrant);
}
