package com.vault.demo.service.backstage.admin.impl;

import com.vault.demo.dao.backstage.AdminDao;
import com.vault.demo.service.backstage.admin.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;

}
