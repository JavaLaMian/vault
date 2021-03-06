package com.vault.demo.dao.backstage;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface BackTenderDao {
    List<Map> selTender(@Param("pageSize") int pageSize,@Param("currentPage") int currentPage);
}
