package com.vault.demo.controller.backstage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vault.demo.bean.*;
import com.vault.demo.service.backstage.loan.BackLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
            Userimf user = bls.selUserByUid(uId);
            m.addAttribute("user",user);
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
        perBid.setRate(loan.getInterest());
        perBid.setBorrowTime(loan.getLowLimit());

        bls.addPerBid(perBid);
        System.out.println(perBid);
        int perid = perBid.getPerBid();
        System.out.println(perid);
        loan.setBidType(1);//设置贷款集资类型为散标类型
        loan.setBidId(perid);
        bls.updLoan(loan);
        return "redirect:/XMN/Loan_List";
    }
    @ResponseBody
    @RequestMapping("/examine_fail")
    public String examineFail(int lId){
        Loan loan = bls.selLoanByLid(lId);
        loan.setLoanStatue(4);//申请失败
        bls.updLoan(loan);
        return "success";
    }
    @ResponseBody
    @RequestMapping("/payment_data")
    public JSONObject getPaymentData(){
        JSONObject object = new JSONObject();
        List<Map> lbhs = bls.selLoanHistoryAll();
        object.put("total",lbhs.size());
        object.put("rows",JSON.toJSON(lbhs));
        return object;
    }
    @ResponseBody
    @RequestMapping("/toactivePayment")
    public String activePayment(Model m,int Id){

        return "";
    }
}
