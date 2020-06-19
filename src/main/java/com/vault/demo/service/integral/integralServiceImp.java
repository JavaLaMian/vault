package com.vault.demo.service.integral;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Integral;
import com.vault.demo.bean.MyIntegral;
import com.vault.demo.common.Pager;
import com.vault.demo.dao.integral.integralDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class integralServiceImp implements integralService {
    @Resource
    private integralDao dao;

    @Override
    public int integral() {
        return dao.integral();
    }

    @Override
    public List<Integral> plistpage(Pager pager) {
        return dao.selprobypage((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize);
    }

    @Override
    public List<Integral> plistpageSort(Pager pager) {
        return dao.selprobypageSort((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize);
    }

    @Override
    public List<Integral> selectByType(Pager pager, String integralType) {
        System.out.println("当前页2："+pager.currPage);
        System.out.println("分页：查找第"+(pager.currPage-1)*pager.pageSize+"到第"+pager.currPage*pager.pageSize+"的数据");

        return dao.selectByType((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize,integralType);
    }

    @Override
    public List<Integral> selectByTypeSort(Pager pager, String integralType) {
        return dao.selectByTypeSort((pager.currPage-1)*pager.pageSize,pager.currPage*pager.pageSize,integralType);
    }

    @Override
    public Integral selectById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public Credit selectCredit(int id) {
        return dao.selectCredit(id);
    }

    @Override
    public List<Map> selectMyIntegral(int id) {
        return dao.selectMyIntegral(id);
    }

    @Override
    public MyIntegral selectMyIntegral2(int id) {
        return dao.selectMyIntegral2(id);
    }

    @Override
    public int conversionAdd(MyIntegral myIntegral) {
        return dao.conversionAdd(myIntegral);
    }

    @Override
    public int integralInventory(int inventory,int id) {
        return dao.integralInventory(inventory,id);
    }


}
