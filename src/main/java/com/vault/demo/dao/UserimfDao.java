package com.vault.demo.dao;

import com.vault.demo.bean.Userimf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserimfDao {
    int addUser(Userimf userimf);
    int updateUserAccount(@Param("uId") int uId, @Param("account") String account);//将新增的用户账号同步为xiaomuniu
    List<Userimf> allList();//所有用户的集合
    void deleteById(Userimf userimf);//根据ID删除用户
    Userimf selectOneByLogin(Userimf userimf);//根据 账号||邮箱 和密码查用户
    Userimf selectByUserimf(Userimf userimf); //查询是否存在这个用户
    int updateUserPwd(@Param("loginPsw") String loginPsw, @Param("email") String email);
}