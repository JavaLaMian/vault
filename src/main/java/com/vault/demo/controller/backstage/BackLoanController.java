package com.vault.demo.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/back_loan")
public class BackLoanController {
    @ResponseBody
    @RequestMapping("/loanData")
    public JSON loadLoanData(){
        JSON data = new JSONArray();
        return data;
    }
}
