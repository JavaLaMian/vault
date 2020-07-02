package com.vault.demo.controller.loan;

import com.vault.demo.bean.*;
import com.vault.demo.dao.BankDao;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

            if (credit == null
                    || credit.getName() == null || "".equals(credit.getName())
                    || credit.getIdentity() == null || "".equals(credit.getIdentity())
                    || userBank == null
                    || credit.getDepart() == null || "".equals(credit.getDepart())
                    || credit.getWages() == null || "".equals(credit.getWages())
                    || userimf.getPhe() == null || "".equals(userimf.getPhe())
                    ||  worryCall == null
                    || userimf.getDealPsw() == null || "".equals(userimf.getDealPsw())
                    ){
                model.addAttribute("loanNotPushType","请完善您的个人详细信息（真实姓名、身份证、银行卡、职业、收入、紧急联系人、联系电话、支付密码）");
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);

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

            if (credit == null
                    || credit.getName() == null || "".equals(credit.getName())
                    || credit.getIdentity() == null || "".equals(credit.getIdentity())
                    || userBank == null
                    || credit.getDepart() == null || "".equals(credit.getDepart())
                    || credit.getWages() == null || "".equals(credit.getWages())
                    || userimf.getPhe() == null || "".equals(userimf.getPhe())
                    ||  worryCall == null
                    || userimf.getDealPsw() == null || "".equals(userimf.getDealPsw())
            ){
                model.addAttribute("loanNotPushType","请完善您的个人详细信息（真实姓名、身份证、银行卡、职业、收入、紧急联系人、联系电话、支付密码）");
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);

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

            if (credit == null
                    || credit.getName() == null || "".equals(credit.getName())
                    || credit.getIdentity() == null || "".equals(credit.getIdentity())
                    || userBank == null
                    || credit.getDepart() == null || "".equals(credit.getDepart())
                    || credit.getWages() == null || "".equals(credit.getWages())
                    || userimf.getPhe() == null || "".equals(userimf.getPhe())
                    ||  worryCall == null
                    || userimf.getDealPsw() == null || "".equals(userimf.getDealPsw())
            ){
                model.addAttribute("loanNotPushType","请完善您的个人详细信息（真实姓名、身份证、银行卡、职业、收入、紧急联系人、联系电话、支付密码）");
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);
//                model.addAttribute("Name",true);

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

        calendar.add(Calendar.YEAR,lowLimitYear);
        calendar.add(Calendar.MONTH,lowLimit);//获取最短还贷时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("最短：" + simpleDateFormat.format(calendar.getTime()) + "\n" + "lowLimit:" + lowLimit + " lowYearLimit:" + lowLimitYear);

        Date minTime = calendar.getTime();

        action.setMinRepayTime(minTime);

        calendar = Calendar.getInstance();

        int topLimitYear = 0;
        int topLimit = loan.getTopLimit();

        if (topLimit >= 12){
            topLimitYear = topLimit / 12;
            topLimit = topLimit % 12;
        }

        if ((calendar.get(Calendar.MONTH) + topLimit) > 12){
            topLimitYear += 1;
        }

        calendar.add(Calendar.YEAR,topLimitYear);
        calendar.add(Calendar.MONTH,topLimit);//获取最长还贷时间
        System.out.println("最长：" + simpleDateFormat.format(calendar.getTime()) + "\n" + "topLimit:" + topLimit + " topYearLimit:" + topLimitYear);                                //不改变年份，明天继续奋斗

        float acMoney = action.getAcMoney();
        acMoney = (float) (acMoney * 0.0001);
        action.setAcMoney(acMoney);
        action.setMaxRepayTime(calendar.getTime());
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

        session.setAttribute("userLoan",loanService.LoanNow((Userimf) session.getAttribute("user")));

        Credit credit = loanService.selectCredit(((Userimf)session.getAttribute("user")));
        Action action = loanService.selectActionByLId((Loan) session.getAttribute("userLoan"));
        model.addAttribute("credit",credit);
        model.addAttribute("action",action);
        UserBank userBank = bankDao.getBC(((Userimf)session.getAttribute("user")).getuId());
        model.addAttribute("bank",userBank);
        model.addAttribute("loanBankHistory",loanService.selectByLId((Loan) session.getAttribute("userLoan")));

        return "loan/loanHuan";
    }
    @RequestMapping("/toloanRecord")
    public String toloanRecord(HttpSession session) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/loanRecord";
    }
    @RequestMapping("/toloanPersonage")
    public String toloanPersonage(HttpSession session) {
        if (checkSessionIsEmpty(session)){//检测用户是否登录
            return "redirect:/loan/main";
        }

        return "loan/loanPersonage";
    }

    public Boolean checkSessionIsEmpty(HttpSession session){
        Userimf userimf = (Userimf)session.getAttribute("user");

        if (userimf == null){
            return true;
        }else {
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
