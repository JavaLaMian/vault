package com.vault.demo.service.integral;

import com.vault.demo.bean.integral;
import com.vault.demo.common.Pager;

import java.util.List;

public interface integralService {

    int integral();
    List<integral> plistpage(Pager pager);

    List<integral> selectByType(Pager pager,String integralType);
}
