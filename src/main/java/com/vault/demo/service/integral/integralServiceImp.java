package com.vault.demo.service.integral;

import com.vault.demo.bean.integral;
import com.vault.demo.common.Pager;
import com.vault.demo.dao.integral.integralDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class integralServiceImp implements integralService {
    @Resource
    private integralDao dao;

    @Override
    public int integral() {
        return dao.integral();
    }

    @Override
    public List<integral> plistpage(Pager pager) {
        return dao.selprobypage((pager.currPage-1)*pager.pageSize,pager.pageSize);
    }

    @Override
    public List<integral> selectByType(Pager pager,String integralType) {
        return dao.selectByType((pager.currPage-1)*pager.pageSize,pager.pageSize,integralType);
    }
}
