package com.vault.demo.dao.backstage;

import com.vault.demo.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface AdminDao {
    Admin checkLogin(@Param("account") String account,@Param("pwd") String pwd);
    Admin getAdminById(int aid);
    void setPwd(@Param("aid") int aid,@Param("newpwd") String newpwd);
    List<Map> selUserRegWeek(@Param("b1")String b1);
}
