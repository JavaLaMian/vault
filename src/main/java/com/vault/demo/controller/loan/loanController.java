package com.vault.demo.controller.loan;

import com.vault.demo.bean.*;
import com.vault.demo.dao.BankDao;
import com.vault.demo.dao.PerBidDao;
import com.vault.demo.dao.UserimfDao;
import com.vault.demo.dao.WorryCallDao;
import com.vault.demo.dao.backstage.BackLoanDao;
import com.vault.demo.dao.file.FileUpload;
import com.vault.demo.service.loan.LoanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/loan")
@Controller
public class loanController {

    @Resource
    UserimfDao userimfDao;

    @Resource
    LoanService loanService;

    @Resource
    BankDao bankDao;

    @Resource
    WorryCallDao worryCallDao;

    @Resource
    FileUpload fileUpload;

    @Resource
    BackLoanDao backLoanDao;

    @Resource
    PerBidDao perBidDao;

    @RequestMapping("/main")
    public String loanmain(){
        return "loan/loanMain";
    }
    //去登录
    @RequestMapping("/tologin")
    public String toLogin(){
        return "loan/loanLogin";
    }
    @RequestMapping("/tologin2")
    public String toLogin2(){
        return "loan/loanLogin2";
    }

    //登录
    @RequestMapping("/login")
    public String login(String account , String password , HttpSession session){
        Userimf userimf = new Userimf();

        if (account.length() < 9){//字符总长小于9，是为邮箱登录
            userimf.setEmail(account);
        }else if (account.substring(0,9).equals("xiaomuniu")){//字符前9为‘xiaomuniu’，是为账号登录
            userimf.setAccount(account);
        }else {//大于9且字符前9不为‘xiaomuniu’，是为邮箱登录
            userimf.setEmail(account);
        }

        userimf.setLoginPsw(password);

        Userimf userimfEX = userimfDao.selectOneByLogin(userimf);

        session.setAttribute("user",userimfEX);
        session.setAttribute("userLoan",loanService.LoanNow(userimfEX));//获取用户的贷款信息

        return "redirect:/loan/main2";
    }

    @RequestMapping("/checkUserAccountAndPwd")
    @ResponseBody
    public String checkUserAccountAndPwd(String account,String password){
        System.out.println("账号"+account+"密码:"+password);

        Userimf userimf = new Userimf();

        if (account.length() < 9){//字符总长小于9，是为邮箱登录
            userimf.setEmail(account);
        }else if (account.substring(0,9).equals("xiaomuniu")){//字符前9为‘xiaomuniu’，是为账号登录
            userimf.setAccount(account);
        }else {//大于9且字符前9不为‘xiaomuniu’，是为邮箱登录
            userimf.setEmail(account);
        }

        userimf.setLoginPsw(password);

        Userimf userimfEX = userimfDao.selectOneByLogin(userimf);

        System.out.println(userimfEX);
        if (userimfEX == null){
            System.out.println("登录失败");

            return "NO";
        }else {
            return "YES";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");

        return "redirect:/loan/main";
    }

    //借贷中心
    @RequestMapping("/main2")
    public String main2(HttpSession session,Model model) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        session.setAttribute("userLoan",loanService.LoanNow((Userimf) session.getAttribute("user")));
        model.addAttribute("action",loanService.selectActionByLId((Loan) session.getAttribute("userLoan")));
        model.addAttribute("loanBankHistory",loanService.selectByLId((Loan) session.getAttribute("userLoan")));
        model.addAttribute("credit",loanService.selectCredit((Userimf) session.getAttribute("user")));

        return "loan/loanJie";
    }

    //判断用户哪些信用信息没绑定
    public String notPushModel(Credit credit, UserBank userBank, Userimf userimf, WorryCall worryCall){
        String model = "";

        if (credit == null) {
            if (!"".equals(model)){
                model += "、用户姓名、身份证号、岗位信息、工资情况";
            }else {
                model += "用户姓名、身份证号、岗位信息、工资情况";
            }
        }else {
            if (credit.getName() == null || "".equals(credit.getName())){
                if (!"".equals(model)){
                    model += "、用户姓名";
                }else {
                    model += "用户姓名";
                }
            }

            if (credit.getIdentity() == null || "".equals(credit.getIdentity())){
                if (!"".equals(model)){
                    model += "、身份证";
                }else {
                    model += "身份证";
                }
            }

            if (credit.getDepart() == null || "".equals(credit.getDepart())){
                if (!"".equals(model)){
                    model += "、岗位信息";
                }else {
                    model += "岗位信息";
                }
            }

            if (credit.getWages() == null || "".equals(credit.getWages())){
                if (!"".equals(model)){
                    model += "、工资情况";
                }else {
                    model += "工资情况";
                }
            }
        }

        if (userBank == null){
            if (!"".equals(model)){
                model += "、银行卡";
            }else {
                model += "银行卡";
            }
        }

        if (userimf.getPhe() == null || "".equals(userimf.getPhe())){
            if (!"".equals(model)){
                model += "、联系电话";
            }else {
                model += "联系电话";
            }
        }

        if (userimf.getDealPsw() == null || "".equals(userimf.getDealPsw())){
            if (!"".equals(model)){
                model += "、支付密码";
            }else {
                model += "支付密码";
            }
        }

        if (worryCall == null){
            if (!"".equals(model)){
                model += "、紧急联系人";
            }else {
                model += "紧急联系人";
            }
        }

        return model;
    }

    //去借款申请页面
    @RequestMapping("/toloanJie")
    public String toloanJie(@Param( "step") Integer step, Model model, HttpSession session, String loanTypeStr , Loan loan, Action action) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        session.setAttribute("userLoan",loanService.LoanNow((Userimf) session.getAttribute("user")));
        session.setAttribute("user",loanService.selectUserimfByUId((Userimf) session.getAttribute("user")));
        model.addAttribute("dealPwd",((Userimf)session.getAttribute("user")).getDealPsw());

        if ("".equals(loanTypeStr)) {

        }else if ("xinyong".equals(loanTypeStr)){ //如果是信用贷款类型
            Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
            UserBank userBank = bankDao.getBC(((Userimf)session.getAttribute("user")).getuId());
            Userimf userimf = (Userimf)session.getAttribute("user");
            WorryCall worryCall = worryCallDao.selectWorryByUId(userimf);

            model.addAttribute("loanTypeStr",1);

            String notPushModelStr = notPushModel(credit,userBank,userimf,worryCall);

            if (!"".equals(notPushModelStr)){
                model.addAttribute("loanNotPushType","请完善您的个人详细信息（" + notPushModelStr +"）");

                return "loan/creditNotPush";
            }

            if (credit.getType() != 2){

                model.addAttribute("loanNotPushType","请耐心等待您的个人详细信息审核通过");

                return "loan/creditNotPush";
            }

        }else if ("diya".equals(loanTypeStr)){      //如果是抵押贷款类型

            Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
            UserBank userBank = bankDao.getBC(((Userimf)session.getAttribute("user")).getuId());
            Userimf userimf = (Userimf)session.getAttribute("user");
            WorryCall worryCall = worryCallDao.selectWorryByUId(userimf);

            model.addAttribute("loanTypeStr",2);

            String notPushModelStr = notPushModel(credit,userBank,userimf,worryCall);

            if (!"".equals(notPushModelStr)){
                model.addAttribute("loanNotPushType","请完善您的个人详细信息（" + notPushModelStr +"）");

                return "loan/creditNotPush";
            }

            if (credit.getType() != 2){

                model.addAttribute("loanNotPushType","请耐心等待您的个人详细信息审核通过");

                return "loan/creditNotPush";
            }

            Car car = loanService.selectCarByUId(userimf);
            House house = loanService.selectHouseByUId(userimf);

            System.out.println(car + "\n" + house);

            if (car == null && house == null){
                model.addAttribute("loanNotPushType","您当前没有可抵押的资产，快去上传吧！");

                return "loan/creditNotPush";
            }

            if (car.getStatus() == 4 && house.getStatus() == 4){
                model.addAttribute("loanNotPushType","请耐心等待您的资产信息审核完毕！");

                return "loan/creditNotPush";
            }

            if (car.getStatus() != 1 && house.getStatus() != 1){
                model.addAttribute("loanNotPushType","当前用户资产无法使用，详细信息请咨询客服！");

                return "loan/creditNotPush";
            }

            model.addAttribute("car",car);
            model.addAttribute("house",house);
        }else if ("danbao".equals(loanTypeStr)){
            Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
            UserBank userBank = bankDao.getBC(((Userimf)session.getAttribute("user")).getuId());
            Userimf userimf = (Userimf)session.getAttribute("user");
            WorryCall worryCall = worryCallDao.selectWorryByUId(userimf);

            model.addAttribute("loanTypeStr",2);

            String notPushModelStr = notPushModel(credit,userBank,userimf,worryCall);

            if (!"".equals(notPushModelStr)){
                model.addAttribute("loanNotPushType","请完善您的个人详细信息（" + notPushModelStr +"）");

                return "loan/creditNotPush";
            }

            if (credit.getType() != 2){

                model.addAttribute("loanNotPushType","请耐心等待您的个人详细信息审核通过");

                return "loan/creditNotPush";
            }


            Warrant warrant = loanService.selectWarrantByUId((Userimf) session.getAttribute("user"));

            if (warrant == null){
                model.addAttribute("loanNotPushType","您当前没有可担保的信息，快去上传吧！");

                return "loan/creditNotPush";
            }else if (warrant.getStatus() == 0){

                model.addAttribute("loanNotPushType","您的担保的信息正在审核中！");

                return "loan/creditNotPush";
            }else if (warrant.getStatus() != 0 && warrant.getStatus() != 1){

                model.addAttribute("loanNotPushType","您当前没有可用的担保信息！");

                return "loan/creditNotPush";
            }

            model.addAttribute("loanTypeStr",3);

            model.addAttribute("warrant",warrant);

        }

        System.out.println("step："+step);
        System.out.println("loan："+loan);

        if(step==null){
            step = 1 ;

        }else if (step == 2){ //用户提交贷款信息
            float loanWantMoney = loan.getLoanWantMoney();

            loanWantMoney = (float) (loanWantMoney * 0.0001);

            System.out.println(loanWantMoney);

            loan.setLoanWantMoney(loanWantMoney);
            loan.setApplicationTime(new Date());
            loan.setLoanStatue(LoanService.CHECK);

            if (loanService.LoanNow((Userimf) session.getAttribute("user")) != null){ //防止表单重复提交
                return "redirect:/loan/toloanJie";
            };

            loanService.insertLoan(loan);//把贷款申请信息存到数据库

            action.setlId(loan.getlId());
            action.setAcMoney(0);

            System.out.println("action"+action);

            loanService.insertAction(action);//把贷款记录信息存到数据库

            Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));

            model.addAttribute("loan",loan);
            model.addAttribute("credit",credit);

        }

        Userimf userimf = (Userimf)session.getAttribute("user");

        Loan loanEX = loanService.LoanNow(userimf);

        if (loanEX != null){        //如果目前用户的贷款有正在审核的情况
            if (loanEX.getLoanStatue() == 0){
                step = 2;
                System.out.println("loanEX：" + loanEX);
                model.addAttribute("loan",loanEX);
                Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
                model.addAttribute("credit",credit);
            }
        }

        if (loanEX != null){        //如果目前用户的贷款审核正等待确认的情况
            if (loanEX.getLoanStatue() == 5){
                step = 2;
                System.out.println("loanEX：" + loanEX);
                model.addAttribute("loan",loanEX);
                Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
                action = loanService.selectActionByLId(loanEX);
                model.addAttribute("credit",credit);
                model.addAttribute("action",action);

                if (action.getAcMoney() != 0){  //如果已经提交具体贷款金额

                }
            }else if (loanEX.getLoanStatue() == 1){
                step = 3;
                System.out.println("loanEX：" + loanEX);
                model.addAttribute("loan",loanEX);
                Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
                action = loanService.selectActionByLId(loanEX);
                model.addAttribute("credit",credit);
                model.addAttribute("action",action);
                UserBank userBank = bankDao.getBC(((Userimf)session.getAttribute("user")).getuId());
                model.addAttribute("weihao",userBank.getCardId().substring(userBank.getCardId().length() - 4,userBank.getCardId().length()));
                model.addAttribute("bank",userBank);
                model.addAttribute("loanBankHistory",loanService.selectByLId(loanEX));
            }
        }

        model.addAttribute("step",step);
        return "loan/loanJieApply";
    }

    @RequestMapping("/toSubmitAcMoney")
    public String toSubmitAcMoney(Action action,HttpSession session,Loan loan){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        loan = (Loan) session.getAttribute("userLoan");

        System.out.println("loan:" + loan);

        Calendar calendar = Calendar.getInstance();

        int lowLimitYear = 0;
        int lowLimit = loan.getLowLimit();

        if (lowLimit >= 12){
            lowLimitYear = lowLimit / 12;
            lowLimit = lowLimit % 12;
        }

        if ((calendar.get(Calendar.MONTH) + lowLimit) > 12){
            lowLimitYear += 1;
        }

//        calendar.add(Calendar.YEAR,lowLimitYear);
        calendar.add(Calendar.MONTH,lowLimit);//获取最短还贷时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("最短：" + simpleDateFormat.format(calendar.getTime()) + "\n" + "lowLimit:" + lowLimit + " lowYearLimit:" + lowLimitYear);

        Date minTime = calendar.getTime();

        action.setMinRepayTime(minTime);

        Calendar calendarEX = Calendar.getInstance();

        int topLimitYear = 0;
        int topLimit = loan.getTopLimit();

        if (topLimit >= 12){
            topLimitYear = topLimit / 12;
            topLimit = topLimit % 12;
        }

        if ((calendarEX.get(Calendar.MONTH) + topLimit) > 12){
            topLimitYear += 1;
        }

//        calendarEX.add(Calendar.YEAR,topLimitYear);
        calendarEX.add(Calendar.MONTH,topLimit);//获取最长还贷时间
        System.out.println("最长：" + simpleDateFormat.format(calendarEX.getTime()) + "\n" + "topLimit:" + topLimit + " topYearLimit:" + topLimitYear);                    //不改变年份，明天继续奋斗

        action.setMaxRepayTime(calendarEX.getTime());

        float acMoney = action.getAcMoney();
        acMoney = (float) (acMoney * 0.0001);
        action.setAcMoney(acMoney);
        action.setAcStatus(5);

        float tobePay = (float) (acMoney + (acMoney * loan.getInterest() * 0.01));
        action.setTobePay(tobePay);

        System.out.println("action:" + action);

        loanService.updateAction(action);

        return "redirect:/loan/toloanJie";
    }

    @RequestMapping("/checkAcMoney")
    @ResponseBody
    public String checkAcMoney(HttpSession session, float acMoney){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        Loan loan = (Loan) session.getAttribute("userLoan");
        float minLimit = loan.getMinLimit();
        float maxLimit = loan.getMaxLimit();
        acMoney = (float) (acMoney * 0.0001);
        System.out.println("acMoney"+acMoney);

        if (acMoney > maxLimit){
            System.out.println("max");

            return "max";
        }else if (acMoney < minLimit){
            System.out.println("min");

            return "min";
        }

        return "success";
    }

    @RequestMapping("/toLoanEnd")
    public String toLoanEnd(HttpSession session){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        Loan loan = (Loan) session.getAttribute("userLoan");
        loan.setLoanStatue(1);
        Action action = loanService.selectActionByLId(loan);
        action.setAcStatus(1);
        action.setActime(new Date());

        loanService.updateAction(action);
        loanService.updateLoanStatus(loan);

        session.setAttribute("userLoan",loanService.LoanNow((Userimf) session.getAttribute("user")));

        UserBank userBank = loanService.selectByUId((Userimf) session.getAttribute("user"));

        loanService.insertLoanBankHistory(loan,userBank,action);

        if (loan.getLoanType() == 2){           //抵押类型的贷款
            if (loan.getGuarantees() == 1){     //车抵押
                Car car = loanService.selectCarByUId((Userimf) session.getAttribute("user"));
                car.setStatus(2);

                loanService.updateCarByUId(car);
            }else if (loan.getGuarantees() == 2){//房子抵押
                House house = loanService.selectHouseByUId((Userimf) session.getAttribute("user"));
                house.setStatus(2);

                loanService.updateHouseByUId(house);
            }
        }else if (loan.getLoanType() == 3){     //担保类型的贷款
            Warrant warrant = new Warrant();
            warrant.setwId(loan.getwId());
            warrant.setStatus(2);

            loanService.updateWarrantStatusByWId(warrant);
        }
        backLoanDao.updPerBidStatus(loan,action);

        return "redirect:/loan/toloanJie";
    }

    @RequestMapping("/toChooseLoanType")
    public String toChooseLoanType(HttpSession session){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/chooseLoanType";
    }

    @RequestMapping("/toCreditRegister")
    public String toCreditRegister(HttpSession session){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "user/creditRegister";
    }

    @RequestMapping("/registerCredit")
    public String registerCredit(Credit credit, HttpSession session, MultipartFile positiveIDPhotoEX, MultipartFile negativeIDPhotoEX, HttpServletRequest request) throws IOException {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        String realPath =  request.getSession().getServletContext().getRealPath("");
        String dirPath = "D:\\vault\\file\\identity\\";
        //上传文件
        String positiveIDPhotoEXFileName = fileUpload.upload(positiveIDPhotoEX,dirPath,null,request,null);
        String negativeIDPhotoEXFileName = fileUpload.upload(negativeIDPhotoEX,dirPath,null,request,null);
        credit.setPositiveIDPhoto(positiveIDPhotoEX.getOriginalFilename());
        credit.setNegativeIDPhoto(negativeIDPhotoEX.getOriginalFilename());


        return "";
    }

    @RequestMapping("/loanJie")
    public String loanJie(HttpSession session){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "";
    }

    @RequestMapping("/toloanHuan")
    public String toloanHuan(HttpSession session, Model model) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
        Action action = loanService.selectActionByLId((Loan) session.getAttribute("userLoan"));
        model.addAttribute("credit",credit);
        model.addAttribute("action",action);
        UserBank userBank = bankDao.getBC(((Userimf)session.getAttribute("user")).getuId());
        model.addAttribute("bank",userBank);
        model.addAttribute("loanBankHistory",loanService.selectByLId((Loan) session.getAttribute("userLoan")));

        return "loan/loanHuan";
    }

    @RequestMapping("/toHuanEnd")
    public String toHuanEnd(HttpSession session,Action action){
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        System.out.println("还款");

        //修改用户余额
        Userimf userimf = (Userimf) session.getAttribute("user");
        userimf.setAvaBalance(userimf.getAvaBalance() - (action.getTobePay() * 10000));

        loanService.updateAvanBanlanceByUId(userimf);

        //修改loan表状态为已还款
        Loan loan = (Loan) session.getAttribute("userLoan");
        loan.setLoanStatue(3);

        loanService.updateLoanStatus(loan);

        //修改action表状态为已完成
        action.setAcStatus(3);

        loanService.updateActionStatusByAId(action);

        if (loan.getLoanType() == 2){           //抵押类型的贷款，吧抵押类型的抵押信息设置回可用
            if (loan.getGuarantees() == 1){     //车抵押
                Car car = loanService.selectCarByUId((Userimf) session.getAttribute("user"));
                car.setStatus(1);

                loanService.updateCarByUId(car);
            }else if (loan.getGuarantees() == 2){//房子抵押
                House house = loanService.selectHouseByUId((Userimf) session.getAttribute("user"));
                house.setStatus(1);

                loanService.updateHouseByUId(house);
            }
        }else if (loan.getLoanType() == 3){     //担保类型的贷，把使用过的担保信息设置为已经使用过了
            Warrant warrant = new Warrant();
            warrant.setwId(loan.getwId());
            warrant.setStatus(3);

            loanService.updateWarrantStatusByWId(warrant);
        }

        //还款记录表记录信息
        Repaymen repaymen = new Repaymen();
        repaymen.setlId(loan.getlId());
        repaymen.setRepayTime(new Date());
        repaymen.setRepayMoney(action.getTobePay());

        loanService.insertRepaymen(repaymen);

        PerBid perBid = perBidDao.selectPerBidByPerBidId(loan);
        perBid.setBidStatus(2);

        perBidDao.updatePerBidStatus(perBid);//还款后把散标状态改为  关闭交易（CLOSE=2）

        return "loan/loanHuanEnd";
    }

    @RequestMapping("/toloanRecord")
    public String toloanRecord(HttpSession session,Model model) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        List loanList = new ArrayList();

        List loanListYuan = loanService.allLoanByUId((Userimf) session.getAttribute("user"));

        for (Object o : loanListYuan){
            Loan loan = (Loan)o;
            Map loanEx = new HashMap();
            loanEx.put("applicationTime",loan.getApplicationTime());
            loanEx.put("lId",loan.getlId());
            loanEx.put("loanType",loan.getLoanType());
            loanEx.put("lowLimit",loan.getLowLimit());

            Action action = loanService.selectActionByLId(loan);

            loanEx.put("acMoney",action.getAcMoney());
            loanEx.put("loanStatue",loan.getLoanStatue());

            loanList.add(loanEx);
        }

        model.addAttribute("loanList",loanList);

        return "loan/loanRecord";
    }
    @RequestMapping("/toloanPersonage")
    public String toloanPersonage(HttpSession session, Model model) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        model.addAttribute("credit",loanService.selectCredit((Userimf) session.getAttribute("user")));
        model.addAttribute("worryCall",worryCallDao.selectWorryByUId((Userimf) session.getAttribute("user")));

        return "loan/loanPersonage";
    }

    public Boolean checkSessionIsEmpty(HttpSession session){
        Userimf userimf = (Userimf)session.getAttribute("user");

        if (userimf == null){
            return true;
        }else {
            session.setAttribute("user",loanService.selectUserimfByUId(((Userimf)session.getAttribute("user"))));
            session.setAttribute("userLoan",loanService.LoanNow((Userimf) session.getAttribute("user")));
            return false;
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        loanService.TestInsertLoan();

        List loanList = loanService.TestAllLoan();

        return loanList.toString();
    }
}
