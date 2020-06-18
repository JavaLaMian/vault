package com.vault.demo.dao.integral;


import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Integral;
import com.vault.demo.bean.MyIntegral;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface integralDao {
    int integral();
    List<Integral> selprobypage(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
    List<Integral> selprobypageSort(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
    List<Integral> selectByType(@Param("startRow") int startRow, @Param("pageSize") int pageSize, @Param("integralType")String integralType);
    List<Integral> selectByTypeSort(@Param("startRow") int startRow, @Param("pageSize") int pageSize, @Param("integralType")String integralType);

    Integral selectById(Integer id);
    Credit selectCredit(int id);
    List<Map> selectMyIntegral(int id);
    MyIntegral selectMyIntegral2(int id);

    int conversionAdd(MyIntegral myIntegral);
    int integralInventory(int inventory,int id); //修改库存
}
