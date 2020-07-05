package com.vault.demo.service.integral;

import com.vault.demo.bean.*;
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

    int conversionAdd(MyIntegral myIntegral);
    int integralInventory(int inventory,int id); //修改库存

    //理财红包兑换加入卡卷
    void bountyAdd(Bounty bounty);
    int signAdd(Sign sign);

    //查出上次签到时间
    Sign selectSignTime(int id);

    //查出今天签到人
    int selectSignCount(String time);

    void addIntrgral(Integral integral);
    int Update(Integral integral);
    int delete(int id);
}
