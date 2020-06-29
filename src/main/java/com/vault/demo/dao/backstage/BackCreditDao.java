package com.vault.demo.dao.backstage;

import com.vault.demo.bean.Credit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface BackCreditDao {
    List<Map> getCreditAll();
}
