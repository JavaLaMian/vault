package com.vault.demo.service.backstage.admin.impl;

import com.vault.demo.bean.Admin;
import com.vault.demo.dao.backstage.AdminDao;
import com.vault.demo.service.backstage.admin.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public Admin checkLogin(String account, String pwd) {
        return adminDao.checkLogin(account,pwd);
    }

    @Override
    public Admin getAdminById(int aid) {
        return adminDao.getAdminById(aid);
    }

    @Override
    public void setPwd(int aid, String newpwd) {
        adminDao.setPwd(aid,newpwd);
    }
}
