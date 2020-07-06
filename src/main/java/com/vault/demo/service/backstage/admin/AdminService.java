package com.vault.demo.service.backstage.admin;

import com.vault.demo.bean.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Admin checkLogin(String account,String pwd);
    Admin getAdminById(int aid);
    void setPwd(int aid,String newpwd);
    List<Map> selUserRegWeek(String b1);
}
