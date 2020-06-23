package com.vault.demo.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vault.demo.bean.*;
import com.vault.demo.service.backstage.loan.BackLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
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
    //审核贷款内容，并确定是否放款
    @RequestMapping("/tooExamine")
    public String tooExamine(int uId,Model m){
        Loan loan = bls.selLoanByUid(uId);
        Credit credit = bls.selCreditByUid(uId);
        m.addAttribute("credit",credit);
        m.addAttribute("loan",loan);
        if(loan.getLoanType()==1){//信用贷
            Userimf user = bls.selUserByUid(uId);
            m.addAttribute("user",user);
            return "backstage/credit_examine";
        }else{//抵押贷
            House house = bls.selHouseByHid(uId);
            Car car = bls.selCarByCid(uId);
            m.addAttribute("house",house);
            m.addAttribute("car",car);
            return "backstage/mortgage_examine";
        }
    }
    @RequestMapping("/examine")
    public String examine(int lId,float maxLimit,float minLimit,float interest,int lowLimit,int topLimit,int uId,PerBid perBid){
        Loan loan = bls.selLoanByLid(lId);
        loan.setMaxLimit(maxLimit);
        loan.setMinLimit(minLimit);
        loan.setInterest(interest);
        loan.setLowLimit(lowLimit);
        loan.setTopLimit(topLimit);
        loan.setApplicationEnd(new Date());
        loan.setLoanStatue(5);//审核通过等待确认
        perBid.setBidStatus(1);//设置散标为预售状态
        perBid.setStartTime(new Date());
        perBid.setBorrower(uId);
        int perid = bls.addPerBid(perBid);
        loan.setBidType(1);//设置贷款集资类型为散标类型
        loan.setBidId(perid);
        bls.updLoan(loan);
        return "redirect:/XMN/Loan_List";
    }
    @RequestMapping("/examine_fail")
    public String examineFail(){
        return "";
    }
}
