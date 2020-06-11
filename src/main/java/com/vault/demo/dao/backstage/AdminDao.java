package com.vault.demo.dao.backstage;

import com.vault.demo.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AdminDao {
    Admin checkLogin(@Param("account") String account,@Param("pwd") String pwd);
}
