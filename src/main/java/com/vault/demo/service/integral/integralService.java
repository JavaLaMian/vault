package com.vault.demo.service.integral;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.integral;
import com.vault.demo.common.Pager;

import java.util.List;

public interface integralService {

    int integral();
    List<integral> plistpage(Pager pager);
    List<integral> plistpageSort(Pager pager);

    List<integral> selectByType(Pager pager,String integralType);

    List<integral> selectByTypeSort(Pager pager,String integralType);

    integral selectById(Integer id);

    Credit selectCredit(int id);
}
