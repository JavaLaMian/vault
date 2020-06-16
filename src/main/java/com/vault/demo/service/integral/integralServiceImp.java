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
        return dao.selprobypage((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize);
    }

    @Override
    public List<integral> plistpageSort(Pager pager) {
        return dao.selprobypageSort((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize);
    }

    @Override
    public List<integral> selectByType(Pager pager,String integralType) {
        System.out.println("当前页2："+pager.currPage);
        System.out.println("分页：查找第"+(pager.currPage-1)*pager.pageSize+"到第"+pager.currPage*pager.pageSize+"的数据");

        return dao.selectByType((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize,integralType);
    }

    @Override
    public List<integral> selectByTypeSort(Pager pager, String integralType) {
        return dao.selectByTypeSort((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize,integralType);
    }

    @Override
    public integral selectById(Integer id) {
        return dao.selectById(id);
    }


}
