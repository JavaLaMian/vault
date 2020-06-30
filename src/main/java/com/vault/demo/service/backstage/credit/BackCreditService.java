package com.vault.demo.service.backstage.credit;

import com.vault.demo.bean.Credit;
import com.vault.demo.bean.Userimf;

import java.util.List;
import java.util.Map;

public interface BackCreditService {
    List<Map> getCreditAll();
    Credit selCreditById(int creId);
    Userimf selUserById(int uId);
}
