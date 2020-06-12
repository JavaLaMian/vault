package com.vault.demo.service.backstage.admin;

import com.vault.demo.bean.Admin;

public interface AdminService {
    Admin checkLogin(String account,String pwd);
    Admin getAdminById(int aid);
    void setPwd(int aid,String newpwd);
}
