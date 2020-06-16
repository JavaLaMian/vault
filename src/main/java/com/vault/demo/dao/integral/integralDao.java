package com.vault.demo.dao.integral;


import com.vault.demo.bean.integral;
import com.vault.demo.common.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface integralDao {
    int integral();
    List<integral> selprobypage(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
    List<integral> selprobypageSort(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
    List<integral> selectByType(@Param("startRow") int startRow, @Param("pageSize") int pageSize,@Param("integralType")String integralType);
    List<integral> selectByTypeSort(@Param("startRow") int startRow, @Param("pageSize") int pageSize,@Param("integralType")String integralType);


}
