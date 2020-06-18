package com.vault.demo.service.integral;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Integral;
import com.vault.demo.bean.MyIntegral;
import com.vault.demo.common.Pager;

import java.util.List;
import java.util.Map;

public interface integralService {

    int integral();
    List<Integral> plistpage(Pager pager);
    List<Integral> plistpageSort(Pager pager);

    List<Integral> selectByType(Pager pager, String integralType);

    List<Integral> selectByTypeSort(Pager pager, String integralType);

    Integral selectById(Integer id);

    Credit selectCredit(int id);

    List<Map>  selectMyIntegral(int id);
    MyIntegral selectMyIntegral2(int id);
}
