package com.vault.demo.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vault.demo.service.backstage.loan.BackLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/back_loan")
public class BackLoanController {
    @Resource
    BackLoanService bls;

    @ResponseBody
    @RequestMapping("/loanData")
    public JSONObject loadLoanData(){
        JSONObject object = new JSONObject();
        List<Map> loan = bls.selLoan();
        object.put("total",loan.size());
        object.put("rows",JSON.toJSON(loan));
        return object;
    }
    @RequestMapping("/lookDetails")
    public String toDetails(){
        return "";
    }
    @RequestMapping("/tooExamine")
    public String tooExamine(){
        return "";
    }
}
