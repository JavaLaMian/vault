package com.vault.demo.dao;

import com.vault.demo.bean.Userimf;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserimfDao {
    List<Userimf> allList();//所有用户的集合
    void deleteById(Userimf userimf);//根据ID删除用户
    Userimf selectOneByLogin(Userimf userimf);//根据 账号||邮箱 和密码查用户
}
