package com.vault.demo.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/back_loan")
public class BackLoanController {
    @ResponseBody
    @RequestMapping("/loanData")
    public JSONObject loadLoanData(){
        JSONObject object = new JSONObject();
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
